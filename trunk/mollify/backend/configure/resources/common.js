/**
	Copyright (c) 2008- Samuli J�rvel�

	All rights reserved. This program and the accompanying materials
	are made available under the terms of the Eclipse Public License v1.0
	which accompanies this distribution, and is available at
	http://www.eclipse.org/legal/epl-v10.html. If redistributing this code,
	this entire header must remain intact.
*/

jQuery.fn.exists = function() { return ($(this).length > 0); }

var preRequestCallback = null;
var postRequestCallback = null;

function initializeButtons() {
	$('button').each(function() {
		$(this).hover(
			function(){ 
				$(this).addClass("ui-state-hover"); 
			},
			function(){ 
				$(this).removeClass("ui-state-hover"); 
			}
		);
	});
}

function getSessionInfo(success, fail) {
	request("GET", 'r.php/session/info/1_5_0', success, fail);
}

function getUsers(success, fail) {
	request("GET", 'r.php/configuration/users', success, fail);
}

function addUser(name, pw, permission, success, fail) {
	var data = JSON.stringify({name:name, password:generate_md5(pw), "permission_mode":permission});
	request("POST", 'r.php/configuration/users', success, fail, data);
}

function removeUser(id, success, fail) {
	request("DELETE", 'r.php/configuration/users/'+id, success, fail);
}

function request(type, url, success, fail, data) {
	if (preRequestCallback) preRequestCallback();
	$.ajax({
		type: type,
		url: url,
		data: data,
		dataType: "json",
		success: function(result) {
			if (postRequestCallback) postRequestCallback();
			success(result.result);
		},
		error: function (xhr, desc, exc) {
			if (postRequestCallback) postRequestCallback();
			fail(xhr.responseText);
		}
	});
}