/**
	Copyright (c) 2008- Samuli J�rvel�

	All rights reserved. This program and the accompanying materials
	are made available under the terms of the Eclipse Public License v1.0
	which accompanies this distribution, and is available at
	http://www.eclipse.org/legal/epl-v10.html. If redistributing this code,
	this entire header must remain intact.
*/

function createAction(action, parent, button) {
	var formId = $(parent).attr('id') + '-' + action;
	$(parent).append('<form id="'+formId+'" method="post"><input type="hidden" name="action" value="'+action+'"></form>');
	$(button).click(function() {
		$('#'+formId).submit();
	});
}