(function(){ mollify.ui.texts.add('en', {

decimalSeparator: ".",
groupingSeparator: ",",
zeroDigit: "0",
plusSign: "+",
minusSign: "-",

days: ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"],
daysShort: ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"],
daysMin: ["Su", "Mo", "Tu", "We", "Th", "Fr", "Sa", "Su"],
months: ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"],
monthsShort: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
today: "Today",
weekStart: 1,

fileSizeFormat: "#.#",
sizeInBytes: "{0} bytes",
sizeOneByte: "1 byte",
sizeOneKilobyte: "1 kB",
sizeInKilobytes: "{0} kB",
sizeInMegabytes: "{0} MB",
sizeInGigabytes: "{0} GB",

shortDateTimeFormat: "M/d/yyyy h:mm:ss tt",
shortDateFormat: "M/d/yyyy",
timeFormat: "h:mm:ss a",

pleaseWait: "Please wait...",
yes: "Yes",
no: "No",
ok: "OK",
dialogCancelButton: "Cancel",
dialogCancel: "Cancel",
dialogClose: "Close",
dialogSave: "Save",
errorDialogTitle: "Mollify",
errorDialogUnknownError: "Request failed",
errorDialogMessage_204: "File already exists.",
errorDialogMessage_205: "Folder already exists.",

loginDialogTitle: "Login",
loginDialogUsername: "Username",
loginDialogPassword: "Password",
loginDialogRememberMe: "Remember me",
loginDialogResetPassword: "Forgot password?",
loginDialogRegister: "Register",
loginDialogLoginButton: "Log in",
loginDialogLoginFailedMessage: "Login failed, user name or password was incorrect.",
resetPasswordPopupMessage: "Enter your email address:",
resetPasswordPopupButton: "Reset",

mainViewSearch: "Search",
mainViewChangePasswordTitle: "Change password...",
mainViewAdministrationTitle: "Administration...",
mainViewLogoutTitle: "Logout",
mainViewRootsTitle: "Published folders",
mainviewCreateFolderTitle : "Create new folder:",
mainviewCreateFolderName : "Folder name",
mainviewDescriptionHint: "Enter description here",

mainviewChangePasswordTitle: "Change password",
mainviewChangePasswordOld: "Old password",
mainviewChangePasswordNew: "New password",
mainviewChangePasswordVerify: "Verify password",
mainviewChangePasswordAction: "Change",
mainviewChangePasswordErrorValueMissing: "Missing",
mainviewChangePasswordErrorConfirm: "Passwords don't match",
mainviewChangePasswordSuccess: "Your password has been changed",
mainviewChangePasswordError: "Could not change password, authentication failed",

mainViewSearchResultsTitle : "Found {0} results",
mainViewSearchResultsDesc : "Searching \"{0}\"",
mainViewSearchResultTooltipMatches : "Matches: ",

uploaderWidgetTitle: "Upload new file(s) by clicking or dragging files here:",
mainviewRetrieveFileTitle: "Retrieve file from URL:",
mainviewRetrieveUrlTitle: "File URL",
mainviewRetrieveFileResourceNotFound: "Could not retrieve file, resource not found: {0}",

fileListColumnTitleName: "Name",
fileListColumnTitlePath: "Path",
fileListColumnTitleSize: "Size",
fileListColumnTitleLastModified: "Last modified",

fileActionDownloadTitle: "Download",
fileActionRenameTitle: "Rename...",
fileActionDeleteTitle: "Delete",

secondaryActions: "Actions",

itemcontextDescriptionHint: "Enter description here",

actionDownloadItem: "Download",
actionCopyItem: "Copy...",
actionCopyMultiple: "Copy...",
actionMoveItem: "Move...",
actionMoveMultiple: "Move...",
actionCopyItemHere: "Copy here...",
actionDeleteItem: "Delete",
actionDeleteMultiple: "Delete ",
actionRenameItem: "Rename...",

actionDeniedDelete: "Item \"{0}\" cannot be deleted because:",
actionDeniedDeleteMany: "One or more items cannot be deleted because:",
actionAcceptDelete: "Are you sure you want to delete \"{0}\":",
actionAcceptDeleteMany: "Are you sure you want to delete {0} items:",

fileItemDetailsGroupFile: "File",
fileItemContextDataName: "Name",
fileItemContextDataPath: "Path",
fileItemContextDataSize: "File size",
fileItemContextDataExtension: "Extension",
fileItemContextDataLastModified: "Last modified",
fileItemDetailsGroupExif: "Image information",
fileItemContextDataImageSize: "Image size",
fileItemContextDataImageSizePixels: "{0} pixels",

copyHereDialogTitle: "Copy Here",
copyHereDialogMessage: "Enter the name for the copy:",
copyFileDialogAction: "Copy",

copyFileDialogTitle: "Copy",
copyFileMessage: "Select folder where \"{0}\" is copied into:",

copyMultipleFileDialogTitle: "Copy",
copyMultipleFileMessage: "Select folder where {0} files are copied into:",

renameDialogTitleFile: "Rename File",
renameDialogTitleFolder: "Rename Folder",
renameDialogNewName: "Enter new name:",
renameDialogRenameButton: "Rename",

moveFileDialogTitle: "Move File",
moveDirectoryDialogTitle: "Move Folder",
moveDirectoryMessage: "Select folder where \"{0}\" is moved into:",
moveFileMessage: "Select folder where \"{0}\" is moved into:",
moveFileDialogAction: "Move",
moveDirectoryDialogAction: "Move",

deleteFileConfirmationDialogTitle: "Delete File",
deleteFolderConfirmationDialogTitle: "Delete Folder",
confirmFileDeleteMessage: "Are you sure you want to delete \"{0}\"?",
confirmFolderDeleteMessage: "Are you sure you want to delete \"{0}\" and all its files and subfolders?",

dropboxEmpty: "Clear",

pluginItemDetailsContextTitle: "Details",

pluginFileViewerEditorPreview: "Preview",
pluginFileViewerEditorView: "View",
pluginFileViewerEditorEdit: "Edit",
fileViewerEditorViewInNewWindowTitle: "Open in New Window",
fileViewerEditorViewEditDialogTitle: "Edit",
fileViewerEditorViewEditTitle: "Edit",

pluginCommentContextTitle: "Comments",
commentsDialogTitle: "Comments",
commentsDialogNoComments: "No comments",
commentsDialogAddButton: "Add",
commentsDetailsCount: "Comments",
commentsDialogRemoveComment: "Remove",

pluginPermissionsAction: "Permissions...",
pluginPermissionsContextTitle: "Permissions",
pluginPermissionsContextEditAction: "Edit...",
pluginPermissionsEditDialogTitle: "Permissions",
pluginPermissionsValueRW: "Read/Write",
pluginPermissionsValueRO: "Read Only",
pluginPermissionsValueN: "None",
pluginPermissionsEditColUser: "User",
pluginPermissionsEditColPermission: "Permission",
pluginPermissionsEditRemove: "Remove",
pluginPermissionsEditNewTitle: "Add or Update Permission",
pluginPermissionsEditNoUser: "Select user",
pluginPermissionsEditNoPermission: "Select permission",

pluginDropboxAddTo: "Add to Dropbox",

pluginArchiverCompress: "Compress...",
pluginArchiverExtract: "Extract",
pluginArchiverDownloadCompressed: "Download compressed",
pluginArchiverCompressDialogTitle: "Compress",
pluginArchiverCompressDialogMessage: "Enter the name for the compressed package:",
pluginArchiverCompressDialogAction: "Compress",

itemContextShareTitle: 'Share',
itemContextShareMenuTitle: 'Share…',
pluginShareFilelistColOtherShared: 'This item has been shared by other users',
pluginShareActionValidationDeleteShared: '\"{0}\" has been shared',
pluginShareActionValidationDeleteSharedOthers: '\"{0}\" has been shared by other users',
shareDialogTitle: 'Shares',
shareDialogNoShares: 'No shares',
shareDialogShareFileTitle: "Share file download",
shareDialogShareFolderTitle: 'Share folder upload',
shareDialogUnnamedShareTitle: 'Unnamed',
shareDialogItemLinkTitle: 'Show/hide link',
shareDialogItemInactiveLinkTitle: 'Inactive',
shareDialogShareLinkInstructions: 'Copy&paste the link below to access this share:',
shareDialogShareLinkCopyToClipboard: 'Copy link to clipboard',
shareDialogShareAddButton: 'Create new…',
shareDialogShareEditButton: 'Edit',
shareDialogShareAddTitle: 'Create New Share',
shareDialogShareEditTitle: 'Edit Share',
shareDialogShareRemoveButton: 'Remove',
shareDialogShareCreateNewTitle: 'Create New Share',
shareDialogShareNameTitle: 'Name (optional):',
shareDialogShareLinkTitle: 'Link',
shareDialogShareActiveTitle: 'Active:',
shareDialogShareValidityTitle: 'Validity',
shareDialogShareExpirationTitle: 'Expires at',
shareDialogSave: 'Save',
shareDialogCancel: 'Cancel',

pluginItemCollectionsNavTitle: "Stored"
})})();
