package getpublication.parser;

import java.util.List;

public class HtmlNewsObjectInfo {
    private String projectName = "";
    
    private List<String> chapters = null;
    
    public String getProjectName() {
        return projectName;
    }
    
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<String> getChapters() {
        return chapters;
    }

    public void setChapters(List<String> chapters) {
        this.chapters = chapters;
    }
}
