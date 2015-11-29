package getpublication.ui.commands.implementations;

import getpublication.db.json.basicconfig.JsonConfig;
import getpublication.ui.CommandChooser;
import getpublication.ui.commands.Command;
import getpublication.ui.commands.ContextCommand;

public class SiteChooserCommand implements Command {

    @Override
    public void action(ContextCommand context) {
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.load();
        context.getDownloadFolder().setPath(jsonConfig.getPathToDownloadFolder());
        
        Command mangahostCommand = new MangahostChooserCommand();
        Command exitCommand = new ExitCommand();
        Command downloadFolderChooserCommand = new DownloadFolderChooserCommand();
        Command anonymousCommand = new SetAnonymousCommand();
        
        CommandChooser commandChooser = new CommandChooser();
        commandChooser.setTitle("select a command:");
        commandChooser.addContext(context);
        commandChooser.addCommand(downloadFolderChooserCommand);
        commandChooser.addCommand(anonymousCommand);
        commandChooser.addCommand(mangahostCommand);
        commandChooser.addCommand(exitCommand);
        
        boolean exit = false;
        while (!exit) {
            Command selectedCommand = commandChooser.run();
            if (selectedCommand instanceof ExitCommand) {
                exit = true;
            }
        }
    }

    @Override
    public String getCommandName() {
        return "select a site";
    }

}
