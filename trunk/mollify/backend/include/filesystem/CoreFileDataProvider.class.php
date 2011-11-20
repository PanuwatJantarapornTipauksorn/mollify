<?php

	/**
	 * Copyright (c) 2008- Samuli Järvelä
	 *
	 * All rights reserved. This program and the accompanying materials
	 * are made available under the terms of the Eclipse Public License v1.0
	 * which accompanies this distribution, and is available at
	 * http://www.eclipse.org/legal/epl-v10.html. If redistributing this code,
	 * this entire header must remain intact.
	 */
		
	class CoreFileDataProvider {
		private $env;
		
		public function __construct($env) {
			$this->env = $env;
		}
		
		public function init($c) {
			$c->registerDataRequestPlugin(array("core-file-modified", "core-item-description"), $this);
		}
				
		public function getRequestData($parent, $items, $result, $key, $requestData) {
			$result = array();
			foreach($items as $i) {
				if (strcmp("core-file-modified", $key) === 0)
					$result[$i->id()] = $this->env->formatTimestampInternal($i->lastModified());
				if (strcmp("core-item-description", $key) === 0)
					$result[$i->id()] = $this->env->filesystem()->description($i);

			} 
			return $result;
		}
				
		public function __toString() {
			return "CoreFileDataProvider";
		}
	}
?>