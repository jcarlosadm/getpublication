package tests.folders;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import getpublication.folders.DownloadFolder;

public class DownloadFolderTest {

    private static final String PATH_TO_DOWNLOAD_FOLDER = System
            .getProperty("user.home") + File.separator + "Download";

    @Test
    public void testSetPathToDownloadFolder() {
        DownloadFolder downloadFolder = new DownloadFolder();

        downloadFolder.setPathToDownloadFolder(PATH_TO_DOWNLOAD_FOLDER);
        assertEquals(PATH_TO_DOWNLOAD_FOLDER,
                downloadFolder.getPathToDownloadFolder());
    }

}
