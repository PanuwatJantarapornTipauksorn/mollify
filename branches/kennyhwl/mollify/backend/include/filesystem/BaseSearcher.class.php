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
	 			
	 abstract class BaseSearcher {
		public function match($item, $text) {
			$m = $this->getMatch($item, $text);
			if ($m) return array("item" => $item->data(), "match" => $m);
			return NULL;
		}
		
		protected abstract function getMatch($item, $text);
	}
?>