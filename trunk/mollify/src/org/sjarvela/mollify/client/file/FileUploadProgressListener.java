/**
 * Copyright (c) 2008- Samuli Järvelä
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html. If redistributing this code,
 * this entire header must remain intact.
 */

package org.sjarvela.mollify.client.file;

import org.sjarvela.mollify.client.data.FileUploadStatus;
import org.sjarvela.mollify.client.service.MollifyError;

public interface FileUploadProgressListener {

	void onProgressUpdateFail(MollifyError error);

	void onProgressUpdate(FileUploadStatus status);

}
