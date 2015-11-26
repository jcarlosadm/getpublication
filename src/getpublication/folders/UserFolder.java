package getpublication.folders;

import java.io.File;

import getpublication.util.CreateFolder;

public class UserFolder {

    private static final String CACHE_FOLDER = "cache";
    private static final String TEMP_FOLDER = "temp";
    private static final String DB_FOLDER = "db";
    private static final String USER_HOME_LOCATION_PROPERTY = "user.home";
    private static final String MAIN_FOLDER = ".getpublications";

    public void createFolders() {
        this.createMainFolder();
        this.createDBFolder();
        this.createTempFolder();
        this.createCacheFolder();
    }

    private void createCacheFolder() {
        CreateFolder.create(new File(getPathToCacheFolder()));
    }

    private void createTempFolder() {
        CreateFolder.create(new File(getPathToTempFolder()));
    }

    private void createDBFolder() {
        CreateFolder.create(new File(getPathToDbFolder()));
    }

    private void createMainFolder() {
        CreateFolder.create(new File(getPathToMainFolder()));
    }

    public static String getPathToMainFolder() {
        String homeDir = System.getProperty(USER_HOME_LOCATION_PROPERTY);
        return (homeDir + File.separator + MAIN_FOLDER);
    }
    
    public static String getPathToDbFolder(){
        return (getPathToMainFolder() + File.separator + DB_FOLDER);
    }
    
    public static String getPathToTempFolder(){
        return (getPathToMainFolder() + File.separator + TEMP_FOLDER);
    }
    
    public static String getPathToCacheFolder(){
        return (getPathToMainFolder() + File.separator + CACHE_FOLDER);
    }
}
