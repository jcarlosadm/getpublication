package getpublication.util;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.commons.io.FilenameUtils;

import getpublication.project.chapter.Chapter;

public class DownloaderThread implements Runnable {

    private String urlString = "";
    private Chapter chapter = null;
    private int page = 0;
    private int totalPages = 0;
    private String tempFolder = "";
    
    public DownloaderThread(String urlString, int page , int totalPages, String tempFolder, Chapter chapter) {
        this.urlString = urlString;
        this.chapter = chapter;
        this.page = page;
        this.totalPages = totalPages;
        this.tempFolder = tempFolder;
    }
    
    @Override
    public void run() {
        Downloader downloader = new Downloader();
        
        String extension = FilenameUtils.getExtension(this.urlString);
        String filename = this.tempFolder + File.separator
                + String.format("%04d", page) + "." + extension;

        File outputFile = new File(filename);
        try {
            downloader.setUrl(urlString);
        } catch (MalformedURLException e) {
            System.out.println("invalid url (page " + page + " of "
                    + this.totalPages + ")");
            return;
        }

        boolean success = this.downloadImage(downloader, outputFile, true);
        success = (success ? true
                : this.downloadImage(downloader, outputFile, false));
        if (success) {
            System.out.print("\rsuccess ");
        } else {
            System.out.print("\rfail ");
        }
        System.out.print(
                "(page " + page + " of " + this.totalPages + ")");
    }
    
    private boolean downloadImage(Downloader downloader, File outputFile,
            boolean fixUrl) {
        try {
            downloader.run(outputFile);
        } catch (IOException e) {
            if (fixUrl) {
                System.out.println("error to download. fixing url...");
                this.chapter.fixUrl(downloader);
            } else {
                System.out.println("error to download.");
            }
            return false;
        }
        return true;
    }

}
