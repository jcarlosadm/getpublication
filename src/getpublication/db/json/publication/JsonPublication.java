package getpublication.db.json.publication;

import java.util.Set;

import org.json.simple.JSONObject;

import getpublication.db.json.JsonBasicOperations;

public abstract class JsonPublication extends JsonBasicOperations {
    
    /**
     * @param projectName project to add
     */
    @SuppressWarnings("unchecked")
    public void addProject(String projectName){
        if (!this.hasProject(projectName)) {
            this.getJsonObject().put(projectName, new JSONObject());
        }
    }
    
    /**
     * @param projectName project to remove
     * @return true if success
     */
    public boolean removeProject(String projectName){
        if (!this.hasProject(projectName)) {
            return false;
        }

        this.getJsonObject().remove(projectName);
        return true;
    }
    
    /**
     * add project property
     * @param projectName project name
     * @param propertyName property name
     * @param propertyValue property value
     */
    @SuppressWarnings("unchecked")
    public void addProjectProperty(String projectName, PropertiesName propertyName,
            String propertyValue){
        if (!this.hasProject(projectName)) {
            return;
        }
        JSONObject obj = (JSONObject) this.getJsonObject().get(projectName);
        obj.put(propertyName.toString(), propertyValue);
    }
    
    /**
     * remove project property
     * @param projectName project name
     * @param propertyName property name
     * @return true if success
     */
    public boolean removeProjectProperty(String projectName, PropertiesName propertyName){
        if (!this.hasProjectProperty(projectName, propertyName)) {
            return false;
        }

        JSONObject obj = (JSONObject) this.getJsonObject().get(projectName);
        obj.remove(propertyName.toString());
        return true;
    }
    
    /**
     * get project property
     * @param projectName project name
     * @param propertyName property name
     * @return value of project property
     */
    public String getProjectProperty(String projectName, PropertiesName propertyName){
        if (!this.hasProjectProperty(projectName, propertyName)) {
            return "";
        }
        JSONObject obj = (JSONObject) this.getJsonObject().get(projectName);
        return ((String) obj.get(propertyName.toString()));
    }
    
    /**
     * Check if project exists
     * @param project project name
     * @return true if this project exists
     */
    public boolean hasProject(String project){
        if (!this.getJsonObject().containsKey(project)) {
            return false;
        }
        return true;
    }
    
    /**
     * Check if project property exists
     * @param project project name
     * @param property property name
     * @return true if project property exists
     */
    public boolean hasProjectProperty(String project, PropertiesName property){
        if (!this.getJsonObject().containsKey(project)) {
            return false;
        }

        JSONObject obj = (JSONObject) this.getJsonObject().get(project);

        if (!obj.containsKey(property.toString())) {
            return false;
        }

        return true;
    }
    
    /**
     * @return set of all projects
     */
    @SuppressWarnings("unchecked")
    public Set<String> getProjects(){
        return this.getJsonObject().keySet();
    }
}
