/**
 * Copyright (c) 2008- Samuli Järvelä
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html. If redistributing this code,
 * this entire header must remain intact.
 */

package org.sjarvela.mollify.client.ui.popup.filecontext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sjarvela.mollify.client.ResourceId;
import org.sjarvela.mollify.client.filesystem.FileDetails;
import org.sjarvela.mollify.client.filesystem.FileSystemAction;
import org.sjarvela.mollify.client.localization.TextProvider;
import org.sjarvela.mollify.client.ui.ActionListener;
import org.sjarvela.mollify.client.ui.StyleConstants;
import org.sjarvela.mollify.client.ui.common.ActionLink;
import org.sjarvela.mollify.client.ui.common.EditableLabel;
import org.sjarvela.mollify.client.ui.common.MultiActionButton;
import org.sjarvela.mollify.client.ui.common.SwitchPanel;
import org.sjarvela.mollify.client.ui.popup.ContextPopup;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class FileContextPopupComponent extends ContextPopup {
	private final DateTimeFormat dateTimeFormat;
	private final TextProvider textProvider;
	private final ActionListener actionListener;

	private final boolean hasGeneralWritePermissions;
	private final boolean descriptionEditingEnabled;
	private final boolean zipDownloadEnabled;

	private Label filename;

	private EditableLabel description;
	private SwitchPanel<DescriptionActionGroup> descriptionActionsSwitch;

	private ActionLink editDescription;
	private ActionLink addDescription;
	private ActionLink applyDescription;
	private ActionLink cancelEditDescription;
	private ActionLink removeDescription;

	private DisclosurePanel details;
	private List<Label> detailRowValues = new ArrayList<Label>();

	private Button renameButton;
	private Button copyButton;
	private Button moveButton;
	private Button deleteButton;

	private enum Details {
		Accessed, Modified, Changed
	}

	public enum Action implements ResourceId {
		addDescription, editDescription, removeDescription, cancelEditDescription, applyDescription
	}

	public enum DescriptionActionGroup implements ResourceId {
		view, edit
	}

	public FileContextPopupComponent(TextProvider textProvider,
			boolean generalWritePermissions, boolean descriptionEditingEnabled,
			boolean zipDownloadEnabled, ActionListener actionListener) {
		super(StyleConstants.FILE_CONTEXT);

		this.hasGeneralWritePermissions = generalWritePermissions;
		this.descriptionEditingEnabled = descriptionEditingEnabled;
		this.zipDownloadEnabled = zipDownloadEnabled;
		this.dateTimeFormat = com.google.gwt.i18n.client.DateTimeFormat
				.getFormat(textProvider.getStrings().shortDateTimeFormat());

		this.textProvider = textProvider;
		this.actionListener = actionListener;

		initialize();
	}

	protected Widget createContent() {
		Panel content = new VerticalPanel();
		content.setStyleName(StyleConstants.FILE_CONTEXT_CONTENT);

		filename = new Label();
		filename.setStyleName(StyleConstants.FILE_CONTEXT_FILENAME);
		content.add(filename);

		description = new EditableLabel(StyleConstants.FILE_CONTEXT_DESCRIPTION);
		content.add(description);

		if (descriptionEditingEnabled)
			createDescriptionActions(content);

		content.add(createDetails());
		content.add(createButtons());
		return content;
	}

	private void createDescriptionActions(Panel content) {
		addDescription = new ActionLink(textProvider.getStrings()
				.fileDetailsAddDescription(),
				StyleConstants.FILE_CONTEXT_ADD_DESCRIPTION,
				StyleConstants.FILE_CONTEXT_DESCRIPTION_ACTION);
		addDescription.setAction(actionListener, Action.addDescription);

		removeDescription = new ActionLink(textProvider.getStrings()
				.fileDetailsRemoveDescription(),
				StyleConstants.FILE_CONTEXT_REMOVE_DESCRIPTION,
				StyleConstants.FILE_CONTEXT_DESCRIPTION_ACTION);
		removeDescription.setAction(actionListener, Action.removeDescription);

		editDescription = new ActionLink(textProvider.getStrings()
				.fileDetailsEditDescription(),
				StyleConstants.FILE_CONTEXT_EDIT_DESCRIPTION,
				StyleConstants.FILE_CONTEXT_DESCRIPTION_ACTION);
		editDescription.setAction(actionListener, Action.editDescription);

		applyDescription = new ActionLink(textProvider.getStrings()
				.fileDetailsApplyDescription(),
				StyleConstants.FILE_CONTEXT_APPLY_DESCRIPTION,
				StyleConstants.FILE_CONTEXT_DESCRIPTION_ACTION);
		applyDescription.setAction(actionListener, Action.applyDescription);

		cancelEditDescription = new ActionLink(textProvider.getStrings()
				.fileDetailsCancelEditDescription(),
				StyleConstants.FILE_CONTEXT_CANCEL_EDIT_DESCRIPTION,
				StyleConstants.FILE_CONTEXT_DESCRIPTION_ACTION);
		cancelEditDescription.setAction(actionListener,
				Action.cancelEditDescription);

		Map<DescriptionActionGroup, Widget> groups = new HashMap();
		Panel descriptionActionsView = new FlowPanel();
		descriptionActionsView
				.setStyleName(StyleConstants.FILE_CONTEXT_DESCRIPTION_ACTIONS);
		descriptionActionsView.add(addDescription);
		descriptionActionsView.add(editDescription);
		descriptionActionsView.add(removeDescription);
		groups.put(DescriptionActionGroup.view, descriptionActionsView);

		Panel descriptionActionsEdit = new FlowPanel();
		descriptionActionsEdit
				.setStyleName(StyleConstants.FILE_CONTEXT_DESCRIPTION_ACTIONS);

		descriptionActionsEdit.add(applyDescription);
		descriptionActionsEdit.add(cancelEditDescription);
		groups.put(DescriptionActionGroup.edit, descriptionActionsEdit);

		descriptionActionsSwitch = new SwitchPanel(
				StyleConstants.FILE_CONTEXT_DESCRIPTION_ACTIONS_SWITCH, groups);

		content.add(descriptionActionsSwitch);
	}

	private Widget createButtons() {
		Panel buttons = new HorizontalPanel();
		buttons.setStyleName(StyleConstants.FILE_CONTEXT_BUTTONS);

		renameButton = createActionButton(textProvider.getStrings()
				.fileActionRenameTitle(), actionListener,
				FileSystemAction.rename);
		renameButton.setVisible(false);

		copyButton = createActionButton(textProvider.getStrings()
				.fileActionCopyTitle(), actionListener, FileSystemAction.copy);
		copyButton.setVisible(hasGeneralWritePermissions);

		moveButton = createActionButton(textProvider.getStrings()
				.fileActionMoveTitle(), actionListener, FileSystemAction.move);
		moveButton.setVisible(false);

		deleteButton = createActionButton(textProvider.getStrings()
				.fileActionDeleteTitle(), actionListener,
				FileSystemAction.delete);
		deleteButton.setVisible(false);

		if (this.zipDownloadEnabled) {
			MultiActionButton downloadButton = createMultiActionButton(
					actionListener, textProvider.getStrings()
							.fileActionDownloadTitle(),
					FileSystemAction.download.name());
			downloadButton.addAction(FileSystemAction.download, textProvider
					.getStrings().fileActionDownloadTitle());
			downloadButton.addAction(FileSystemAction.download_as_zip,
					textProvider.getStrings().fileActionDownloadZippedTitle());
			downloadButton.setDefaultAction(FileSystemAction.download);
			buttons.add(downloadButton);
		} else {
			buttons.add(createActionButton(textProvider.getStrings()
					.fileActionDownloadTitle(), actionListener,
					FileSystemAction.download));
		}

		buttons.add(renameButton);
		buttons.add(copyButton);
		buttons.add(moveButton);
		buttons.add(deleteButton);

		return buttons;
	}

	private Widget createDetails() {
		details = new DisclosurePanel(textProvider.getStrings()
				.fileActionDetailsTitle(), false);
		details.addStyleName(StyleConstants.FILE_CONTEXT_DETAILS);
		details.getHeader().getElement().getParentElement().setClassName(
				StyleConstants.FILE_CONTEXT_DETAILS_HEADER);

		Panel content = new VerticalPanel();
		content.setStyleName(StyleConstants.FILE_CONTEXT_DETAILS_CONTENT);

		for (Details detail : Details.values()) {
			String title = "";

			if (detail.equals(Details.Accessed))
				title = textProvider.getStrings()
						.fileDetailsLabelLastAccessed();
			else if (detail.equals(Details.Modified))
				title = textProvider.getStrings()
						.fileDetailsLabelLastModified();
			else if (detail.equals(Details.Changed))
				title = textProvider.getStrings().fileDetailsLabelLastChanged();

			content.add(createDetailsRow(title, detail.name().toLowerCase()));
		}

		details.setContent(content);
		return details;
	}

	private Widget createDetailsRow(String labelText, String style) {
		HorizontalPanel detailsRow = new HorizontalPanel();
		detailsRow.setStyleName(StyleConstants.FILE_CONTEXT_DETAILS_ROW);

		Label label = new Label(labelText);
		label
				.setStylePrimaryName(StyleConstants.FILE_CONTEXT_DETAILS_ROW_LABEL);
		label.addStyleDependentName(style);
		detailsRow.add(label);

		Label value = new Label();
		label
				.setStylePrimaryName(StyleConstants.FILE_CONTEXT_DETAILS_ROW_VALUE);
		label.addStyleDependentName(style);
		detailsRow.add(value);

		detailRowValues.add(value);

		return detailsRow;
	}

	private void emptyDetails() {
		description.setText("");

		for (Details detail : Details.values())
			detailRowValues.get(detail.ordinal()).setText("");

		renameButton.setVisible(false);
		deleteButton.setVisible(false);
		moveButton.setVisible(false);
	}

	public void updateDetails(FileDetails details) {
		if (details == null) {
			emptyDetails();
			return;
		}

		for (Details detail : Details.values()) {
			Label value = detailRowValues.get(detail.ordinal());

			if (detail.equals(Details.Accessed))
				value.setText(dateTimeFormat.format(details.getLastAccessed()));
			else if (detail.equals(Details.Modified))
				value.setText(dateTimeFormat.format(details.getLastModified()));
			else if (detail.equals(Details.Changed))
				value.setText(dateTimeFormat.format(details.getLastChanged()));
		}
	}

	public void updateButtons(boolean isWritable) {
		renameButton.setVisible(isWritable);
		deleteButton.setVisible(isWritable);
		moveButton.setVisible(isWritable && hasGeneralWritePermissions);
	}

	public void setDescription(String description) {
		this.description.setText(description);
	}

	public void setDescriptionEditable(boolean isEditable,
			boolean descriptionDefined) {
		description.setEditable(isEditable);
		if (!descriptionEditingEnabled)
			return;

		addDescription.setVisible(!isEditable && !descriptionDefined);
		editDescription.setVisible(!isEditable && descriptionDefined);
		removeDescription.setVisible(!isEditable && descriptionDefined);
		applyDescription.setVisible(isEditable);
		cancelEditDescription.setVisible(isEditable);

		if (isEditable)
			descriptionActionsSwitch.switchTo(DescriptionActionGroup.edit);
		else
			descriptionActionsSwitch.switchTo(DescriptionActionGroup.view);
	}

	public DisclosurePanel getDetails() {
		return details;
	}

	public Label getFilename() {
		return filename;
	}

	public EditableLabel getDescription() {
		return description;
	}

	public ActionLink getEditDescription() {
		return editDescription;
	}

	public ActionLink getAddDescription() {
		return addDescription;
	}

	public ActionLink getApplyDescription() {
		return applyDescription;
	}

	public ActionLink getCancelEditDescription() {
		return cancelEditDescription;
	}

	public ActionLink getRemoveDescription() {
		return removeDescription;
	}
}
