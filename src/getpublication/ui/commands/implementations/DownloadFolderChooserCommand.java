package getpublication.ui.commands.implementations;

import getpublication.db.json.basicconfig.JsonConfig;
import getpublication.folders.DownloadFolder;
import getpublication.ui.commands.Command;
import getpublication.ui.commands.ContextCommand;
import getpublication.util.folder.chooser.DialogFolderChooser;

public class DownloadFolderChooserCommand implements Command {
    
    @Override
    public void action(ContextCommand context) {
        String path = DialogFolderChooser.pickFolder();
        if (path != null && !path.equals("")) {
            JsonConfig jsonConfig = new JsonConfig();
            jsonConfig.load();
            jsonConfig.setDownloadFolder(path);
            jsonConfig.save();
            DownloadFolder downloadFolder = context.getDownloadFolder();
            downloadFolder.setPathToDownloadFolder(path);
        }
    }
}
