package org.sjarvela.mollify.client.ui.mainview;

import org.sjarvela.mollify.client.file.FileActionHandler;
import org.sjarvela.mollify.client.file.FileDetailsProvider;
import org.sjarvela.mollify.client.localization.Localizator;
import org.sjarvela.mollify.client.ui.fileaction.FileDetailsPopup;

public class FileDetailsPopupFactory {
	public FileDetailsPopupFactory(FileActionHandler fileActionHandler,
			FileDetailsProvider fileDetailsProvider, Localizator localizator) {
		this.fileActionHandler = fileActionHandler;
		this.fileDetailsProvider = fileDetailsProvider;
		this.localizator = localizator;
	}

	private Localizator localizator;
	private FileDetailsProvider fileDetailsProvider;
	private FileActionHandler fileActionHandler;

	public FileDetailsPopup createPopup() {
		return new FileDetailsPopup(localizator, fileDetailsProvider,
				fileActionHandler);
	}

}
