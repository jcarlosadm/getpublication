package tests.fixurl;

import static org.junit.Assert.*;

import org.junit.Test;

import getpublication.fixurl.FixUrlString;
import getpublication.fixurl.FixUrlStringMangahost;

public class FixUrlStringMangahostTest {

    private static final String URL1 = "http://mangahost.images.webp.webp";
    private static final String URL_EXPECTED_1 = "http://mangahost.images.png.webp";
    private static final String URL2 = "http://mangahost.mangas_files.01png.webp";
    private static final String URL_EXPECTED_2 = "http://mangahost.images.01png.png.webp";

    @Test
    public void testFix() {
        
        FixUrlString fix = new FixUrlStringMangahost();
        
        assertEquals(URL_EXPECTED_1, fix.fix(URL1));
        assertEquals(URL_EXPECTED_2, fix.fix(URL2));
        
    }

}
