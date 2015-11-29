package getpublication.ui.commands.implementations;

import getpublication.db.json.JsonBasicOperations;
import getpublication.db.json.publication.JsonPublication;
import getpublication.db.json.publication.mangahost.JsonMangahost;
import getpublication.project.SiteName;
import getpublication.ui.CommandChooser;
import getpublication.ui.commands.Command;
import getpublication.ui.commands.ContextCommand;

public class MangahostChooserCommand implements Command{

    @Override
    public void action(ContextCommand context) {
        context.setSiteName(SiteName.MANGAHOST);
        JsonPublication jsonPublication = new JsonMangahost();
        ((JsonBasicOperations) jsonPublication).load();
        context.setJsonPublication(jsonPublication);
        
        Command addProject = new AddProjectCommand();
        Command removeProject = new RemoveProjectCommand();
        Command selectProject = new ProjectChooserCommand();
        Command chapterChooser = new ChapterChooserCommand();
        Command exitCommand = new ExitCommand();
        
        CommandChooser commandChooser = new CommandChooser();
        commandChooser.addContext(context);
        commandChooser.setTitle("select a command:");
        commandChooser.addCommand(addProject);
        commandChooser.addCommand(removeProject);
        commandChooser.addCommand(selectProject);
        
        boolean exit = false;
        while (!exit) {
            if (context.getProject() != null) {
                commandChooser.removeCommand(exitCommand);
                commandChooser.addCommand(chapterChooser);
                commandChooser.addCommand(exitCommand);
            } else {
                commandChooser.removeCommand(exitCommand);
                commandChooser.removeCommand(chapterChooser);
                commandChooser.addCommand(exitCommand);
            }
            
            Command selectedCommand = commandChooser.run();
            if (selectedCommand instanceof ExitCommand) {
                exit = true;
            }
        }
    }

    @Override
    public String getCommandName() {
        return "Mangahost";
    }

}
