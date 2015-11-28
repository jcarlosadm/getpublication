package getpublication.util.folder;

import java.io.File;

public class CreateFolder {
    public static boolean create(File folder) {
        if (folder.exists() && folder.isDirectory()) {
            return true;
        }
        
        return folder.mkdirs();
    }
}
