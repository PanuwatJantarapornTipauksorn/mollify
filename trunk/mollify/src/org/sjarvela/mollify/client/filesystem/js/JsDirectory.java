/**
 * Copyright (c) 2008- Samuli Järvelä
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html. If redistributing this code,
 * this entire header must remain intact.
 */

package org.sjarvela.mollify.client.filesystem.js;

import org.sjarvela.mollify.client.filesystem.Folder;

import com.google.gwt.core.client.JavaScriptObject;

public class JsDirectory extends JavaScriptObject {
	public static JsDirectory create(Folder directory) {
		return create(directory.getId(), directory.getName(), directory
				.getParentId());
	}

	public static JsDirectory create(String id, String name, String parentId) {
		JsDirectory result = JsDirectory.createObject().cast();
		result.putValues(id, name, parentId);
		return result;
	}

	protected JsDirectory() {
	}

	public final native String getId() /*-{
		return this.id;
	}-*/;

	public final native String getName() /*-{
		return this.name;
	}-*/;

	public final native String getParentId() /*-{
		return this.parent_id;
	}-*/;

	private final native void putValues(String id, String name, String parentId) /*-{
		this.id = id;
		this.name = name;
		this.parent_id = parentId;
	}-*/;

}
