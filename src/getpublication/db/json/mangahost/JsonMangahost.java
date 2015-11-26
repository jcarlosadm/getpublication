package getpublication.db.json.mangahost;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import getpublication.db.json.JsonPublication;
import getpublication.db.json.PropertiesName;
import getpublication.folders.UserFolder;
import getpublication.util.CreateFolder;

public class JsonMangahost implements JsonPublication {

    private static final String FOLDER = UserFolder.getPathToDbFolder()
            + File.separator + "mangahost";

    private static final String DB_PATH_AND_NAME = FOLDER + File.separator
            + "mangahost.json";

    private JSONObject jsonObject = new JSONObject();

    public JsonMangahost() {
        CreateFolder.create(new File(FOLDER));
    }

    @Override
    public String getDbPathAndName() {
        return DB_PATH_AND_NAME;
    }

    @Override
    public void load() {
        File file = new File(DB_PATH_AND_NAME);
        if (file.exists()) {
            JSONParser parser = new JSONParser();
            try {
                this.jsonObject = (JSONObject) parser
                        .parse(new FileReader(file));
            } catch (IOException | ParseException e) {
                System.out.println("error to open json file");
            }
        }
    }

    @Override
    public void save() {
        try {
            FileUtils.writeStringToFile(new File(DB_PATH_AND_NAME),
                    this.jsonObject.toJSONString());
        } catch (IOException e) {
            System.out.println("Error to save json file");
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void addProject(String projectName) {
        this.jsonObject.put(projectName, new JSONObject());
    }

    @Override
    public boolean removeProject(String projectName) {
        if (!this.hasProject(projectName)) {
            return false;
        }

        this.jsonObject.remove(projectName);
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void addProjectProperty(String projectName, PropertiesName propertyName,
            String propertyValue) {
        JSONObject obj = (JSONObject) this.jsonObject.get(projectName);
        obj.put(propertyName.toString(), propertyValue);
    }

    @Override
    public boolean removeProjectProperty(String projectName,
            PropertiesName propertyName) {
        if (!this.hasProjectProperty(projectName, propertyName)) {
            return false;
        }

        JSONObject obj = (JSONObject) this.jsonObject.get(projectName);
        obj.remove(propertyName.toString());
        return true;
    }

    @Override
    public String getProjectProperty(String projectName, PropertiesName propertyName) {
        if (!this.hasProjectProperty(projectName, propertyName)) {
            return "";
        }
        JSONObject obj = (JSONObject) this.jsonObject.get(projectName);
        return ((String) obj.get(propertyName.toString()));
    }

    @Override
    public boolean hasProject(String project) {
        if (!this.jsonObject.containsKey(project)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean hasProjectProperty(String project, PropertiesName property) {
        if (!this.jsonObject.containsKey(project)) {
            return false;
        }

        JSONObject obj = (JSONObject) this.jsonObject.get(project);

        if (!obj.containsKey(property.toString())) {
            return false;
        }

        return true;
    }

    @Override
    public Set<?> getProjects() {
        return this.jsonObject.keySet();
    }

}
