package getpublication.db.json.basicconfig;

import java.io.File;

import getpublication.db.json.JsonBaseOperations;
import getpublication.folders.DownloadFolder;
import getpublication.folders.UserFolder;

public class JsonConfig extends JsonBaseOperations {

    private static final String DB_PATH_AND_NAME = UserFolder
            .getPathToDbFolder() + File.separator + "config.json";

    @Override
    protected String getJsonPath() {
        return DB_PATH_AND_NAME;
    }

    @SuppressWarnings("unchecked")
    public void setDownloadFolder(String pathDowloadFolder) {
        this.getJsonObject().put(JsonConfigConstants.DOWNLOADFOLDER.toString(),
                pathDowloadFolder);
    }

    public String getDownloadFolder() {
        String path = ((String) this.getJsonObject()
                .get(JsonConfigConstants.DOWNLOADFOLDER.toString()));
        if (path == null || path.equals("")) {
            path = (new DownloadFolder()).getPathToDownloadFolder();
            this.setDownloadFolder(path);
        }

        return path;
    }
}
