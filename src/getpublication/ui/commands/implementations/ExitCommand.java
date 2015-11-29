package getpublication.ui.commands.implementations;

import getpublication.ui.commands.Command;
import getpublication.ui.commands.ContextCommand;

public class ExitCommand implements Command{

    @Override
    public void action(ContextCommand context) {
    }

    @Override
    public String getCommandName() {
        return "exit";
    }

}
