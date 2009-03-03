/**
 * Copyright (c) 2008- Samuli Järvelä
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html. If redistributing this code,
 * this entire header must remain intact.
 */

package org.sjarvela.mollify.client.service;

import org.sjarvela.mollify.client.filesystem.Directory;
import org.sjarvela.mollify.client.filesystem.FileUploadStatus;
import org.sjarvela.mollify.client.filesystem.upload.FileUploadListener;
import org.sjarvela.mollify.client.service.request.ResultListener;

public interface FileUploadService {

	void getUploadProgress(String id, ResultListener<FileUploadStatus> listener);

	String getFileUploadId();

	String getUploadUrl(Directory directory);

	void handleResult(String resultString, FileUploadListener listener);
}
