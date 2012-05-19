/**
 * Copyright (c) 2008- Samuli Järvelä
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html. If redistributing this code,
 * this entire header must remain intact.
 */

package org.sjarvela.mollify.client.ui.searchresult.impl;

import java.util.List;

import org.sjarvela.mollify.client.Callback;
import org.sjarvela.mollify.client.ResourceId;
import org.sjarvela.mollify.client.filesystem.FileSystemAction;
import org.sjarvela.mollify.client.filesystem.FileSystemItem;
import org.sjarvela.mollify.client.filesystem.Folder;
import org.sjarvela.mollify.client.filesystem.SearchResult;
import org.sjarvela.mollify.client.filesystem.VirtualGroupFolder;
import org.sjarvela.mollify.client.filesystem.handler.FileSystemActionHandler;
import org.sjarvela.mollify.client.localization.TextProvider;
import org.sjarvela.mollify.client.localization.Texts;
import org.sjarvela.mollify.client.ui.StyleConstants;
import org.sjarvela.mollify.client.ui.action.ActionListener;
import org.sjarvela.mollify.client.ui.common.dialog.ResizableDialog;
import org.sjarvela.mollify.client.ui.common.grid.GridComparator;
import org.sjarvela.mollify.client.ui.common.grid.GridListener;
import org.sjarvela.mollify.client.ui.common.grid.SortOrder;
import org.sjarvela.mollify.client.ui.common.popup.DropdownButton;
import org.sjarvela.mollify.client.ui.dropbox.DropBox;
import org.sjarvela.mollify.client.ui.fileitemcontext.popup.ContextPopupHandler;
import org.sjarvela.mollify.client.ui.fileitemcontext.popup.DefaultItemContextPopupFactory;
import org.sjarvela.mollify.client.ui.fileitemcontext.popup.ItemContextPopup;
import org.sjarvela.mollify.client.ui.formatter.PathFormatter;
import org.sjarvela.mollify.client.ui.mainview.impl.DefaultMainView.Action;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public class SearchResultDialog extends ResizableDialog implements
		ActionListener {
	private final TextProvider textProvider;
	private final String criteria;
	private final SearchResult result;
	private final FileSystemActionHandler fileSystemActionHandler;
	private final ContextPopupHandler<FileSystemItem> itemContextHandler;
	private final DropBox dropBox;

	private final SearchResultFileList list;
	private final DropdownButton selectOptionsButton;
	private final DropdownButton fileActions;
	private final ItemContextPopup itemContextPopup;

	private FlowPanel listPanel;

	public SearchResultDialog(TextProvider textProvider, String criteria,
			SearchResult result, PathFormatter formatter,
			DefaultItemContextPopupFactory itemContextPopupFactory,
			FileSystemActionHandler fileSystemActionHandler, DropBox dropBox) {
		super(textProvider.getText(Texts.searchResultsDialogTitle),
				"search-results", true);
		this.result = result;

		this.textProvider = textProvider;
		this.criteria = criteria;
		this.fileSystemActionHandler = fileSystemActionHandler;
		this.dropBox = dropBox;

		this.itemContextPopup = itemContextPopupFactory.createPopup(dropBox);
		this.itemContextHandler = new ContextPopupHandler<FileSystemItem>(
				itemContextPopup);

		this.list = new SearchResultFileList(textProvider, formatter);
		this.list.setResults(result);
		this.list.addListener(new GridListener<FileSystemItem>() {
			@Override
			public void onColumnClicked(FileSystemItem item, String columnId,
					Element e) {
				itemContextHandler.onItemSelected(item, e);
			}

			@Override
			public void onIconClicked(FileSystemItem item, Element e) {
				itemContextHandler.onItemSelected(item, e);
			}

			@Override
			public void onMenuClicked(FileSystemItem item, Element e) {
				if (item.equals(Folder.Parent)
						|| (item instanceof VirtualGroupFolder))
					return;
				itemContextHandler.onOpenItemMenu(item, e);
			}

			@Override
			public void onColumnSorted(String columnId, SortOrder sort) {
				list.setComparator(createComparator(columnId, sort));
			}

			@Override
			public void onSelectionChanged(List<FileSystemItem> selected) {
				fileActions.setEnabled(selected.size() > 0);
			}

			@Override
			public void onRendered() {
			}
		});

		this.itemContextPopup.setActionHandler(fileSystemActionHandler);

		selectOptionsButton = new DropdownButton(this,
				textProvider.getText(Texts.mainViewSelectButton),
				"mollify-search-result-select-options");
		selectOptionsButton.addAction(Action.selectAll,
				textProvider.getText(Texts.mainViewSelectAll));
		selectOptionsButton.addAction(Action.selectNone,
				textProvider.getText(Texts.mainViewSelectNone));

		fileActions = new DropdownButton(this,
				textProvider.getText(Texts.mainViewSelectActions),
				"mollify-search-result-actions");
		fileActions.addAction(Action.addToDropbox,
				textProvider.getText(Texts.mainViewSelectActionAddToDropbox));
		fileActions.addSeparator();
		fileActions.addAction(Action.copyMultiple,
				textProvider.getText(Texts.fileActionCopyTitle));
		fileActions.addAction(Action.moveMultiple,
				textProvider.getText(Texts.fileActionMoveTitle));
		fileActions.addAction(Action.deleteMultiple,
				textProvider.getText(Texts.fileActionDeleteTitle));
		fileActions.setEnabled(false);

		this.setMinimumSize(500, 300);
		initialize();
	}

	protected GridComparator<FileSystemItem> createComparator(String columnId,
			SortOrder sort) {
		return new SearchResultsComparator(columnId, sort);
	}

	@Override
	protected Widget createContent() {
		Panel panel = new FlowPanel();
		panel.setStylePrimaryName(StyleConstants.SEARCH_RESULTS_DIALOG_CONTENT);

		panel.add(createInfoPanel());

		listPanel = new FlowPanel();
		listPanel
				.setStylePrimaryName(StyleConstants.SEARCH_RESULTS_DIALOG_LIST);
		listPanel.add(list);

		panel.add(listPanel);
		return panel;
	}

	@Override
	protected com.google.gwt.user.client.Element getSizedElement() {
		return listPanel.getElement();
	}

	private Widget createInfoPanel() {
		Panel p = new FlowPanel();
		p.setStylePrimaryName(StyleConstants.SEARCH_RESULTS_DIALOG_INFO);
		Label info = new Label(textProvider.getText(Texts.searchResultsInfo,
				criteria, String.valueOf(result.getMatchCount())));
		info.setStylePrimaryName(StyleConstants.SEARCH_RESULTS_DIALOG_INFO_TEXT);
		p.add(info);
		return p;
	}

	@Override
	protected Widget createButtons() {
		FlowPanel buttons = new FlowPanel();
		buttons.addStyleName(StyleConstants.SEARCH_RESULTS_DIALOG_BUTTONS);

		buttons.add(selectOptionsButton);
		buttons.add(fileActions);
		buttons.add(createButton(textProvider.getText(Texts.dialogCloseButton),
				new ClickHandler() {
					public void onClick(ClickEvent event) {
						SearchResultDialog.this.hide();
					}
				}, "search-results"));

		return buttons;
	}

	@Override
	public void onAction(ResourceId action, Object o) {
		if (Action.selectAll.equals(action)) {
			list.selectAll();
			return;
		}
		if (Action.selectNone.equals(action)) {
			list.selectNone();
			return;
		}

		// file actions
		List<FileSystemItem> selected = list.getSelected();
		if (selected.isEmpty())
			return;

		if (Action.copyMultiple.equals(action)) {
			fileSystemActionHandler.onAction(selected, FileSystemAction.copy,
					null, null, new Callback() {
						@Override
						public void onCallback() {
							list.selectNone();
						}
					});

		}
		if (Action.moveMultiple.equals(action)) {
			fileSystemActionHandler.onAction(selected, FileSystemAction.move,
					null, null, new Callback() {
						@Override
						public void onCallback() {
							list.selectNone();
						}
					});
		}
		if (Action.deleteMultiple.equals(action)) {
			fileSystemActionHandler.onAction(selected, FileSystemAction.delete,
					null, null, new Callback() {
						@Override
						public void onCallback() {
							list.selectNone();
						}
					});
		}
		if (Action.addToDropbox.equals(action)) {
			dropBox.addItems(selected);
			list.selectNone();
		}
	}
}
