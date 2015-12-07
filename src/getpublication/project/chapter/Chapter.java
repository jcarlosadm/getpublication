package getpublication.project.chapter;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import getpublication.folders.DownloadFolder;
import getpublication.folders.UserFolder;
import getpublication.json.publication.JsonPublication;
import getpublication.util.ConvertImage;
import getpublication.util.Downloader;
import getpublication.util.joinfiles.GetJoinFilesInstance;
import getpublication.util.joinfiles.JoinFiles;
import getpublication.util.joinfiles.PublicationExtension;

public abstract class Chapter {
    private static final String DEFAULT_EXTENSION = PublicationExtension.CBZ
            .toString();

    private List<String> urlStringList = new ArrayList<>();

    protected String name;

    public Chapter(String name) {
        this.name = name;
    }

    public void addUrlStringList(List<String> urlStringList) {
        this.urlStringList = urlStringList;
    }

    public void download(DownloadFolder downloadFolder) {
        System.out.println("downloading chapter " + this.name);

        List<String> fileList = this.downloadImageFiles();
        this.convertImageFiles(fileList);
        String JoinedFilename = this.joinFiles(fileList);
        this.moveJoinedFileToDownloadFolder(JoinedFilename, downloadFolder);
        this.deleteTempFiles(fileList);

        System.out.println("chapter finished");
    }

    private List<String> downloadImageFiles() {
        Downloader downloader = new Downloader();
        String tempFolder = UserFolder.getPathToTempFolder();
        List<String> fileList = new ArrayList<>();
        List<String> errorPages = new ArrayList<>();

        int page = 0;
        for (String urlString : this.urlStringList) {
            page += 1;

            String extension = FilenameUtils.getExtension(urlString);
            String filename = tempFolder + File.separator
                    + String.format("%04d", page) + "." + extension;

            File outputFile = new File(filename);
            try {
                downloader.setUrl(urlString);
            } catch (MalformedURLException e) {
                System.out.println("invalid url (page " + page + " of "
                        + this.urlStringList.size() + ")");
                continue;
            }

            boolean success = this.downloadImage(downloader, outputFile, true);
            success = (success ? true
                    : this.downloadImage(downloader, outputFile, false));
            if (success) {
                fileList.add(filename);
                System.out.print("\rsuccess ");
            } else {
                errorPages.add(urlString);
                System.out.print("\rfail ");
            }
            System.out.print(
                    "(page " + page + " of " + this.urlStringList.size() + ")");
        }
        System.out.println();
        
        float percent = 100.0f * ((float) fileList.size() / this.urlStringList.size());
        System.out.println(String.format("%.2f", percent) + "% of pages successfully downloaded");
        if (!errorPages.isEmpty()) {
            System.out.println("pages not downloaded:");
            for (String errorPage : errorPages) {
                System.out.println(errorPage);
            }
        }
        
        return fileList;
    }

    private boolean downloadImage(Downloader downloader, File outputFile,
            boolean fixUrl) {
        try {
            downloader.run(outputFile);
        } catch (IOException e) {
            if (fixUrl) {
                System.out.println("error to download. fixing url...");
                this.fixUrl(downloader);
            } else {
                System.out.println("error to download.");
            }
            return false;
        }
        return true;
    }

    private void convertImageFiles(List<String> fileList) {
        for (int index = 0; index < fileList.size(); index++) {
            String filename = (String) fileList.get(index);

            String extension = FilenameUtils.getExtension(filename);
            if (extension.equals("webp")) {
                try {
                    File file = new File(filename);
                    ConvertImage.convert(file, "png");
                    fileList.set(index,
                            FilenameUtils.removeExtension(filename) + ".png");
                    file.delete();
                    System.out.print("\rfile " + FilenameUtils.getName(filename)
                            + " converted to "
                            + FilenameUtils.getName(fileList.get(index)));
                } catch (IOException e) {
                    System.out.println("\rfail to convert file " + filename);
                }
            }
        }
        System.out.println();
    }

    private void moveJoinedFileToDownloadFolder(String joinedFilename,
            DownloadFolder downloadFolder) {
        File srcFile = new File(joinedFilename);
        if (!srcFile.exists()) {
            return;
        }

        File destFile = new File(downloadFolder.getPath() + File.separator
                + FilenameUtils.getName(joinedFilename));
        try {
            FileUtils.moveFile(srcFile, destFile);
            System.out.println("File " + FilenameUtils.getName(joinedFilename)
                    + " moved to download folder: " + downloadFolder.getPath());
        } catch (IOException e) {
            System.out.println("error to move joined file");
        }
    }

    private void deleteTempFiles(List<String> fileList) {
        for (String filename : fileList) {
            (new File(filename)).delete();
        }
        System.out.println("All temp files deleted");
    }

    public String getFinalFileName() {
        JsonPublication jPublication = this.getJsonPublication();
        jPublication.load();
        String extension = jPublication.getPublicationExtension();
        if (extension == null || extension.equals("")) {
            extension = DEFAULT_EXTENSION;
        }

        return (this.name.replace(' ', '_') + "." + extension);
    }

    private String joinFiles(List<String> fileList) {
        JsonPublication jsonPublication = this.getJsonPublication();
        jsonPublication.load();
        String extension = jsonPublication.getPublicationExtension();
        if (extension == null || extension.equals("")) {
            extension = DEFAULT_EXTENSION;
        }

        JoinFiles joinFiles = GetJoinFilesInstance.getInstance(extension);

        if (joinFiles == null) {
            return "";
        }

        if (joinFiles.join(fileList, this.getFinalFileName()) == true) {
            return UserFolder.getPathToTempFolder() + File.separator
                    + this.getFinalFileName();
        }

        return "";
    }

    protected abstract void fixUrl(Downloader downloader);

    protected abstract JsonPublication getJsonPublication();
}
