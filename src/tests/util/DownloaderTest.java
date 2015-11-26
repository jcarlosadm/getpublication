package tests.util;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.Test;

import getpublication.util.Downloader;

public class DownloaderTest {

    private static final String FAIL_TEST_MESSAGE = "define a valid url";
    private static final String FILE_PATH = "testDownloadFile.webp";
    private static final String URL_STRING = "http://img.mangahost.com/br/images/"
            +"nisekoi-br/134/AS_CD_NSK_01.jpg.webp";

    @Test
    public void testSetUrl() {
        Downloader downloader = new Downloader();
        String urlString = URL_STRING;
        try {
            downloader.setUrl(urlString);
            assertEquals(downloader.getUrlString(), urlString);
        } catch (MalformedURLException e) {
            fail(FAIL_TEST_MESSAGE);
        }
    }
    
    @Test
    public void testRun() {
        Downloader downloader = new Downloader();
        String urlString = URL_STRING;
        File file = new File(FILE_PATH);
        try {
            downloader.setUrl(urlString);
            downloader.run(file);
            assertTrue(file.exists());
            file.delete();
        } catch (IOException e) {
            fail(FAIL_TEST_MESSAGE);
        }
    }

}
