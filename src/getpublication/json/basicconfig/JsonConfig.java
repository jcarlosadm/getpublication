package getpublication.json.basicconfig;

import java.io.File;

import getpublication.folders.DownloadFolder;
import getpublication.folders.UserFolder;
import getpublication.json.JsonBasicOperations;

public class JsonConfig extends JsonBasicOperations {

    private static final String DB_PATH_AND_NAME = UserFolder
            .getPathToDbFolder() + File.separator + "config.json";

    @Override
    protected String getJsonPath() {
        return DB_PATH_AND_NAME;
    }

    @SuppressWarnings("unchecked")
    public void setPathToDownloadFolder(String pathToDowloadFolder) {
        this.getJsonObject().put(JsonConfigConstants.DOWNLOADFOLDER.toString(),
                pathToDowloadFolder);
    }

    public String getPathToDownloadFolder() {
        String path = ((String) this.getJsonObject()
                .get(JsonConfigConstants.DOWNLOADFOLDER.toString()));
        if (path == null || path.equals("")) {
            path = (new DownloadFolder()).getPath();
            this.setPathToDownloadFolder(path);
        }

        return path;
    }
}
