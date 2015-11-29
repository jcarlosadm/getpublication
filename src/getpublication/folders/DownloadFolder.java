package getpublication.folders;

import java.io.File;

public class DownloadFolder {
    private String pathToDownloadFolder = System.getProperty("user.home")
            + File.separator + "Downloads";

    public String getPath() {
        return this.pathToDownloadFolder;
    }

    public void setPath(String pathToDownloadFolder) {
        this.pathToDownloadFolder = pathToDownloadFolder;
    }
}
