(function(){ mollify.ui.texts.add('de', {

decimalSeparator: ".",
groupingSeparator: ",",
zeroDigit: "0",
plusSign: "+",
minusSign: "-",

days: ["Sonntag", "Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag", "Sonntag"],
daysShort: ["Son", "Mon", "Die", "Mit", "Don", "Fre", "Sam", "Son"],
daysMin: ["So", "Mo", "Di", "Mi", "Do", "Fr", "Sa", "So"],
months: ["Januar", "Februar", "März", "April", "Mai", "Juni", "Juli", "August", "September", "Oktober", "November", "Dezember"],
monthsShort: ["Jan", "Feb", "Mär", "Apr", "Mai", "Jun", "Jul", "Aug", "Sep", "Okt", "Nov", "Dez"],
today: "Heute",
weekStart: 1,

fileSizeFormat: "#.#",
sizeInBytes: "{0} bytes",
sizeOneByte: "1 byte",
sizeOneKilobyte: "1 kB",
sizeInKilobytes: "{0} kB",
sizeInMegabytes: "{0} MB",
sizeInGigabytes: "{0} GB",

shortDateTimeFormat: "d/M/yyyy h:mm:ss tt",
shortDateFormat: "d/M/yyyy",
timeFormat: "h:mm:ss a",

pleaseWait: "Bitte warten...",
yes: "Ja",
no: "Nein",
ok: "OK",
dialogCancelButton: "Abbrechen",
dialogCancel: "Abbrechen",
dialogClose: "Schließen",
dialogSave: "Speichern",
errorDialogTitle: "Mollify",
errorDialogUnknownError: "Anfrage fehlgeschlagen",
errorDialogMessage_204: "Datei existiert schon.",
errorDialogMessage_205: "Ordner existiert schon.",

loginDialogTitle: "Login",
loginDialogUsername: "Benutzername",
loginDialogPassword: "Passwort",
loginDialogRememberMe: "Angemeldet bleiben?",
loginDialogResetPassword: "Passwort vergessen?",
loginDialogRegister: "Regisitrieren",
loginDialogLoginButton: "Einloggen",
loginDialogLoginFailedMessage: "Benutzername oder Passwort falsch, bitte erneut versuchen.",
resetPasswordPopupMessage: "E-Mail Adresse eingeben:",
resetPasswordPopupButton: "Zurücksetzen",
resetPasswordPopupResetSuccess: "Neues Passwort wurde an angegebene E-Mail Adresse geschickt.",
resetPasswordPopupResetFailed: "Password zurücksetzen fehlgeschlagen",

mainviewMenuTitle: "Dateien",
mainViewSearch: "Suche",
mainViewChangePasswordTitle: "Passwort ändern...",
mainViewAdministrationTitle: "Administration...",
mainViewLogoutTitle: "Logout",
mainViewRootsTitle: "Ordner",
mainviewCreateFolderTitle : "Neuen Ordner erstellen:",
mainviewCreateFolderName : "Ordnername",
mainviewDescriptionHint: "Beschreibung",
mainviewFolderNotFound: "Ordner \"{0}\" wurde nicht gefunden",

mainviewChangePasswordTitle: "Passwort ändern",
mainviewChangePasswordOld: "Altes Passwort",
mainviewChangePasswordNew: "Neues Passwort",
mainviewChangePasswordVerify: "Bestätige Passwort",
mainviewChangePasswordAction: "Ändern",
mainviewChangePasswordErrorValueMissing: "Es fehlt etwas",
mainviewChangePasswordErrorConfirm: "Passwort Fehler",
mainviewChangePasswordSuccess: "Passort wurde erfolgreich geändert",
mainviewChangePasswordError: "Konnte nicht geändert werden, keine Rechte",

mainViewSearchResultsTitle : "{0} Suchergebnisse",
mainViewSearchResultsDesc : "Suchen \"{0}\"",
mainViewSearchResultTooltipMatches : "Suchergebnisse: ",

uploaderWidgetTitle: "Lade neue Dateien hoch:",
mainviewRetrieveFileTitle: "Datei von URL hochladen:",
mainviewRetrieveUrlTitle: "Datei URL",
mainviewRetrieveFileResourceNotFound: "Datei konnte nicht gefunden werden, Quelle existiert nicht. {0}",

fileListColumnTitleName: "Name",
fileListColumnTitlePath: "Pfad",
fileListColumnTitleSize: "Größe",
fileListColumnTitleLastModified: "Zuletzt geändert",

fileActionDownloadTitle: "Download",
fileActionRenameTitle: "Umbenennen...",
fileActionDeleteTitle: "Löschen",

secondaryActions: "Aktionen",

itemcontextDescriptionHint: "Beschreibung",

actionDownloadItem: "Download",
actionCopyItem: "Kopieren...",
actionCopyMultiple: "Kopieren...",
actionMoveItem: "Bewegen...",
actionMoveMultiple: "Bewegen...",
actionCopyItemHere: "Hierhin kopieren...",
actionDeleteItem: "Löschen",
actionDeleteMultiple: "Löschen",
actionRenameItem: "Umbenennen...",

actionDeniedDelete: "Datei/Ordner \"{0}\" konnte nicht gelöscht werden, weil:",
actionDeniedDeleteMany: "Eine oder mehrere Dateien/Ordner konnten nicht gelöscht werden, weil:",
actionAcceptDelete: "Wirklich löschen? \"{0}\":",
actionAcceptDeleteMany: "Wirklich {0} Dateien löschen?:",

fileItemDetailsGroupFile: "Datei",
fileItemContextDataName: "Name",
fileItemContextDataPath: "Pfad",
fileItemContextDataSize: "Datei Größe",
fileItemContextDataExtension: "Anhang",
fileItemContextDataLastModified: "Zuletzt geändert",
fileItemDetailsGroupExif: "Bild Infos",
fileItemContextDataImageSize: "Bildgröße",
fileItemContextDataImageSizePixels: "{0} Pixel",

copyHereDialogTitle: "Hierhin kopieren",
copyHereDialogMessage: "Namen für die Kopie eingeben:",
copyFileDialogAction: "Kopie",

copyFileDialogTitle: "Kopie",
copyFileMessage: "Ordner auswählen wo \"{0}\" reinkopiert werden soll:",

copyMultipleFileDialogTitle: "Kopie",
copyMultipleFileMessage: "Ordner auswählen wo {0} Dateien reinkopiert werden sollen:",

renameDialogTitleFile: "Datei umbenennen",
renameDialogTitleFolder: "Ordner umbenennen",
renameDialogNewName: "Neuen Namen eingeben:",
renameDialogRenameButton: "Umbenennen",

moveFileDialogTitle: "Datei bewegen",
moveDirectoryDialogTitle: "Ordner bewegen",
moveDirectoryMessage: "Ordner auswählen wo \"{0}\" hinbewegt werden soll:",
moveFileMessage: "Ordner auswählen wo \"{0}\" hinbewegt werden soll:",
moveFileDialogAction: "Bewegen",
moveDirectoryDialogAction: "Bewegen",

deleteFileConfirmationDialogTitle: "Datei löschen",
deleteFolderConfirmationDialogTitle: "Ordner löschen",
confirmFileDeleteMessage: "Wirklich \"{0}\" löschen ?",
confirmFolderDeleteMessage: "Wirklich \"{0}\" löschen und alle Datein und Unterordner?",

dropboxEmpty: "Leeren",

pluginItemDetailsContextTitle: "Details",

pluginFileViewerEditorPreview: "Vorschau",
pluginFileViewerEditorView: "Zeigen",
pluginFileViewerEditorEdit: "Bearbeiten",
fileViewerEditorViewInNewWindowTitle: "In neuem Fenster öffnen",
fileViewerEditorViewEditDialogTitle: "Bearbeiten",
fileViewerEditorViewEditTitle: "Bearbeiten",

pluginCommentContextTitle: "Kommentare",
commentsDialogTitle: "Kommentare",
commentsDialogNoComments: "Keine Kommentare",
commentsDialogAddButton: "Hinzufügen",
commentsDetailsCount: "Kommentare",
commentsDialogRemoveComment: "Entfernen",

pluginPermissionsAction: "Rechte...",
pluginPermissionsContextTitle: "Rechte",
pluginPermissionsContextEditAction: "Bearbeiten...",
pluginPermissionsEditDialogTitle: "Rechte",
pluginPermissionsValueRW: "Read/Write",
pluginPermissionsValueRO: "Read Only",
pluginPermissionsValueNO: "No Rights",
pluginPermissionsEditColUser: "Benutzer",
pluginPermissionsEditColPermission: "Rechte",
pluginPermissionsEditRemove: "Entfernen",
pluginPermissionsEditNewTitle: "Rechte aktualisieren oder hinzufügen?",
pluginPermissionsEditNoUser: "Benutzer auswählen?",
pluginPermissionsEditNoPermission: "Rechte auswählen",

pluginDropboxAddTo: "Zur Sammelbox hinzufügen",

pluginArchiverCompress: "Komprimieren...",
pluginArchiverExtract: "Entpacken",
pluginArchiverDownloadCompressed: "Komprimiert herunterladen",
pluginArchiverCompressDialogTitle: "Komprimieren",
pluginArchiverCompressDialogMessage: "Name für komprimierte Datei:",
pluginArchiverCompressDialogAction: "Komprimieren",
pluginArchiverCompressSelectFolderDialogMessage: "Ordner auswählen wo Zip Datei gespeichert werden soll:",

itemContextShareTitle: 'Freigeben',
itemContextShareMenuTitle: 'Freigeben…',
pluginShareFilelistColOtherShared: 'Diese Datei wurde freigegeben',
pluginShareActionValidationDeleteShared: '\"{0}\" wurde freigegeben',
pluginShareActionValidationDeleteSharedOthers: '\"{0}\" wurde von anderen Benutzernamen freigegben',
shareDialogTitle: 'Freigegeben Elemente',
shareDialogNoShares: 'Keine freigegebenen Elemente',
shareDialogShareFileTitle: "Datei Download freigeben",
shareDialogShareFolderTitle: 'Ordner Upload freigeben',
shareDialogUnnamedShareTitle: 'Kein Name',
shareDialogItemLinkTitle: 'Zeigen/Verstecken Link',
shareDialogItemInactiveLinkTitle: 'Nicht aktiv',
shareDialogShareLinkInstructions: 'Kopieren&Einsetzen den Link von eben um die freigegebene Datei anzuzeigen:',
shareDialogShareLinkCopyToClipboard: 'Link in die Zwischenablage',
shareDialogShareAddButton: 'Neue hinzufügen…',
shareDialogShareEditButton: 'Beabreiten',
shareDialogShareAddTitle: 'Teilen',
shareDialogShareEditTitle: 'Freigegebenes Element bearbeiten',
shareDialogShareRemoveButton: 'Entfernen',
shareDialogShareCreateNewTitle: 'freigeben',
shareDialogShareNameTitle: 'Name (optional):',
shareDialogShareLinkTitle: 'Link',
shareDialogShareActiveTitle: 'Aktiv:',
shareDialogShareValidityTitle: 'Gültigkeit',
shareDialogShareExpirationTitle: 'Ablaufen am',
shareDialogSave: 'Speichern',
shareDialogCancel: 'Abbrechen',
pluginShareConfigViewNavTitle: "Freigegebene Elemente",
pluginShareConfigViewCountTitle: "Count",

pluginItemCollectionsNavTitle: "Gespeichert",
pluginItemCollectionStore: "Speichern...",
pluginItemCollectionStoreDialogTitle: "Elemente speichern.",
pluginItemCollectionStoreDialogMessage: "Name für Kollektion eingeben:",
pluginItemCollectionStoreDialogAction: "Speicherung",
pluginItemCollectionShareTitle: "Teile gespeicherte Kollektion",
pluginItemCollectionsEditDialogTitle: "Kollektion bearbeiten: {0}",
pluginItemCollectionsEditDialogRemove: "Entfernen",
pluginItemCollectionsNavRemove: "Entfernen",
pluginItemCollectionsNavShare: "Teilen...",
pluginItemCollectionsNavEdit: "Bearbeiten...",

configviewMenuTitle: "Einstellungen",
configViewUserNavTitle: "Benutzer",

configUserAccountNavTitle: "Account",
configUserAccountUsernameTitle: "Benutzer",
configUserAccountPasswordTitle: "Passwort",
configUserAccountPasswordAction: "Ändern",

configViewAdminNavTitle: "Admin",
configAdminUsersNavTitle: "Benutzer",
configAdminGroupsNavTitle: "Gruppen",
configAdminUsersPermissionModeAdmin: "Administrator",
configAdminTableIdTitle: "Id",

configAdminUsersNameTitle: "Benutzername",
configAdminUsersPermissionTitle: "Standard Rechte",
configAdminUsersEmailTitle: "Email",
configAdminUsersUserDialogEditTitle: "Benutzer bearbeiten",
configAdminUsersUserDialogAddTitle: "Neuen Benutzer hinzufügen",
configAdminUserDialogUsernameTitle: "Benutzername",
configAdminUserDialogEmailTitle: "Email",
configAdminUserDialogPasswordTitle: "Passwort",
configAdminUserDialogGeneratePassword: "Generieren",
configAdminUserDialogPermissionModeTitle: "Standard Rechte",
configAdminUserDialogAuthenticationTitle: "Authentication",
configAdminUsersUserDialogAuthDefault: "Standard ({0})",
configAdminUserDialogExpirationTitle: "Ablaufen am",
configAdminUsersFoldersTitle: "Ordner",
configAdminUsersGroupsTitle: "Gruppen",
configAdminUsersFolderNameTitle: "Benutzer Ordner Name",
configAdminUsersFolderDefaultNameTitle: "Standard Name",
configAdminUsersGroupNameTitle: "Gruppenname",
configAdminUserAddFolderTitle: "Benutzer Ordner hinzufügen",
configAdminUserAddFolderMessage: "Ordner auswählen, die zum Benutzer hinzugefügt werden sollen:",
configAdminUserAddGroupTitle: "Benutzer Gruppe hinzufügen",
configAdminUserAddGroupMessage: "Gruppen auswählen, die zum Benutzer hinzugefügt werden sollen",
configAdminUsersRemoveUsersConfirmationTitle: "Benutzer entfernen ",
configAdminUsersRemoveUsersConfirmationMessage: "Wirklich {0} Benutzer löschen?",
configAdminUsersRemoveUserConfirmationTitle: "Benutzer entfernen",
configAdminUsersRemoveUserConfirmationMessage: "Wirklich \"{0}\" entfernen?",
configAdminUsersChangePasswordDialogTitle: "Passwort für \"{0}\" ändern",

configAdminFoldersNavTitle: "Ordner",
configAdminFoldersNameTitle: "Ordnername",
configAdminFoldersPathTitle: "Pfad",
configAdminFoldersFolderDialogEditTitle: "Ordner bearbeiten",
configAdminFoldersFolderDialogAddTitle: "Neuen Ordner hinzufügen",
configAdminFolderAddUserTitle: "Benutzerordner hinzufügen",
configAdminFolderAddUserMessage: "Benutzer oder Gruppen auswählen, die hinzugefügt werden sollen:",
configAdminFolderUserTypeTitle: "Type",
configAdminFolderDialogNameTitle: "Ordnername",
configAdminFolderDialogPathTitle: "Server Dateisystem Pfad",
configAdminUsersFolderDefaultName: "{0} (standard)",
configAdminFolderUsersTitle: "Ordner Benutzer und Gruppen",
configAdminFoldersFolderDialogAddFolderDoesNotExist: "Ordner gibt es noch nicht, Ordner hinzufügen?",
configAdminFoldersRemoveFoldersConfirmationTitle: "Ordner entfernen",
configAdminFoldersRemoveFoldersConfirmationMessage: "Wirklich {0} Ordner löschen?",
configAdminFoldersRemoveFolderConfirmationTitle: "Ordner löschen",
configAdminFoldersRemoveFolderConfirmationMessage: "Wirklcih \"{0}\" Ordner löschen?",

configAdminGroupsDialogAddTitle: "Gruppe hinzufügen",
configAdminGroupsDialogEditTitle: "Gruppe bearbeiten",
configAdminGroupDialogNameTitle: "Name",
configAdminGroupsDescriptionTitle: "Beschreibung",
configAdminGroupsUsersTitle: "Gruppen Benutzer",
configAdminGroupsFoldersTitle: "Gruppen Ordner",
configAdminGroupAddFolderTitle: "Gruppen-Ordner hinzufügen",
configAdminGroupAddFolderMessage: "Ordner auswählen, die hinzugefügt werden sollen:",
configAdminGroupAddUserTitle: "Gruppenbenutzer hinzufügen",
configAdminGroupAddUserMessage: "Benutzer auswählen, die hinzugefügt werden sollen:",
configAdminGroupsRemoveGroupsConfirmationTitle: "Gruppen löschen",
configAdminGroupsRemoveGroupsConfirmationMessage: "Wirklich {0} Gruppen löschen?",
configAdminGroupsRemoveGroupConfirmationTitle: "Gruppe löschen",
configAdminGroupsRemoveGroupConfirmationMessage: "Wirklich \"{0}\" löschen?"
})})();
