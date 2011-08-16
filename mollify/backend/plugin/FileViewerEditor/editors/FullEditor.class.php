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

	abstract class FullEditor extends EditorBase {
		public function getInfo($item) {
			return array(
				"embedded" => $this->getUrl($item, "embedded", TRUE),
				"full" => $this->getUrl($item, "full", TRUE)
			);
		}
		
		public function processRequest($item, $path) {
			if ($path[0] === 'embedded')
				$this->processEmbeddedEditorRequest($item);
			else if ($path[0] === 'full')
				$this->processFullEditorRequest($item);
			else
				throw $this->invalidRequestException();
		}

		protected function processEmbeddedEditorRequest($item) {
			$html = '<html>
				<head>
					<title>'.$item->name().'</title>
					<script type="text/javascript" src="'.$this->getCommonResourcesUrl().'jquery-1.4.2.min.js"></script>
					<script>
						function onEditorSave(s) {
							var data = getSaveContent();
							$.ajax({
								type: "POST",
								processData: false,
								contentType: "text/plain",
								url: "'.$this->getServiceUrl("filesystem", array($item->publicId(), 
"content"), TRUE).'",
								data: data,
								success: function(result) {
									s();
								},
								error: function (xhr, desc, exc) {
									alert("error");
								}
							});
						}
						function getSaveContent() {
						'.$this->getDataJs().'
						}
					</script>
				</head>
				<body>'.$this->getHtml($item).'
				</body>
			</html>';
			
			$this->response()->html($html);
		}
		
		protected abstract function getHtml($item);
		
		protected abstract function getDataJs();
	}
?>