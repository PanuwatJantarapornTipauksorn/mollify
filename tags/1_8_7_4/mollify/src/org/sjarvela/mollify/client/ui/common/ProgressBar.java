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

import org.sjarvela.mollify.client.ui.StyleConstants;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ProgressBar extends Composite {
	private VerticalPanel panel;
	private Element current;
	private Element left;

	public ProgressBar(String... styles) {
		super();

		panel = new VerticalPanel();
		this.initWidget(panel);

		this.setStyleName(StyleConstants.PROGRESS_BAR);
		for (String style : styles)
			this.addStyleName(style);

		createElement();
		setProgress(0);
	}

	private void createElement() {
		Element row = DOM.createTR();
		row.setClassName(StyleConstants.PROGRESS_BAR_TOTAL);

		current = DOM.createTD();
		current.setClassName(StyleConstants.PROGRESS_BAR_CURRENT);
		current.setInnerHTML("&nbsp;");

		left = DOM.createTD();
		left.setClassName(StyleConstants.PROGRESS_BAR_LEFT);
		left.setInnerHTML("&nbsp;");

		DOM.appendChild(this.getElement(), row);
		DOM.appendChild(row, current);
		DOM.appendChild(row, left);
	}

	public void setProgress(double progress) {
		if (progress == 0d) {
			current.setAttribute("width", "0px");
			current.setAttribute("style", "display:none");
			left.setAttribute("width", "100%");
			return;
		}
		if (progress == 100d) {
			current.setAttribute("width", "100%");
			left.setAttribute("style", "display:none");
			left.setAttribute("width", "0px");
			return;
		}
		String currentWidth = String.valueOf((int) progress) + "%";
		String leftWidth = String.valueOf(100 - (int) progress) + "%";

		current.removeAttribute("style");
		current.setAttribute("width", currentWidth);
		left.removeAttribute("style");
		left.setAttribute("width", leftWidth);
	}

}
