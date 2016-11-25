package getpublication.json.publication;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
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
    public List<String> getProjects() {
        JSONObject projects = this.getProjectsObj();
        if (projects == null) {
            return null;
        }
        
        List<String> list = new ArrayList<>(projects.keySet());
        List<String> orderingList = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        
        for (String projectName : list) {
            orderingList.add(projectName.toLowerCase());
            map.put(projectName.toLowerCase(), projectName);
        }
        
        Collections.sort(orderingList);
        list.clear();
        
        for (String string : orderingList) {
            list.add(map.get(string));
        }

        return list;
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
    public void setConvertImagesProperty(boolean value) {
        this.getJsonObject().put(PropertiesName.CONVERT_IMAGES.toString(), (new Boolean(value)).toString());
    }
    
    public boolean getConvertImagesProperty() {
        String string = (String) this.getJsonObject().get(PropertiesName.CONVERT_IMAGES.toString());
        
        boolean value = true;
        try {
            value = Boolean.parseBoolean(string);
        } catch (Exception e) {
        }
        
        return value;
    }

    @SuppressWarnings("unchecked")
    public void setLastChapter(String projectName, String lastChapter) {
        if (!this.hasProject(projectName)) {
            return;
        }

        JSONObject projectObj = this.getProjectsObj();
        JSONObject project = (JSONObject) projectObj.get(projectName);

        project.put(PropertiesName.LAST_CHAPTER.toString(), lastChapter);
    }

    public String getLastChapter(String projectName) {
        if (!this.hasProject(projectName)) {
            return null;
        }

        JSONObject projectObj = this.getProjectsObj();
        JSONObject project = (JSONObject) projectObj.get(projectName);
        if (!project.containsKey(PropertiesName.LAST_CHAPTER.toString())) {
            return "";
        }

        return ((String) project
                .get(PropertiesName.LAST_CHAPTER.toString()));
    }
    
    @SuppressWarnings("unchecked")
    public void addChapter(String projectName, String chapter) {
        if (!this.hasProject(projectName)) {
            return;
        }
        
        JSONObject projectObj = this.getProjectsObj();
        JSONObject project = (JSONObject) projectObj.get(projectName);
        
        JSONArray jsonArray = null;
        
        if (project.containsKey(PropertiesName.CHAPTERS.toString())) {
            jsonArray = (JSONArray) project.get(PropertiesName.CHAPTERS.toString());
        } else {
            jsonArray = new JSONArray();
        }
        
        jsonArray.add(chapter);
        project.put(PropertiesName.CHAPTERS.toString(), jsonArray);
    }
    
    @SuppressWarnings("unchecked")
    public void replaceChapters(String projectName, List<String> chapters) {
        if (!this.hasProject(projectName)) {
            return;
        }
        
        JSONObject projectObj = this.getProjectsObj();
        JSONObject project = (JSONObject) projectObj.get(projectName);
        
        JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(chapters);
        
        project.put(PropertiesName.CHAPTERS.toString(), jsonArray);
    }
    
    
    public List<String> getChapters(String projectName) {
        if (!this.hasProject(projectName)) {
            return null;
        }
        
        List<String> list = new ArrayList<>();

        JSONObject projectObj = this.getProjectsObj();
        JSONObject project = (JSONObject) projectObj.get(projectName);
        
        if (!project.containsKey(PropertiesName.CHAPTERS.toString())) {
            return list;
        }
        
        JSONArray jsonArray = (JSONArray) project.get(PropertiesName.CHAPTERS.toString());
        @SuppressWarnings("unchecked")
        Iterator<String> iterator = jsonArray.iterator();
        
        while(iterator.hasNext()) {
            list.add((String)iterator.next());
        }
        
        return list;
    }
    
    public void addFavProject(String projectName) {
        this.modFavProject(projectName, true);
    }
    
    public void removeFavProject(String projectName) {
        this.modFavProject(projectName, false);
    }
    
    @SuppressWarnings("unchecked")
    private void modFavProject(String projectName, boolean add) {
        if (!this.hasProject(projectName)) {
            return;
        }
        
        JSONObject jsonObject = this.getJsonObject();
        JSONArray jsonArray = null;
        if (!jsonObject.containsKey(PropertiesName.FAV_PROJECTS.toString())) {
            jsonArray = new JSONArray();
        } else {
            jsonArray = (JSONArray) jsonObject.get(PropertiesName.FAV_PROJECTS.toString());
        }
        
        if (!jsonArray.contains(projectName) && add == true) {
            jsonArray.add(projectName);
        } else if(jsonArray.contains(projectName) && add == false) {
            jsonArray.remove(projectName);
        }
        
        jsonObject.put(PropertiesName.FAV_PROJECTS.toString(), jsonArray);
    }
    
    @SuppressWarnings("unchecked")
    public List<String> getFavProjects() {
        List<String> favProjects = new ArrayList<>();
        
        JSONObject jsonObject = this.getJsonObject();
        if (jsonObject.containsKey(PropertiesName.FAV_PROJECTS.toString())) {
            JSONArray jsonArray = (JSONArray) jsonObject.get(PropertiesName.FAV_PROJECTS.toString());
            
            favProjects.addAll(jsonArray);
        }
        
        List<String> tempList = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        for (String string : favProjects) {
            tempList.add(string.toLowerCase());
            map.put(string.toLowerCase(), string);
        }
        
        Collections.sort(tempList);
        favProjects.clear();
        for (String string : tempList) {
            favProjects.add(map.get(string));
        }
        
        return favProjects;
    }

    /**
     * @return path to folder of the json file
     */
    protected abstract String getFolderPath();
}
