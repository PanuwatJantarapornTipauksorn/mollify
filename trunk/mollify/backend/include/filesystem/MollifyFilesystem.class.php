<?php

	/**
	 * Copyright (c) 2008- Samuli J�rvel�
	 *
	 * All rights reserved. This program and the accompanying materials
	 * are made available under the terms of the Eclipse Public License v1.0
	 * which accompanies this distribution, and is available at
	 * http://www.eclipse.org/legal/epl-v10.html. If redistributing this code,
	 * this entire header must remain intact.
	 */

	abstract class MollifyFilesystem {
		const TYPE_LOCAL = "local";
		
		protected $id;
		protected $name;
		protected $filesystemInfo;
		
		function __construct($id, $name, $filesystemInfo) {
			$this->id = $id;
			$this->name = $name;
			$this->filesystemInfo = $filesystemInfo;
		}
		
		abstract function type();
		
		abstract function exists();
		
		abstract function create();

		public function id() {
			return $this->id;
		}

		public function internalId($item) {
			return $this->filesystemInfo->internalId($item->id());
		}

		public function internalPath($item) {
			return $this->filesystemInfo->internalPath($item->id());
		}
				
		public function itemId($path) {
			return $this->filesystemInfo->publicId($this->id(), $path);
		}
		
		public function name() {
			return $this->name;
		}
		
		public function rootId() {
			return $this->filesystemInfo->publicId($this->id);
		}
		
		public function root() {
			$id = $this->rootId();
			Logging::logDebug("Filesystem [".$this->id."] root [".$id."]");
			return $this->createItem($id, '');
		}
		
		public abstract function createItem($id, $path, $nonexisting = FALSE);
		
		public function details($item) {
			return array();
		}
		
		public function items($folder) {
			return $this->filesystemInfo->items($folder);
		}
		
		public abstract function folders($parent);
		
		public abstract function files($parent);
		
		public abstract function parent($item);
		
		public abstract function size($file);
		
		public abstract function rename($item, $name);
		
		public abstract function copy($item, $to);
		
		public abstract function move($item, $to);
		
		public abstract function delete($item);
		
		public abstract function read($item, $range = NULL);
		
		public abstract function write($item);
		
		public abstract function addToZip($item, $zip);
				
		public abstract function createFolder($folder, $name);
		
		public abstract function createEmptyItem($folder, $name);
		
		protected function ignoredItems($path) {
			return $this->filesystemInfo->ignoredItems($this, $path);
		}
		
		protected function itemPublicId($path) {
			return $this->filesystemInfo->publicId($this->id(), $path);
		}
		
		protected function itemWithPath($path, $create = FALSE) {
			return $this->createItem($this->itemPublicId($path), $path, $create);
		}

		public function __toString() {
			return get_class($this)." (".$this->id.") ".$this->name;
		}
	}
	
	class NonExistingFolderException extends ServiceException {}
?>