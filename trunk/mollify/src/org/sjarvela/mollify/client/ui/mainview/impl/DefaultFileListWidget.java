/**
 * Copyright (c) 2008- Samuli Järvelä
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html. If redistributing this code,
 * this entire header must remain intact.
 */

package org.sjarvela.mollify.client.ui.mainview.impl;

import java.util.ArrayList;
import java.util.List;

import org.sjarvela.mollify.client.filesystem.FileSystemItem;
import org.sjarvela.mollify.client.js.JsObj;
import org.sjarvela.mollify.client.localization.TextProvider;
import org.sjarvela.mollify.client.localization.Texts;
import org.sjarvela.mollify.client.ui.common.grid.DefaultGridColumn;
import org.sjarvela.mollify.client.ui.common.grid.GridColumn;
import org.sjarvela.mollify.client.ui.common.grid.GridComparator;
import org.sjarvela.mollify.client.ui.common.grid.GridListener;
import org.sjarvela.mollify.client.ui.common.grid.SelectController;
import org.sjarvela.mollify.client.ui.common.grid.SelectionMode;
import org.sjarvela.mollify.client.ui.dnd.DragAndDropManager;
import org.sjarvela.mollify.client.ui.filelist.FileList;

import com.google.gwt.user.client.ui.Widget;

public class DefaultFileListWidget implements FileListWidget {
	private final FileList list;

	public DefaultFileListWidget(TextProvider textProvider,
			DragAndDropManager dragAndDropManager, final JsObj columnSetup) {
		this.list = new FileList(textProvider, dragAndDropManager) {
			protected java.util.List<org.sjarvela.mollify.client.ui.common.grid.GridColumn> getColumns() {
				if (columnSetup == null || columnSetup.getKeys().size() == 0)
					return super.getColumns();

				List<GridColumn> c = new ArrayList();
				for (String id : columnSetup.getKeys()) {
					JsObj col = columnSetup.getJsObj(id);
					String titleKey = col.getString("title");

					GridColumn column = null;
					if (COLUMN_ID_NAME.equals(id))
						column = new DefaultGridColumn(COLUMN_ID_NAME,
								textProvider
										.getText(titleKey != null ? titleKey
												: Texts.fileListColumnTitleName
														.name()),
								col.hasValue("sortable") ? col
										.getBoolean("sortable") : true);
					else if (COLUMN_ID_TYPE.equals(id))
						column = new DefaultGridColumn(COLUMN_ID_TYPE,
								textProvider
										.getText(titleKey != null ? titleKey
												: Texts.fileListColumnTitleType
														.name()),
								col.hasValue("sortable") ? col
										.getBoolean("sortable") : true);
					else if (COLUMN_ID_SIZE.equals(id))
						column = new DefaultGridColumn(COLUMN_ID_SIZE,
								textProvider
										.getText(titleKey != null ? titleKey
												: Texts.fileListColumnTitleSize
														.name()),
								col.hasValue("sortable") ? col
										.getBoolean("sortable") : true);
					else {
						// TODO custom
					}
					if (column != null)
						c.add(column);
				}
				if (c.isEmpty())
					throw new RuntimeException("Column setup empty");
				return c;
			};
		};
	}

	@Override
	public Widget getWidget() {
		return list;
	}

	@Override
	public void refresh() {
		list.refresh();
	}

	@Override
	public void removeAllRows() {
		list.removeAllRows();
	}

	@Override
	public void setSelectionMode(SelectionMode selectionMode) {
		list.setSelectionMode(selectionMode);
	}

	@Override
	public void setSelectController(SelectController controller) {
		list.setSelectController(controller);
	}

	@Override
	public void selectAll() {
		list.selectAll();
	}

	@Override
	public void selectNone() {
		list.selectNone();
	}

	@Override
	public void addListener(GridListener listener) {
		list.addListener(listener);
	}

	@Override
	public void setComparator(GridComparator<FileSystemItem> comparator) {
		list.setComparator(comparator);
	}

	@Override
	public void setContent(List<FileSystemItem> items) {
		list.setContent(items);
	}
}