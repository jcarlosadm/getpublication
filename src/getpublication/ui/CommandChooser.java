package getpublication.ui;

import java.util.ArrayList;
import java.util.List;

import getpublication.ui.commands.Command;
import getpublication.ui.commands.ContextCommand;
import getpublication.util.UserInput;

public class CommandChooser {

    private String title = "";

    private List<Command> commands = new ArrayList<>();

    private ContextCommand context = null;

    public void setTitle(String title) {
        this.title = title;
    }

    public void addCommand(Command command) {
        if (!this.commands.contains(command)) {
            this.commands.add(command);
        }
    }

    public void removeCommand(Command command) {
        this.commands.remove(command);
    }

    public void addContext(ContextCommand context) {
        this.context = context;
    }

    public Command run() {
        boolean exit = false;
        Command command = null;
        while (!exit) {
            if (!this.title.equals("")) {
                System.out.println(this.title);
            }

            for (int index = 0; index < this.commands.size(); index++) {
                System.out.println(index + " - "
                        + this.commands.get(index).getCommandName());
            }

            int choice = -1;
            try {
                choice = Integer.parseInt(UserInput.getInput());
            } catch (Exception e) {
                choice = -1;
            }

            if (choice >= 0 && choice < this.commands.size()) {
                System.out.println("\ncommand "
                        + this.commands.get(choice).getCommandName()
                        + " selected");
                command = this.commands.get(choice);

                exit = true;
            } else {
                System.out.println("error choice. try again\n");
            }
        }
        if (command != null && this.context != null) {
            command.action(this.context);
        }
        return command;
    }
}
