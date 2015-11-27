package getpublication.project.chapter.mangahost;

import java.io.File;
import java.net.MalformedURLException;
import java.util.List;

import org.apache.commons.io.FilenameUtils;

import getpublication.folders.UserFolder;
import getpublication.project.chapter.Chapter;
import getpublication.util.Downloader;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

public class MangahostChapter extends Chapter {

    private static final String FINAL_EXTENSION = ".png.webp";
    private static final String STRING_TO_BE_SET = "images";
    private static final String STRING_TO_BE_REPLACED = "mangas_files";
    private static final String MIDDLE_EXTENSION_TO_BE_SET = ".png.";
    private static final String MIDDLE_EXTENSION_TO_BE_REPLACED = ".webp.";

    public MangahostChapter(String name) {
        super(name);
    }

    @Override
    protected void fixUrl(Downloader downloader) {
        String urlString = downloader.getUrlString();

        if (urlString.contains(MIDDLE_EXTENSION_TO_BE_REPLACED)) {
            urlString = urlString.replace(MIDDLE_EXTENSION_TO_BE_REPLACED,
                    MIDDLE_EXTENSION_TO_BE_SET);
        } else if (urlString.contains(STRING_TO_BE_REPLACED)) {
            urlString = urlString.replace(STRING_TO_BE_REPLACED,
                    STRING_TO_BE_SET);
            if (!urlString.contains(FINAL_EXTENSION)) {
                String extension = FilenameUtils.getExtension(urlString);
                urlString = urlString.replace("." + extension, FINAL_EXTENSION);
            }
        }

        try {
            downloader.setUrl(urlString);
        } catch (MalformedURLException e) {
            System.out.println("invalid url");
        }
    }

    @Override
    protected String joinFiles(List<String> fileList) {
        String path = UserFolder.getPathToTempFolder() + File.separator
                + this.name.replace(' ', '_') + ".cbz";

        try {
            ZipFile zipFile = new ZipFile(path);

            ZipParameters zipParameters = new ZipParameters();
            zipParameters.setCompressionMethod(Zip4jConstants.COMP_STORE);
            zipParameters
                    .setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);

            for (String filename : fileList) {
                zipFile.addFile(new File(filename), zipParameters);
            }
        } catch (ZipException e) {
            System.out.println("failed to create zip file");
            return "";
        }

        return path;
    }
    
    /* TODO remove
     * public static void main(String[] args) {
        MangahostChapter mangahostChapter = new MangahostChapter("fairy tail 442");
        
        mangahostChapter.addUrlString(
                "http://img.mangahost.com/br/images/fairy-tail-br/442/442_02png.png.webp");
        mangahostChapter.addUrlString(
                "http://img.mangahost.com/br/images/fairy-tail-br/442/442_04png.png.webp");
        
        mangahostChapter.download(new DownloadFolder());
    }*/

}
