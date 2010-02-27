/**
 * Copyright (c) 2008- Samuli Järvelä
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html. If redistributing this code,
 * this entire header must remain intact.
 */

package org.sjarvela.mollify.client.ui.dropbox;

import org.sjarvela.mollify.client.ui.common.Coords;

public interface DropBox {

	public boolean isVisible();

	public void toggle();

	public void setPosition(Coords position);

}
