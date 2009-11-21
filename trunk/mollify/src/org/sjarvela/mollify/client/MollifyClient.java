/**
 * Copyright (c) 2008- Samuli Järvelä
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html. If redistributing this code,
 * this entire header must remain intact.
 */

package org.sjarvela.mollify.client;

import org.sjarvela.mollify.client.service.ServiceError;
import org.sjarvela.mollify.client.service.ServiceProvider;
import org.sjarvela.mollify.client.service.request.listener.ResultListener;
import org.sjarvela.mollify.client.session.SessionInfo;
import org.sjarvela.mollify.client.ui.ViewManager;
import org.sjarvela.mollify.client.ui.login.UiSessionManager;
import org.sjarvela.mollify.client.ui.mainview.MainViewFactory;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.GWT;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class MollifyClient implements Client {
	public static final String PROTOCOL_VERSION = "1_0_0";

	private final ViewManager viewManager;
	private final UiSessionManager sessionManager;
	private final MainViewFactory mainViewFactory;
	private final ServiceProvider serviceProvider;

	@Inject
	public MollifyClient(ViewManager viewManager,
			ServiceProvider serviceProvider, UiSessionManager sessionManager,
			MainViewFactory mainViewFactory) {
		this.viewManager = viewManager;
		this.serviceProvider = serviceProvider;
		this.sessionManager = sessionManager;
		this.mainViewFactory = mainViewFactory;
	}

	public void start() {
		Log.info("Starting Mollify, protocol version " + PROTOCOL_VERSION);
		Log.debug("Host page location: " + GWT.getHostPageBaseURL());
		Log.debug("Module name: " + GWT.getModuleName());
		Log.debug("Module location: " + GWT.getModuleBaseURL());

		serviceProvider.getSessionService().getSessionInfo(PROTOCOL_VERSION,
				new ResultListener<SessionInfo>() {
					public void onFail(ServiceError error) {
						viewManager.showServiceError(error.getError()
								.getError(), error);
					}

					public void onSuccess(SessionInfo session) {
						start(session);
					}
				});
	};

	private void start(SessionInfo session) {
		sessionManager.start(session, new Callback() {
			public void onCallback() {
				showMain();
			}
		}, new Callback() {
			public void onCallback() {
				viewManager.empty();
				start();
			}
		});
	}

	private void showMain() {
		viewManager.openView(mainViewFactory.createMainView(sessionManager)
				.getViewWidget());
	}
}
