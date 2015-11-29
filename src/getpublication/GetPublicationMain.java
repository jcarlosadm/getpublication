package getpublication;

import getpublication.ui.commands.ContextCommand;
import getpublication.ui.commands.implementations.SiteChooserCommand;

public class GetPublicationMain {
    public static void main(String[] args) {
        ContextCommand contextCommand = new ContextCommand();
        SiteChooserCommand command = new SiteChooserCommand();
        command.action(contextCommand);
    }
}
