package getpublication.json.publication;

import java.io.File;
import java.util.Set;

import org.json.simple.JSONObject;

import getpublication.json.JsonBasicOperations;
import getpublication.util.folder.CreateFolder;

public abstract class JsonPublication extends JsonBasicOperations {

    /**
     * Constructor
     * 
     * @param folderName
     *            folder to create
     */
    public JsonPublication() {
        String folderName = this.getFolderPath();
        if (folderName != null && !folderName.equals("")) {
            CreateFolder.create(new File(folderName));
        }
    }

    /**
     * @param projectName
     *            project to add
     */
    @SuppressWarnings("unchecked")
    public void addProject(String projectName) {
        if (!this.hasProject(projectName)) {
            JSONObject projects = this.getProjectsObj();
            if (projects == null) {
                this.getJsonObject().put("projects", new JSONObject());
                projects = this.getProjectsObj();
            }

            projects.put(projectName, new JSONObject());
        }
    }

    /**
     * @param projectName
     *            project to remove
     * @return true if success
     */
    public boolean removeProject(String projectName) {
        if (!this.hasProject(projectName)) {
            return false;
        }

        JSONObject projects = this.getProjectsObj();

        projects.remove(projectName);
        return true;
    }

    /**
     * add project property
     * 
     * @param projectName
     *            project name
     * @param propertyName
     *            property name
     * @param propertyValue
     *            property value
     */
    @SuppressWarnings("unchecked")
    public void addProjectProperty(String projectName,
            PropertiesName propertyName, String propertyValue) {
        if (!this.hasProject(projectName)) {
            return;
        }

        JSONObject projects = this.getProjectsObj();

        JSONObject obj = (JSONObject) projects.get(projectName);
        obj.put(propertyName.toString(), propertyValue);
    }

    /**
     * remove project property
     * 
     * @param projectName
     *            project name
     * @param propertyName
     *            property name
     * @return true if success
     */
    public boolean removeProjectProperty(String projectName,
            PropertiesName propertyName) {
        if (!this.hasProjectProperty(projectName, propertyName)) {
            return false;
        }

        JSONObject projects = this.getProjectsObj();

        JSONObject obj = (JSONObject) projects.get(projectName);
        obj.remove(propertyName.toString());
        return true;
    }

    /**
     * get project property
     * 
     * @param projectName
     *            project name
     * @param propertyName
     *            property name
     * @return value of project property
     */
    public String getProjectProperty(String projectName,
            PropertiesName propertyName) {
        if (!this.hasProjectProperty(projectName, propertyName)) {
            return "";
        }

        JSONObject projects = this.getProjectsObj();

        JSONObject obj = (JSONObject) projects.get(projectName);
        return ((String) obj.get(propertyName.toString()));
    }

    /**
     * Check if project exists
     * 
     * @param projectName
     *            project name
     * @return true if this project exists
     */
    public boolean hasProject(String projectName) {
        JSONObject projects = this.getProjectsObj();
        if (projects == null) {
            return false;
        }

        if (!projects.containsKey(projectName)) {
            return false;
        }
        return true;
    }

    /**
     * Check if project property exists
     * 
     * @param projectName
     *            project name
     * @param propertyName
     *            property name
     * @return true if project property exists
     */
    public boolean hasProjectProperty(String projectName,
            PropertiesName propertyName) {
        JSONObject projects = this.getProjectsObj();
        if (projects == null) {
            return false;
        }

        if (!projects.containsKey(projectName)) {
            return false;
        }

        JSONObject obj = (JSONObject) projects.get(projectName);

        if (!obj.containsKey(propertyName.toString())) {
            return false;
        }

        return true;
    }

    /**
     * @return set of all projects
     */
    @SuppressWarnings("unchecked")
    public Set<String> getProjects() {
        JSONObject projects = this.getProjectsObj();
        if (projects == null) {
            return null;
        }

        return projects.keySet();
    }

    private JSONObject getProjectsObj() {
        return ((JSONObject) this.getJsonObject().get("projects"));
    }

    /**
     * Set extension
     * 
     * @param extension
     *            extension to set
     */
    @SuppressWarnings("unchecked")
    public void setPublicationExtension(String extension) {
        this.getJsonObject().put(
                PropertiesName.PUBLICATION_EXTENSION.toString(), extension);
    }

    /**
     * @return publication extension
     */
    public String getPublicationExtension() {
        return (String) this.getJsonObject()
                .get(PropertiesName.PUBLICATION_EXTENSION.toString());
    }

    @SuppressWarnings("unchecked")
    public void setLastChapter(String projectName, String lastChapter) {
        if (!this.hasProject(projectName)) {
            return;
        }

        JSONObject projectObj = this.getProjectsObj();

        projectObj.put(PropertiesName.LAST_CHAPTER.toString(), lastChapter);
    }

    public String getLastChapter(String projectName) {
        if (!this.hasProject(projectName)) {
            return null;
        }

        JSONObject projectObj = this.getProjectsObj();
        if (!projectObj.containsKey(PropertiesName.LAST_CHAPTER.toString())) {
            return "";
        }

        return ((String) projectObj
                .get(PropertiesName.LAST_CHAPTER.toString()));
    }

    /**
     * @return path to folder of the json file
     */
    protected abstract String getFolderPath();
}
