package getpublication.ui.commands.implementations;

import getpublication.db.json.publication.JsonPublication;
import getpublication.db.json.publication.PropertiesName;
import getpublication.project.Project;
import getpublication.project.ProjectBuilder;
import getpublication.ui.StringChooser;
import getpublication.ui.commands.Command;
import getpublication.ui.commands.ContextCommand;

public class ProjectChooserCommand implements Command {

    @Override
    public void action(ContextCommand context) {
        JsonPublication jsonPublication = context.getJsonPublication();
        if (jsonPublication == null) {
            System.out.println("error to get projects");
            return;
        }
        
        StringChooser stringChooser = new StringChooser();
        stringChooser.setTitle("select a project:");
        for (String projectName : jsonPublication.getProjects()) {
            stringChooser.addString(projectName);
        }
        String selectedProject = stringChooser.runWithIndex();
        
        if (selectedProject == null || selectedProject.equals("")) {
            System.out.println("project not selected");
            return;
        }
        
        ProjectBuilder projectBuilder = new ProjectBuilder();
        projectBuilder.setName(selectedProject);
        projectBuilder.setUrlPart(jsonPublication.getProjectProperty(selectedProject, 
                PropertiesName.NAME_IN_URL));
        projectBuilder.setAnonymousMode(context.isAnonymousMode());
        Project project = projectBuilder.build(context.getSiteName());
        
        context.setProject(project);
        System.out.println("project selected");
    }

    @Override
    public String getCommandName() {
        return "select project";
    }
}
