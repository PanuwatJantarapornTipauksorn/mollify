/**
 * Copyright (c) 2008- Samuli Järvelä
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html. If redistributing this code,
 * this entire header must remain intact.
 */

package org.sjarvela.mollify.client.service.environment;

import org.sjarvela.mollify.client.service.ConfigurationService;
import org.sjarvela.mollify.client.service.ExternalService;
import org.sjarvela.mollify.client.service.FileSystemService;
import org.sjarvela.mollify.client.service.FileUploadService;
import org.sjarvela.mollify.client.service.SessionService;
import org.sjarvela.mollify.client.service.UrlResolver;
import org.sjarvela.mollify.client.service.request.ResponseProcessor;
import org.sjarvela.mollify.client.session.ClientSettings;

public interface ServiceEnvironment {

	void initialize(UrlResolver urlProvider, ClientSettings settings,
			ResponseProcessor responseProcessor);

	SessionService getSessionService();

	ConfigurationService getConfigurationService();

	FileSystemService getFileSystemService();

	FileUploadService getFileUploadService();

	ExternalService getExternalService();

	ExternalService getExternalService(String name);

	void setSessionId(String id);

}