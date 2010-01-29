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

	require_once("include/Session.class.php");
	
	class InstallerSession extends Session {
		public function __construct($settings) {
			$this->name = "MOLLIFY_SESSION";			
			if ($settings->hasSetting("session_name")) {
				$n = $settings->setting("session_name");
				if (strlen($n) > 0) $this->name .= "_".$n;
			}
		}
		
	}
?>