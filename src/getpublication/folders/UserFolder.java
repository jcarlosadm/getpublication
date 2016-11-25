package getpublication.folders;

import java.io.File;

import getpublication.util.folder.CreateFolder;

public class UserFolder {

    private static final String TEMP_FOLDER = "temp";
    private static final String DB_FOLDER = "db";
    private static final String USER_HOME_LOCATION_PROPERTY = "user.home";
    private static final String MAIN_FOLDER = ".getpublications";
    private static final String PLUGIN_FOLDER = "plugins";
    private static final String LOG_FOLDER = "logs";
    private static final String IMAGE_CACHE_FOLDER = "image_cache";

    public void createFolders() {
        this.createMainFolder();
        this.createDBFolder();
        this.createTempFolder();
        this.createPluginFolder();
        this.createLogFolder();
        this.createImageCacheFolder();
    }

    private void createLogFolder() {
        CreateFolder.create(new File(getPathToLogFolder()));
    }

    private void createPluginFolder(){
        CreateFolder.create(new File(getPathToPluginFolder()));
    }

    private void createTempFolder() {
        CreateFolder.create(new File(getPathToTempFolder()));
    }

    private void createDBFolder() {
        CreateFolder.create(new File(getPathToDbFolder()));
    }
    
    private void createImageCacheFolder() {
        CreateFolder.create(new File(getPathToImageCacheFolder()));
    }

    private void createMainFolder() {
        CreateFolder.create(new File(getPathToMainFolder()));
    }
    
    public static String getPathToLogFolder() {
        return (getPathToMainFolder() + File.separator + LOG_FOLDER);
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
    
    public static String getPathToPluginFolder(){
        return (getPathToMainFolder() + File.separator + PLUGIN_FOLDER);
    }
    
    public static String getPathToImageCacheFolder() {
        return (getPathToMainFolder() + File.separator + IMAGE_CACHE_FOLDER);
    }
}
