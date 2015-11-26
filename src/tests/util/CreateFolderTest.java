package tests.util;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import getpublication.util.CreateFolder;

public class CreateFolderTest {

    private static final String FOLDER_TEST = "folderTest";

    @Test
    public void test() {
        File folder = new File(FOLDER_TEST);
        CreateFolder.create(folder);
        assertTrue(folder.exists() && folder.isDirectory());
        folder.delete();
    }

}
