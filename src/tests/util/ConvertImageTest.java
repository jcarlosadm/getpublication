package tests.util;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;
import org.junit.Test;

import getpublication.util.ConvertImage;
import getpublication.util.Downloader;

public class ConvertImageTest {

    
    private static final String EXTENSION_TOBE_CONVERTED = "jpg";
    private static final String OUTPUT_FILE_TEST = "outputFileTest.webp";
    private static final String URL_STRING = "http://img.mangahost.com/br/images/"
            +"nisekoi-br/134/AS_CD_NSK_01.jpg.webp";
    private static final String FAIL_FORMAT_MESSAGE = "define a valid format";
    private static final String FAIL_URL_MESSAGE = "define a valid url";

    @Test
    public void testConvert() {
        Downloader downloader = new Downloader();
        String urlString = URL_STRING;
        File outputFile = new File(OUTPUT_FILE_TEST);
        
        try {
            downloader.setUrl(urlString);
            downloader.run(outputFile);
        } catch (IOException e) {
            fail(FAIL_URL_MESSAGE);
        }
        
        try {
            assertTrue(ConvertImage.convert(outputFile, EXTENSION_TOBE_CONVERTED));
        } catch (IOException e) {
            fail(FAIL_FORMAT_MESSAGE);
        }
        
        outputFile.delete();
        File outputFile2 = new File(FilenameUtils.removeExtension(outputFile.getPath())
                +"."+EXTENSION_TOBE_CONVERTED);
        outputFile2.delete();
    }

}
