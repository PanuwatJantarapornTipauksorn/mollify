/**
 * Copyright (c) 2008- Samuli Järvelä
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html. If redistributing this code,
 * this entire header must remain intact.
 */

package org.sjarvela.mollify.client.ui.common;

import org.sjarvela.mollify.client.ResourceId;
import org.sjarvela.mollify.client.ui.action.ActionListener;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Label;

public class ActionToggleButton extends Label {
	boolean down = false;

	public ActionToggleButton(String title, String id) {
		this(title, id, null);
	}

	public ActionToggleButton(String title, String id, String styleClass) {
		super(title);

		if (styleClass != null)
			setStylePrimaryName(styleClass);

		if (id != null)
			getElement().setId(id);

		updateStyle();
	}

	private void updateStyle() {
		if (down)
			this.addStyleDependentName("down");
		else
			this.removeStyleDependentName("down");
	}

	public void setAction(final ActionListener actionListener,
			final ResourceId actionId) {

		this.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				ActionToggleButton.this.down = !ActionToggleButton.this.down;
				ActionToggleButton.this.updateStyle();
				actionListener.onAction(actionId, null);
			}
		});
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean b) {
		down = b;
		updateStyle();
	}
}
