/**
 * Copyright (c) 2008- Samuli Järvelä
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html. If redistributing this code,
 * this entire header must remain intact.
 */

package org.sjarvela.mollify.client.request.file;

import org.sjarvela.mollify.client.filesystem.Directory;
import org.sjarvela.mollify.client.request.ResultListener;

import com.google.gwt.user.client.ui.FormHandler;

public interface FileUploadHandler {
	FormHandler getUploadFormHandler(FileUploadController controller,
			String uploadId);

	void getUploadProgress(String id, ResultListener listener);

	String getFileUploadId();

	String getUploadUrl(Directory directory);

	void addListener(FileUploadListener listener);

}
