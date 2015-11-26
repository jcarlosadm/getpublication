package getpublication.folders;

import java.io.File;

public class DownloadFolder {
    private String pathToDownloadFolder = System.getProperty("user.home")
            + File.separator + "Download";

    public String getPathToDownloadFolder() {
        return this.pathToDownloadFolder;
    }

    public void setPathToDownloadFolder(String pathToDownloadFolder) {
        this.pathToDownloadFolder = pathToDownloadFolder;
    }
}
