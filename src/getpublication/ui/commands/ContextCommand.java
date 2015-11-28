package getpublication.ui.commands;

import getpublication.db.json.publication.JsonPublication;
import getpublication.folders.DownloadFolder;

public class ContextCommand {
    private DownloadFolder downloadFolder = new DownloadFolder();
    
    private JsonPublication jsonPublication = null;

    public void setDownloadFolder(DownloadFolder downloadFolder){
        this.downloadFolder = downloadFolder;
    }
    
    public DownloadFolder getDownloadFolder(){
        return this.downloadFolder;
    }

    public JsonPublication getJsonPublication() {
        return this.jsonPublication;
    }
    
    public void setJsonPublication(JsonPublication jsonPublication){
        this.jsonPublication = jsonPublication;
    }
}
