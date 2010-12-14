/**
	Copyright (c) 2008- Samuli J�rvel�

	All rights reserved. This program and the accompanying materials
	are made available under the terms of the Eclipse Public License v1.0
	which accompanies this distribution, and is available at
	http://www.eclipse.org/legal/epl-v10.html. If redistributing this code,
	this entire header must remain intact.
*/

function permissionModeFormatter(mode, options, obj) {
	switch (mode.toLowerCase()) {
		case 'a': return "Admin";
		case 'st': return "Staff";
		case 'no': return "Normal";
		default: return "-";
	}
}