/*
	Copyright (c) 2008- Samuli Järvelä

	All rights reserved. This program and the accompanying materials
	are made available under the terms of the Eclipse Public License v1.0
	which accompanies this distribution, and is available at
	http://www.eclipse.org/legal/epl-v10.html. If redistributing this code,
	this entire header must remain intact.
*/

function init_page_update() {
	set_title("Database Update");
	check_authentication();
}

function check_authentication() {
	empty_errors();
	$("#db-footer").html("<div class='progress'>Checking authentication...</div>");
	
	request("mysql/update.php", "action=check_auth",
		function(result) {
			$("#db-footer").empty();
			
			if (!result.success) on_auth_error(result.error);
			else start_update();
		}
	);
}

function on_auth_error(error) {
	$("#db-content").load("mysql/page_update_auth.html", function() {
		set_effects();
		show_error(error);
		$("#proceed.button").click(function() { check_authentication(); });
	});
}

function on_update_error(error) {
	show_error(error);
}

function start_update() {
	empty_errors();
	$("#db-footer").html("<div class='progress'>Getting update info...</div>");
	
	request("mysql/update.php", "action=update_info",
		function(result) {
			$("#db-footer").empty();
			
			if (!result.success) {
				on_update_error(result.error);
			} else {
				$("#db-content").load("mysql/page_update_info.html", function() {
					set_effects();
					$("#update.button").click(update);					
					$("#db_from").html(result.result.installed);
					$("#db_to").html(result.result.current);
				});
			}
		}
	);
}

function update() {
	empty_errors();
	$("#db-footer").html("<div class='progress'>Updating...</div>");
	
	request("mysql/update.php", "action=update",
		function(result) {
			$("#db-footer").empty();
			
			if (!result.success) on_update_error(result.error);
			else $("#db-content").load("mysql/page_update_success.html");
		}
	);
}