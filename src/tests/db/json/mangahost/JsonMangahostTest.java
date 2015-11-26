package tests.db.json.mangahost;

import static org.junit.Assert.*;

import org.junit.Test;

import getpublication.db.json.JsonPublication;
import getpublication.db.json.PropertiesName;
import getpublication.db.json.mangahost.JsonMangahost;

public class JsonMangahostTest {

    private static final String PROJECT_PROPERTY_TEST = "test";
    private static final String PROJECT_TEST = "projectTest~";

    @Test
    public void testAddProject() {
        JsonPublication json = new JsonMangahost();
        json.addProject(PROJECT_TEST);
        assertTrue(json.hasProject(PROJECT_TEST));
        json.removeProject(PROJECT_TEST);
    }

    @Test
    public void testRemoveProject() {
        JsonPublication json = new JsonMangahost();
        json.addProject(PROJECT_TEST);
        json.removeProject(PROJECT_TEST);
        assertTrue(!json.hasProject(PROJECT_TEST));
    }

    @Test
    public void testAddProjectProperty() {
        JsonPublication json = new JsonMangahost();
        json.addProject(PROJECT_TEST);
        json.addProjectProperty(PROJECT_TEST, PropertiesName.NAME_IN_URL,
                PROJECT_PROPERTY_TEST);
        assertTrue(json.hasProjectProperty(PROJECT_TEST, PropertiesName.NAME_IN_URL));
        json.removeProject(PROJECT_TEST);
    }

    @Test
    public void testRemoveProjectProperty() {
        JsonPublication json = new JsonMangahost();
        json.addProject(PROJECT_TEST);
        json.addProjectProperty(PROJECT_TEST, PropertiesName.NAME_IN_URL,
                PROJECT_PROPERTY_TEST);
        json.removeProjectProperty(PROJECT_TEST, PropertiesName.NAME_IN_URL);
        assertTrue(!json.hasProjectProperty(PROJECT_TEST, PropertiesName.NAME_IN_URL));
        json.removeProject(PROJECT_TEST);
    }

}
