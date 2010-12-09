package org.sjarvela.mollify.client.ui.dialog;

import org.sjarvela.mollify.client.ui.StyleConstants;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class CenteredDialog extends DialogBox {
	public CenteredDialog(String title, String... styles) {
		super(false, true);

		for (String style : styles) {
			this.addStyleName(style);
		}
		this.setText(title);
	}

	void initialize() {
		VerticalPanel content = new VerticalPanel();
		content.add(createContent());
		content.add(createButtons());
		this.add(content);

		this.setPopupPositionAndShow(new PositionCallback() {
			public void setPosition(int offsetWidth, int offsetHeight) {
				int left = ((Window.getClientWidth() - offsetWidth) / 2) >> 0;
				int top = ((Window.getClientHeight() - offsetHeight) / 2) >> 0;
				setPopupPosition(left, top);
			}
		});
	}

	abstract Widget createButtons();

	abstract Widget createContent();

	@Override
	public void show() {
		super.show();
		onShow();
	}

	void onShow() {
	}

	protected Widget createButton(String title, ClickListener listener,
			String... styles) {
		Button button = new Button(title);
		button.addStyleName(StyleConstants.DIALOG_BUTTON);
		for (String style : styles) {
			button.addStyleName(style);
		}
		button.addClickListener(listener);
		return button;
	}
}