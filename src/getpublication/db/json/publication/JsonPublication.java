package getpublication.db.json.publication;

import java.util.Set;

public interface JsonPublication {
    
    /**
     * @param projectName project to add
     */
    public void addProject(String projectName);
    
    /**
     * @param projectName project to remove
     * @return true if success
     */
    public boolean removeProject(String projectName);
    
    /**
     * add project property
     * @param projectName project name
     * @param propertyName property name
     * @param propertyValue property value
     */
    public void addProjectProperty(String projectName, PropertiesName propertyName,
            String propertyValue);
    
    /**
     * remove project property
     * @param projectName project name
     * @param propertyName property name
     * @return true if success
     */
    public boolean removeProjectProperty(String projectName, PropertiesName propertyName);
    
    /**
     * get project property
     * @param projectName project name
     * @param propertyName property name
     * @return value of project property
     */
    public String getProjectProperty(String projectName, PropertiesName propertyName);
    
    /**
     * Check if project exists
     * @param project project name
     * @return true if this project exists
     */
    public boolean hasProject(String project);
    
    /**
     * Check if project property exists
     * @param project project name
     * @param property property name
     * @return true if project property exists
     */
    public boolean hasProjectProperty(String project, PropertiesName property);
    
    /**
     * @return set of all projects
     */
    public Set<String> getProjects();
}
