package getpublication.db.json.publication.mangahost;

import java.io.File;
import java.util.Set;

import org.json.simple.JSONObject;

import getpublication.db.json.JsonBaseOperations;
import getpublication.db.json.publication.JsonPublication;
import getpublication.db.json.publication.PropertiesName;
import getpublication.folders.UserFolder;
import getpublication.util.folder.CreateFolder;

public class JsonMangahost extends JsonBaseOperations implements JsonPublication {

    private static final String FOLDER = UserFolder.getPathToDbFolder()
            + File.separator + "mangahost";

    private static final String DB_PATH_AND_NAME = FOLDER + File.separator
            + "mangahost.json";

    public JsonMangahost() {
        CreateFolder.create(new File(FOLDER));
    }

    @Override
    public String getDbPathAndName() {
        return DB_PATH_AND_NAME;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void addProject(String projectName) {
        this.getJsonObject().put(projectName, new JSONObject());
    }

    @Override
    public boolean removeProject(String projectName) {
        if (!this.hasProject(projectName)) {
            return false;
        }

        this.getJsonObject().remove(projectName);
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void addProjectProperty(String projectName, PropertiesName propertyName,
            String propertyValue) {
        JSONObject obj = (JSONObject) this.getJsonObject().get(projectName);
        obj.put(propertyName.toString(), propertyValue);
    }

    @Override
    public boolean removeProjectProperty(String projectName,
            PropertiesName propertyName) {
        if (!this.hasProjectProperty(projectName, propertyName)) {
            return false;
        }

        JSONObject obj = (JSONObject) this.getJsonObject().get(projectName);
        obj.remove(propertyName.toString());
        return true;
    }

    @Override
    public String getProjectProperty(String projectName, PropertiesName propertyName) {
        if (!this.hasProjectProperty(projectName, propertyName)) {
            return "";
        }
        JSONObject obj = (JSONObject) this.getJsonObject().get(projectName);
        return ((String) obj.get(propertyName.toString()));
    }

    @Override
    public boolean hasProject(String project) {
        if (!this.getJsonObject().containsKey(project)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean hasProjectProperty(String project, PropertiesName property) {
        if (!this.getJsonObject().containsKey(project)) {
            return false;
        }

        JSONObject obj = (JSONObject) this.getJsonObject().get(project);

        if (!obj.containsKey(property.toString())) {
            return false;
        }

        return true;
    }

    @Override
    public Set<?> getProjects() {
        return this.getJsonObject().keySet();
    }

    @Override
    protected String getJsonPath() {
        return DB_PATH_AND_NAME;
    }

}
