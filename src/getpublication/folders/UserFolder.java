package getpublication.folders;

import java.io.File;

import getpublication.util.folder.CreateFolder;

public class UserFolder {

    private static final String TEMP_FOLDER = "temp";
    private static final String DB_FOLDER = "db";
    private static final String USER_HOME_LOCATION_PROPERTY = "user.home";
    private static final String MAIN_FOLDER = ".getpublications";
    private static final String PLUGIN_FOLDER = "plugins";

    public void createFolders() {
        this.createMainFolder();
        this.createDBFolder();
        this.createTempFolder();
        this.createPluginFolder();
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
    
    public static String getPathToPluginFolder(){
        return (getPathToMainFolder() + File.separator + PLUGIN_FOLDER);
    }
}
