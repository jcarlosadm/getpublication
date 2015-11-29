package getpublication.ui.commands.implementations;

import getpublication.ui.commands.Command;
import getpublication.ui.commands.ContextCommand;
import getpublication.util.UserInput;

public class SetAnonymousCommand implements Command {

    @Override
    public void action(ContextCommand context) {
        boolean anonymousMode = false;
        boolean exit = false;
        while (!exit) {
            System.out.println("type true or false to set anonymous mode:");
            
            try {
                anonymousMode = Boolean.parseBoolean(UserInput.getInput());
                exit = true;
            } catch (Exception e) {
                anonymousMode = false;
                System.out.println("input error. try again");
            }
        }
        
        context.setAnonymousMode(anonymousMode);
    }

    @Override
    public String getCommandName() {
        return "set anonymous mode";
    }

}
