package getpublication.ui.commands;

import getpublication.folders.DownloadFolder;

public class ContextCommand {
    private DownloadFolder downloadFolder = new DownloadFolder();
    
    public void setDownloadFolder(DownloadFolder downloadFolder){
        this.downloadFolder = downloadFolder;
    }
    
    public DownloadFolder getDownloadFolder(){
        return this.downloadFolder;
    }
}
