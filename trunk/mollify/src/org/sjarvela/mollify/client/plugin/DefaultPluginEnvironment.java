/**
 * Copyright (c) 2008- Samuli Järvelä
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html. If redistributing this code,
 * this entire header must remain intact.
 */

package org.sjarvela.mollify.client.plugin;

import org.sjarvela.mollify.client.event.DefaultEventDispatcher;
import org.sjarvela.mollify.client.event.EventDispatcher;
import org.sjarvela.mollify.client.plugin.itemdetails.NativeItemDetailsProvider;
import org.sjarvela.mollify.client.plugin.response.NativeResponseProcessor;
import org.sjarvela.mollify.client.service.request.ResponseInterceptor;
import org.sjarvela.mollify.client.ui.fileitemcontext.ItemDetailsHandler;
import org.sjarvela.mollify.client.ui.fileitemcontext.ItemDetailsProvider;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class DefaultPluginEnvironment implements PluginEnvironment {
	private final EventDispatcher eventDispatcher;
	private final ResponseInterceptor responseInterceptor;
	private final ItemDetailsProvider itemDetailsProvider;

	@Inject
	public DefaultPluginEnvironment(EventDispatcher eventDispatcher,
			ResponseInterceptor responseInterceptor,
			ItemDetailsProvider itemDetailsProvider) {
		this.eventDispatcher = eventDispatcher;
		this.responseInterceptor = responseInterceptor;
		this.itemDetailsProvider = itemDetailsProvider;
	}

	public void addResponseProcessor(JavaScriptObject rp) {
		responseInterceptor.addProcessor(new NativeResponseProcessor(rp));
	}

	public void addEventHandler(JavaScriptObject eh) {
		// TODO use proper interface here instead of casting
		((DefaultEventDispatcher) eventDispatcher).addEventHandler(eh);
	}

	public void addItemDetailsProvider(JavaScriptObject dp) {
		((ItemDetailsHandler) itemDetailsProvider)
				.addItemDetailsProvider(new NativeItemDetailsProvider(dp));
	}

	public JavaScriptObject getJsEnv() {
		return createNativeEnv(this);
	}

	private native JavaScriptObject createNativeEnv(DefaultPluginEnvironment e) /*-{
		var env = {};
		env.addResponseProcessor = function (cb) {
			e.@org.sjarvela.mollify.client.plugin.DefaultPluginEnvironment::addResponseProcessor(Lcom/google/gwt/core/client/JavaScriptObject;)(cb);
		}
		env.addEventHandler = function (cb) {
			e.@org.sjarvela.mollify.client.plugin.DefaultPluginEnvironment::addEventHandler(Lcom/google/gwt/core/client/JavaScriptObject;)(cb);
		}
		env.addItemDetailsProvider = function (cb) {
			e.@org.sjarvela.mollify.client.plugin.DefaultPluginEnvironment::addItemDetailsProvider(Lcom/google/gwt/core/client/JavaScriptObject;)(cb);
		}
		return env;
	}-*/;

}
