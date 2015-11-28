package getpublication.ui.commands.implementations;

import getpublication.db.json.JsonBasicOperations;
import getpublication.db.json.publication.JsonPublication;
import getpublication.ui.StringChooser;
import getpublication.ui.commands.Command;
import getpublication.ui.commands.ContextCommand;

public class RemoveProjectCommand implements Command {

    @Override
    public void action(ContextCommand context) {
        JsonPublication jsonPublication = context.getJsonPublication();
        ((JsonBasicOperations) jsonPublication).load();
        
        StringChooser stringChooser = new StringChooser();
        stringChooser.setTitle("type a number of one project:");
        for (String projectName : jsonPublication.getProjects()) {
            stringChooser.addString(projectName);
        }
        
        String selectedProject = stringChooser.runWithIndex();
        if (selectedProject != null && !selectedProject.equals("")) {
            jsonPublication.removeProject(selectedProject);
            
            ((JsonBasicOperations) jsonPublication).save();
            System.out.println("project "+selectedProject+" removed");
        } else {
            System.out.println("remove canceled");
        }
    }

    @Override
    public String getCommandName() {
        return "remove project";
    }

}
