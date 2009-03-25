package org.sjarvela.mollify.client.ui.dialog;

import org.sjarvela.mollify.client.localization.TextProvider;
import org.sjarvela.mollify.client.session.PasswordHandler;
import org.sjarvela.mollify.client.ui.StyleConstants;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class PasswordDialog extends CenteredDialog {
	private final TextProvider textProvider;
	private final PasswordHandler passwordHandler;

	private PasswordTextBox originalPassword;
	private PasswordTextBox newPassword;
	private PasswordTextBox confirmNewPassword;

	public PasswordDialog(TextProvider textProvider,
			PasswordHandler passwordHandler) {
		super(textProvider.getStrings().passwordDialogTitle(),
				StyleConstants.PASSWORD_DIALOG);
		this.textProvider = textProvider;
		this.passwordHandler = passwordHandler;

		initialize();
	}

	@Override
	Widget createContent() {
		VerticalPanel panel = new VerticalPanel();
		panel.addStyleName(StyleConstants.PASSWORD_DIALOG_CONTENT);

		Label originalPasswordTitle = new Label(textProvider.getStrings()
				.passwordDialogOriginalPassword());
		originalPasswordTitle
				.setStyleName(StyleConstants.PASSWORD_ORIGINAL_PASSWORD_TITLE);
		panel.add(originalPasswordTitle);

		originalPassword = new PasswordTextBox();
		originalPassword
				.addStyleName(StyleConstants.PASSWORD_ORIGINAL_PASSWORD_VALUE);
		panel.add(originalPassword);

		Label newPasswordTitle = new Label(textProvider.getStrings()
				.passwordDialogNewPassword());
		newPasswordTitle
				.setStyleName(StyleConstants.PASSWORD_NEW_PASSWORD_TITLE);
		panel.add(newPasswordTitle);

		newPassword = new PasswordTextBox();
		newPassword.addStyleName(StyleConstants.PASSWORD_NEW_PASSWORD_VALUE);
		panel.add(newPassword);

		Label confirmNewPasswordTitle = new Label(textProvider.getStrings()
				.passwordDialogConfirmNewPassword());
		confirmNewPasswordTitle
				.setStyleName(StyleConstants.PASSWORD_CONFIRM_NEW_PASSWORD_TITLE);
		panel.add(confirmNewPasswordTitle);

		confirmNewPassword = new PasswordTextBox();
		confirmNewPassword
				.addStyleName(StyleConstants.PASSWORD_CONFIRM_NEW_PASSWORD_VALUE);
		panel.add(confirmNewPassword);

		return panel;
	}

	@Override
	Widget createButtons() {
		HorizontalPanel buttons = new HorizontalPanel();
		buttons.addStyleName(StyleConstants.PASSWORD_DIALOG_BUTTONS);
		buttons.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);

		buttons.add(createButton(textProvider.getStrings()
				.passwordDialogChangeButton(), new ClickListener() {

			public void onClick(Widget sender) {
				onRename();
			}
		}, StyleConstants.PASSWORD_DIALOG_BUTTON_CHANGE));

		buttons.add(createButton(
				textProvider.getStrings().dialogCancelButton(),
				new ClickListener() {

					public void onClick(Widget sender) {
						PasswordDialog.this.hide();
					}
				}, StyleConstants.DIALOG_BUTTON_CANCEL));

		return buttons;
	}

	private void onRename() {
		this.newPassword.removeStyleDependentName(StyleConstants.INVALID);
		this.confirmNewPassword
				.removeStyleDependentName(StyleConstants.INVALID);

		String oldPassword = this.originalPassword.getText();
		String newPassword = this.newPassword.getText();
		String confirmPassword = this.confirmNewPassword.getText();

		if (oldPassword.length() == 0 || newPassword.length() == 0
				|| confirmPassword.length() == 0) {
			return;
		}

		if (!newPassword.equals(confirmPassword)) {
			this.newPassword.addStyleDependentName(StyleConstants.INVALID);
			this.confirmNewPassword
					.addStyleDependentName(StyleConstants.INVALID);
			return;
		}
		this.hide();
		passwordHandler.changePassword(oldPassword, newPassword);
	}
}