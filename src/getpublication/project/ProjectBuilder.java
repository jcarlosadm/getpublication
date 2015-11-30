package getpublication.project;

public abstract class ProjectBuilder {
    
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
    
    public Project build(){
        return this.getInstance(this.name, this.urlPart, this.anonymousMode);
    }
    
    protected abstract Project getInstance(String name, String urlPart, boolean anonymousMode);
}
