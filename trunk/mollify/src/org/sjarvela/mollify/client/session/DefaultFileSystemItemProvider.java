/**
 * Copyright (c) 2008- Samuli Järvelä
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html. If redistributing this code,
 * this entire header must remain intact.
 */

package org.sjarvela.mollify.client.session;

import java.util.ArrayList;
import java.util.List;

import org.sjarvela.mollify.client.filesystem.FileSystemItemProvider;
import org.sjarvela.mollify.client.filesystem.js.JsFolder;
import org.sjarvela.mollify.client.filesystem.js.JsFolderInfo;
import org.sjarvela.mollify.client.service.FileSystemService;
import org.sjarvela.mollify.client.service.environment.ServiceEnvironment;
import org.sjarvela.mollify.client.service.request.listener.ResultListener;
import org.sjarvela.mollify.client.session.file.FilePermission;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class DefaultFileSystemItemProvider implements FileSystemItemProvider {
	private final FileSystemService fileSystemService;
	private List<JsFolder> roots = new ArrayList();

	@Inject
	public DefaultFileSystemItemProvider(SessionManager sessionManager,
			ServiceEnvironment env) {
		sessionManager.addSessionListener(new SessionListener() {
			public void onSessionStarted(SessionInfo session) {
				updateRootFolders(session);
			}

			public void onSessionEnded() {
				roots.clear();
			}
		});

		this.fileSystemService = env.getFileSystemService();
	}

	protected void updateRootFolders(SessionInfo session) {
		this.roots = new ArrayList();

		// TODO Map<String, VirtualGroupFolder> virtualRootsAdded = new
		// HashMap();
		// for (JsRootFolder f : session.getRootFolders()) {
		// if (f.hasGroup()) {
		// String name = f.getGroupParts().get(0);
		// if (!virtualRootsAdded.containsKey(name)) {
		// VirtualGroupFolder groupFolder = new VirtualGroupFolder(
		// name, name);
		// groupFolder.add(f);
		//
		// this.roots.add(groupFolder);
		// virtualRootsAdded.put(name, groupFolder);
		// } else {
		// virtualRootsAdded.get(name).add(f);
		// }
		// } else {
		// this.roots.add(f);
		// }
		// }
	}

	@Override
	public void getFolders(JsFolder parent,
			ResultListener<List<JsFolder>> listener) {
		if (parent.isEmpty())
			listener.onSuccess(roots);
		// else if (parent instanceof VirtualGroupFolder)
		// listener.onSuccess(((VirtualGroupFolder) parent).getChildren());
		else
			fileSystemService.getFolders(parent, listener);
	}

	@Override
	public List<JsFolder> getRootFolders() {
		return roots;
	}

	@Override
	public JsFolder getRootFolder(String id) {
		if (id == null)
			return null;

		for (JsFolder f : roots)
			if (id.equals(f.getId()))
				return f;
		return null;
	}

	@Override
	public void getFilesAndFolders(JsFolder parent,
			ResultListener<JsFolderInfo> listener) {
		if (parent.isEmpty())
			listener.onSuccess(JsFolderInfo.create(roots, null,
					FilePermission.None));
		// TODO else if (parent instanceof VirtualGroupFolder)
		// listener.onSuccess(new FolderInfo(FilePermission.None,
		// ((VirtualGroupFolder) parent).getChildren(),
		// Collections.EMPTY_LIST, null));
		else
			fileSystemService.getFolderInfo(parent, null, listener);
	}

}
