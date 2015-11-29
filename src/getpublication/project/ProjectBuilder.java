package getpublication.project;

import getpublication.project.mangahost.MangahostProject;

public class ProjectBuilder {
    
    private String name = "";
    
    private String urlPart = "";
    
    private boolean anonymousMode = false;
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setUrlPart(String urlPart){
        this.urlPart = urlPart;
    }
    
    public void setAnonymousMode(boolean anonymousMode){
        this.anonymousMode = anonymousMode;
    }
    
    public Project build(SiteName siteName){
        if (siteName.equals(SiteName.MANGAHOST)) {
            return new MangahostProject(this.name, this.urlPart, this.anonymousMode);
        }
        
        return null;
    }
}
