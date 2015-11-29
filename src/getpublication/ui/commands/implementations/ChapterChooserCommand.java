package getpublication.ui.commands.implementations;

import getpublication.project.Project;
import getpublication.ui.StringChooser;
import getpublication.ui.commands.Command;
import getpublication.ui.commands.ContextCommand;

public class ChapterChooserCommand implements Command {

    @Override
    public void action(ContextCommand context) {
        Project project = context.getProject();
        if (project == null) {
            System.out.println("project not selected");
            return;
        }
        
        StringChooser stringChooser = new StringChooser();
        stringChooser.setTitle("select a chapter:");
        stringChooser.setStringList(project.getAllChapterNames());
        String selectedChapter = stringChooser.runSideBySide();
        
        if (selectedChapter == null || selectedChapter.equals("")) {
            System.out.println("chapter not selected");
            return;
        }
        
        project.downloadChapter(selectedChapter, context.getDownloadFolder());
    }

    @Override
    public String getCommandName() {
        return "select chapter for download";
    }

}
