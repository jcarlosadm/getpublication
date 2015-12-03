package tests.folders;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import getpublication.folders.UserFolder;

public class UserFolderTest {

    @Test
    public void testCreateFolders() {
        UserFolder userFolder = new UserFolder();
        userFolder.createFolders();
        
        assertTrue((new File(UserFolder.getPathToMainFolder()).exists()));
        assertTrue((new File(UserFolder.getPathToDbFolder()).exists()));
        assertTrue((new File(UserFolder.getPathToTempFolder()).exists()));
    }

}
