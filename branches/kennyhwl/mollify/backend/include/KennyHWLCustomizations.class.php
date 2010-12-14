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

	class KennyHWLCustomizations {
		static $FOLDER_PATHS = "/foo/kennyhwl/users/";
		static $INBOX_NAME = "Inbox";
		
		protected $env;
		
		public function __construct($env) {
			$this->env = $env;
			$this->env->filesystem()->registerDetailsPlugin($this);
		}
		
		public function onUserAdded($id, $user) {
			if (strtoupper($user['permission_mode']) !== 'NO') return;
			
			$folderName = $user["name"];
			$folderPath = self::$FOLDER_PATHS.$folderName;
			
			mkdir($folderPath);
			mkdir($folderPath.DIRECTORY_SEPARATOR.self::$INBOX_NAME);
			
			$folderId = $this->env->configuration()->addFolder($folderName, $folderPath);
			$this->env->configuration()->addUserFolder($id, $folderId, NULL);
			
			$fs = $this->env->filesystem()->filesystem(array("id" => $folderId, "path" => $folderPath, "name" => $folderName), FALSE);
			$this->env->configuration()->addItemPermission($fs->root()->id(), Authentication::PERMISSION_VALUE_READWRITE, $id);
		}
		
		public function getItemDetails($item) {
			if (!$item->isFile()) {
				if ($item->path() === self::$INBOX_NAME.DIRECTORY_SEPARATOR)
					return array("protected" => true);
				return array("protected" => false);
			}
			return FALSE;
		}
		
		public function __toString() {
			return "KennyHWLCustomizations";
		}
	}
?>