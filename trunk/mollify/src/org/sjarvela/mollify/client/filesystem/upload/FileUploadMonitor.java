/**
 * Copyright (c) 2008- Samuli Järvelä
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html. If redistributing this code,
 * this entire header must remain intact.
 */
package org.sjarvela.mollify.client.filesystem.upload;

import org.sjarvela.mollify.client.filesystem.FileUploadStatus;
import org.sjarvela.mollify.client.service.FileUploadService;
import org.sjarvela.mollify.client.service.ServiceError;
import org.sjarvela.mollify.client.service.request.ResultListener;

import com.google.gwt.user.client.Timer;

public class FileUploadMonitor {
	static int INTERVAL = 1000;

	private final FileUploadService uploadHandler;
	private final Timer timer;
	private final String uploadId;
	private final FileUploadProgressListener listener;

	private boolean stop = false;

	public FileUploadMonitor(String uploadId,
			FileUploadProgressListener listener, FileUploadService uploadHandler) {

		this.uploadId = uploadId;
		this.listener = listener;
		this.uploadHandler = uploadHandler;

		timer = new Timer() {
			public void run() {
				if (!stop)
					onTimer();
			}
		};
	}

	public void start() {
		timer.schedule(INTERVAL);
	}

	public void stop() {
		stop = true;
		timer.cancel();
	}

	private void onTimer() {
		uploadHandler.getUploadProgress(uploadId,
				new ResultListener<FileUploadStatus>() {
					public void onFail(ServiceError error) {
						listener.onProgressUpdateFail(error);
					}

					public void onSuccess(FileUploadStatus result) {
						listener.onProgressUpdate(result);
						if (!stop)
							timer.schedule(INTERVAL);
					}
				});

	}
}