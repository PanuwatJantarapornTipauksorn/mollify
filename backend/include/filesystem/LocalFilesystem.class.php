<?php

	/**
	 * LocalFilesystem.class.php
	 *
	 * Copyright 2008- Samuli Järvelä
	 * Released under GPL License.
	 *
	 * License: http://www.mollify.org/license.php
	 */

	class LocalFilesystem extends MollifyFilesystem {
		private $rootPath;
		
		function __construct($id, $def, $filesystemInfo) {
			parent::__construct($id, $def['name'] != NULL ? $def['name'] : $def['default_name'], $filesystemInfo);
			if ($def == NULL or !isset($def["path"])) throw new ServiceException("INVALID_CONFIGURATION", "Invalid filesystem definition");
			$this->rootPath = self::folderPath($def["path"]);
		}
		
		public function isDirectDownload() {
			return TRUE;
		}
		
		public function assert() {
			if (!$this->exists())
				throw new NonExistingFolderException("INVALID_CONFIGURATION", "Invalid folder definition, path does not exist [".$this->id()."]");
		}
		
		public function exists() {
			return file_exists($this->filesystemInfo->env()->convertCharset($this->rootPath, FALSE));
		}
		
		public function create() {
			$rootPath = $this->filesystemInfo->env()->convertCharset($this->rootPath, FALSE);
			
			if (!mkdir($rootPath, 0755)) return FALSE;
			if ($this->filesystemInfo->env()->features()->isFeatureEnabled("folder_protection")) {
				copy($this->filesystemInfo->env()->getScriptRootPath()."/include/apache/htaccess", $rootPath.'.htaccess');
			}
			return TRUE;
		}
		
		public function type() {
			return MollifyFilesystem::TYPE_LOCAL;
		}

		public function createItemWithInternalPath($id, $path) {
			if (strpos($path, $this->rootPath) === FALSE)
				throw new ServiceException("INVALID_REQUEST", "Illegal filesystem (".$this->rootPath.") for path: ".$path);
			return $this->createItem($id, $this->publicPath($path));
		}
				
		public function createItem($id, $path) {
			self::assertPath($path);
			
			$fullPath = self::joinPath($this->rootPath, $path);
			$isFile = (strcasecmp(substr($fullPath, -1), DIRECTORY_SEPARATOR) != 0);
			
			if ($isFile) return new File($id, $this->rootId(), $path, self::basename($fullPath), $this);
			return new Folder($id, $this->rootId(), $path, self::basename($fullPath), $this);
		}

		private function publicPath($path) {
			return substr($path, strlen($this->rootPath));
		}
		
		public function internalPath($item) {
			return $this->localPath($item);
		}
		
		public function localPath($item) {
			return self::joinPath($this->rootPath, $item->path());
		}
		
		public function itemExists($item) {
			return file_exists($this->internalPath($item));
		}
		
		public function details($item) {
			$datetimeFormat = $this->internalTimestampFormat();
			
			$details = array("id" => $item->id());
			if ($item->isFile()) {
				$path = $this->filesystemInfo->env()->convertCharset($this->localPath($item), FALSE);
				$details["last_changed"] = date($datetimeFormat, filectime($path));
				$details["last_modified"] = date($datetimeFormat, filemtime($path));
				$details["last_accessed"] = date($datetimeFormat, fileatime($path));
			}
			return $details;
		}

		public function extension($item) {
			if (!$item->isFile()) return NULL;
			
			$extPos = strrpos($item->name(), '.');
			if ($extPos > 0)
				return substr($item->name(), $extPos + 1);
			return "";
		}

		public function items($parent) {
			$parentPath = $this->localPath($parent);
			$nativeParentPath = $this->filesystemInfo->env()->convertCharset($parentPath, FALSE);
			
			$items = scandir($nativeParentPath);
			if (!$items) throw new ServiceException("INVALID_PATH", $parent->id());
			
			$ignored = $this->ignoredItems($this->publicPath($parentPath));
				
			$result = array();
			foreach($items as $i => $name) {
				if (substr($name, 0, 1) == '.') continue;
				if (in_array(strtolower($name), $ignored)) continue;
				
				$path = self::joinPath($parentPath, $this->filesystemInfo->env()->convertCharset($name));
				$nativePath = self::joinPath($nativeParentPath, $name);
				$itemName = $this->filesystemInfo->env()->convertCharset($name);
				
				if (!is_dir($nativePath)) {	
					$p = $this->publicPath($path);
					$result[] = new File($this->itemId($p), $this->rootId(), $p, $itemName, $this);
				} else {
					$p = $this->publicPath(self::folderPath($path));
					$result[] = new Folder($this->itemId($p), $this->rootId(), $p, $itemName, $this);
				}
			}
			
			return $result;
		}
		
		public function hierarchy($item) {
			$result = array();
			
			$root = $item->root();
			$result[] = $root;

			$to = $item->isFile() ? $item->parent() : $item;
			
			$toPath = $this->localPath($to);
			$rootPath = $root->internalPath();
						
			$parts = preg_split("/[\/]/", substr($toPath, strlen($rootPath)), -1, PREG_SPLIT_NO_EMPTY);
			$current = $rootPath;
			
			foreach($parts as $part) {
				$current .= $part.DIRECTORY_SEPARATOR;
				
				$public = $this->publicPath(self::folderPath($current));
				$itemId = $this->itemId($public);

				$result[] = new Folder($itemId, $this->rootId(), $public, $part, $this);
			}
			
			return $result;
		}
		
		/* nativePath assumes path in local charset, not utf8 */
		private function allFilesRecursively($nativePath) {
			$files = scandir($nativePath);
			if (!$files) throw new ServiceException("INVALID_PATH", $this->path);
			
			$ignored = $this->ignoredItems($this->publicPath($nativePath));
			$result = array();
			
			foreach($files as $i => $name) {
				if (substr($name, 0, 1) == '.' || in_array(strtolower($name), $ignored))
					continue;
	
				$fullPath = self::joinPath($nativePath, $name);
				if (is_dir($fullPath)) {
					$result = array_merge($result, $this->allFilesRecursively($fullPath));
					continue;
				}
				
				$result[] = $fullPath;
			}
			return $result;
		}
		
		public function parent($item) {
			if ($item->path() === '') return NULL;
			
			$path = $this->localPath($item);
			return $this->itemWithPath($this->publicPath(self::folderPath(dirname($path))));
		}

		public function rename($item, $name) {
			self::assertFilename($name);
			
			$old = $this->localPath($item);
			$new = self::joinPath(dirname($old), $name);
			
			if (!$item->isFile()) $new = self::folderPath($new);

			if (file_exists($this->filesystemInfo->env()->convertCharset($new, FALSE)))
				throw new ServiceException("FILE_ALREADY_EXISTS", "Failed to rename [".$item->id()."], target already exists (".$new.")");

			if (!rename($this->filesystemInfo->env()->convertCharset($old, FALSE), $this->filesystemInfo->env()->convertCharset($new, FALSE))) throw new ServiceException("REQUEST_FAILED", "Failed to rename [".$item->id()."]");
			
			return $this->createItem($item->id(), $this->publicPath($new));
		}

		public function copy($item, $to) {			
			$target = $to->internalPath();

			if (file_exists($target)) throw new ServiceException("FILE_ALREADY_EXISTS", "Failed to copy [".$item->id()."] to [".$to->id()."], target already exists (".$target.")");
			
			$result = FALSE;
			if ($item->isFile()) {
				$result = copy($this->filesystemInfo->env()->convertCharset($item->internalPath(), FALSE), $this->filesystemInfo->env()->convertCharset($target, FALSE));
			} else {
				$result = $this->copyFolderRecursively($this->filesystemInfo->env()->convertCharset($item->internalPath(), FALSE), $this->filesystemInfo->env()->convertCharset($target, FALSE));
			}
			if (!$result) throw new ServiceException("REQUEST_FAILED", "Failed to copy [".$item->id()." to .".$to->id()."]");
			
			return $to;
		}
		
		private function copyFolderRecursively($from, $to) { 
			$dir = opendir($from); 
			@mkdir($to);
		    
		    while (false !== ($item = readdir($dir))) { 
		        if (($item == '.') or ($item == '..')) continue;
		        
		        $source = $from.DIRECTORY_SEPARATOR.$item;
		        $target = $to.DIRECTORY_SEPARATOR.$item;
		        
				if (is_dir($source))
					$this->copyFolderRecursively($source, $target);
				else
					copy($source, $target);
		    } 
		    closedir($dir);
		    return TRUE; 
		} 
		
		public function move($item, $to) {			
			$target = self::joinPath($to->internalPath(), $item->name());
			if (!$item->isFile()) $target = self::folderPath($target);
			if (file_exists($this->filesystemInfo->env()->convertCharset($target, FALSE))) throw new ServiceException("FILE_ALREADY_EXISTS", "Failed to move [".$item->id()."] to [".$to->id()."], target already exists (".$target.")");
			if (!rename($this->filesystemInfo->env()->convertCharset($item->internalPath(), FALSE), $this->filesystemInfo->env()->convertCharset($target, FALSE))) throw new ServiceException("REQUEST_FAILED", "Failed to move [".$item->id()."] to ".$target);
			
			return $to->filesystem()->createItemWithInternalPath($item->id(), $target);
		}
		
		public function delete($item) {
			if ($item->isFile()) {
				if (!unlink($this->filesystemInfo->env()->convertCharset($this->localPath($item), FALSE)))
					throw new ServiceException("REQUEST_FAILED", "Cannot delete [".$item->id()."]");				
			} else {		
				$this->deleteFolderRecursively($this->filesystemInfo->env()->convertCharset($this->localPath($item), FALSE));
			}
		}
		
		private function deleteFolderRecursively($path) {
			$path = self::folderPath($path);
			$handle = opendir($path);
			
			if (!$handle)
				throw new ServiceException("REQUEST_FAILED", "Could not open directory for traversal (recurse): ".$path);
		    
		    while (false !== ($item = readdir($handle))) {
				if ($item != "." and $item != ".." ) {
					$fullpath = $path.$item;
	
					if (is_dir($fullpath)) {
						$this->deleteFolderRecursively($fullpath);
					} else {
						if (!unlink($fullpath)) {
							closedir($handle);
							throw new ServiceException("REQUEST_FAILED", "Failed to remove file (recurse): ".$fullpath);
						}
					}
				}
			}
			
			closedir($handle);
			
			if (!rmdir($path))
				throw new ServiceException("REQUEST_FAILED", "Failed to remove directory (delete_directory_recurse): ".$path);
		}
		
		public function createFolder($folder, $name) {
			self::assertFilename($name);
			
			$path = self::folderPath(self::joinPath($this->localPath($folder), $name));
			if (file_exists($this->filesystemInfo->env()->convertCharset($path, FALSE))) throw new ServiceException("DIR_ALREADY_EXISTS", $folder->id()."/".$name);
			if (!mkdir($this->filesystemInfo->env()->convertCharset($path, FALSE), $this->filesystemInfo->setting("new_folder_permission_mask"))) {
				throw new ServiceException("CANNOT_CREATE_FOLDER", $folder->id()."/".$name);
			} else {
				chmod($this->filesystemInfo->env()->convertCharset($path, FALSE), $this->filesystemInfo->setting("new_folder_permission_mask"));
			}
			return $this->itemWithPath($this->publicPath($path));
		}
		
		public function createFile($folder, $name) {
			self::assertFilename($name);
			
			$target = self::joinPath($this->localPath($folder), $name);
			if (file_exists($this->filesystemInfo->env()->convertCharset($target, FALSE))) throw new ServiceException("FILE_ALREADY_EXISTS");
			return $this->itemWithPath($this->publicPath($target));
		}

		public function fileWithName($folder, $name) {
			self::assertFilename($name);
			
			$path = self::joinPath($this->localPath($folder), $name);
			return $this->itemWithPath($this->publicPath($path));
		}

		public function folderWithName($folder, $name) {
			self::assertFilename($name);
			
			$path = self::joinPath($this->localPath($folder), $name.DIRECTORY_SEPARATOR);
			return $this->itemWithPath($this->publicPath($path));
		}
		
		public function size($file) {
			return sprintf("%u", filesize($this->filesystemInfo->env()->convertCharset($this->localPath($file), FALSE)));
		}
		
		public function lastModified($item) {
			return filemtime($this->filesystemInfo->env()->convertCharset($this->localPath($item), FALSE));
		}

		public function read($item, $range = NULL) {
			$handle = @fopen($this->filesystemInfo->env()->convertCharset($this->localPath($item), FALSE), "rb");
			if (!$handle)
				throw new ServiceException("REQUEST_FAILED", "Could not open file for reading: ".$item->id());
			return $handle;
		}
		
		public function write($item, $s, $append = FALSE) {
			$handle = @fopen($this->filesystemInfo->env()->convertCharset($this->localPath($item), FALSE), ($append ? "ab" : "wb"));
			if (!$handle)
				throw new ServiceException("REQUEST_FAILED", "Could not open file for writing: ".$item->id());
			while (!feof($s)) {
				set_time_limit(0);
				fwrite($handle, fread($s, 1024));
			}
			fclose($handle);
		}
		
		public function put($item, $content) {
			file_put_contents($this->filesystemInfo->env()->convertCharset($this->localPath($item), FALSE), $content);
		}

		public function addTo($item, $c) {
			if ($item->isFile()) {
				$c->add($item->name(), $this->filesystemInfo->env()->convertCharset($this->localPath($item), FALSE), $item->size());
			} else {
				if ($c->acceptFolders()) {
					$c->add($item->name(), $this->filesystemInfo->env()->convertCharset($this->localPath($item), FALSE));
				} else {					
					$offset = strlen($this->localPath($item)) - strlen($item->name()) - 1;
					$files = $this->allFilesRecursively($this->filesystemInfo->env()->convertCharset($this->localPath($item), FALSE));	//TODO rights!
					
					foreach($files as $file) {
						$st = stat($file);
						$c->add($this->filesystemInfo->env()->convertCharset(substr($file, $offset)), $file, $st['size']);
					}
				}
			}
		}
		
		public function __toString() {
			return "LOCAL (".$this->id.") ".$this->name."(".$this->rootPath.")";
		}

		public static function assertFilename($name) {
			if (strlen($name) == 0) return;
			if (strpos($name, "\\") !== FALSE or strpos($name, "/") !== FALSE)
				throw new ServiceException("INVALID_REQUEST", "Invalid name [".$name."]");
		}

		public static function assertPath($path) {
			if (strlen($path) == 0) return;
			if (strpos($path, "..\\") !== FALSE or strpos($path, "../") !== FALSE)
				throw new ServiceException("INVALID_REQUEST", "Invalid path [".$path."]");
		}
		
		static function joinPath($item1, $item2) {
			return self::folderPath($item1).$item2;
		}
		
		static function folderPath($path) {
			return rtrim($path, DIRECTORY_SEPARATOR).DIRECTORY_SEPARATOR;
		}
		
		static function basename($path) {
			$name = strrchr(rtrim($path, DIRECTORY_SEPARATOR), DIRECTORY_SEPARATOR);
			if (!$name) return "";
			return substr($name, 1);
		}
	}
?>