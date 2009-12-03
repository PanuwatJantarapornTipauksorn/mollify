/**
 * Copyright (c) 2008- Samuli Järvelä
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html. If redistributing this code,
 * this entire header must remain intact.
 */

package org.sjarvela.mollify.client.ui.configuration.folders;

import java.util.Arrays;
import java.util.List;

import org.sjarvela.mollify.client.filesystem.FolderInfo;
import org.sjarvela.mollify.client.localization.TextProvider;
import org.sjarvela.mollify.client.ui.StyleConstants;
import org.sjarvela.mollify.client.ui.common.grid.DefaultGridColumn;
import org.sjarvela.mollify.client.ui.common.grid.Grid;
import org.sjarvela.mollify.client.ui.common.grid.GridColumn;
import org.sjarvela.mollify.client.ui.common.grid.GridData;
import org.sjarvela.mollify.client.ui.common.grid.GridDataProvider;

public class DirectoryInfoList extends Grid<FolderInfo> implements
		GridDataProvider<FolderInfo> {
	public static GridColumn COLUMN_NAME;
	public static GridColumn COLUMN_LOCATION;
	public static List<GridColumn> ALL_COLUMNS = null;

	public DirectoryInfoList(TextProvider textProvider, String style) {
		super(StyleConstants.FOLDER_LIST_HEADER, getColumns(textProvider));

		this.setStylePrimaryName(StyleConstants.FOLDER_LIST);
		if (style != null)
			this.addStyleDependentName(style);
		this.setDataProvider(this);
	}

	private static List<GridColumn> getColumns(TextProvider textProvider) {
		if (ALL_COLUMNS == null) {
			COLUMN_NAME = new DefaultGridColumn("name", textProvider
					.getStrings().folderListColumnTitleName(), false);
			COLUMN_LOCATION = new DefaultGridColumn("path", textProvider
					.getStrings().folderListColumnTitleLocation(), false);

			ALL_COLUMNS = Arrays.asList((GridColumn) COLUMN_NAME,
					(GridColumn) COLUMN_LOCATION);
		}

		return ALL_COLUMNS;
	}

	public String getColumnStyle(GridColumn column) {
		return StyleConstants.FOLDER_LIST_COLUMN_PREFIX + column.getId();
	}

	public GridData getData(FolderInfo directory, GridColumn column) {
		if (column.equals(DirectoryInfoList.COLUMN_NAME))
			return new GridData.Text(directory.getName());
		else if (column.equals(DirectoryInfoList.COLUMN_LOCATION))
			return new GridData.Text(directory.getPath());
		return new GridData.Text("");
	}

	private static final List<String> ROW_STYLE = Arrays
			.asList(StyleConstants.FOLDER_LIST_ROW);

	public List<String> getRowStyles(FolderInfo t) {
		return ROW_STYLE;
	}
}
