package getpublication.project.chapter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import getpublication.folders.DownloadFolder;
import getpublication.folders.UserFolder;
import getpublication.json.publication.JsonPublication;
import getpublication.util.PageProgressPrinter;
import getpublication.util.convert.ConvertThread;
import getpublication.util.downloader.Downloader;
import getpublication.util.downloader.DownloaderThread;
import getpublication.util.joinfiles.GetJoinFilesInstance;
import getpublication.util.joinfiles.JoinFiles;
import getpublication.util.joinfiles.PublicationExtension;

public abstract class Chapter {
    private static final int NUMBER_OF_THREADS = 4;

    private static final String DEFAULT_EXTENSION = PublicationExtension.CBZ
            .toString();

    private List<String> urlStringList = new ArrayList<>();

    protected String name;
    
    protected boolean convertImages = true;

    public Chapter(String name, boolean convertImages) {
        this.name = name.replaceAll("/", "_");
        this.convertImages = convertImages;
    }

    public void addUrlStringList(List<String> urlStringList) {
        this.urlStringList = urlStringList;
    }

    public void download(DownloadFolder downloadFolder) {
        System.out.println("downloading chapter " + this.name);

        List<String> fileList = this.downloadImageFiles();
        if (convertImages == true) {
            this.convertImageFiles(fileList);
        }
        String JoinedFilename = this.joinFiles(fileList);
        this.moveJoinedFileToDownloadFolder(JoinedFilename, downloadFolder);
        this.deleteTempFiles(fileList);

        System.out.println("chapter finished");
    }

    private List<String> downloadImageFiles() {
        String tempFolder = UserFolder.getPathToTempFolder();
        List<String> fileList = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();
        
        PageProgressPrinter dPrinter = PageProgressPrinter.getInstance();
        dPrinter.setTotalPages(this.urlStringList.size());
        dPrinter.resetCounters();
        dPrinter.setMessageBefore("Download Progress");

        int page = 0;
        for (String urlString : this.urlStringList) {
            page += 1;

            threads.add(new Thread(new DownloaderThread(urlString, page,
                    this.urlStringList.size(), tempFolder, this)));
            
            if (threads.size() >= NUMBER_OF_THREADS) {
                this.executeThread(threads);
            }
        }
        
        if (threads.size() > 0) {
            this.executeThread(threads);
        }
        
        dPrinter.newLine();

        File tempFolderFolder = new File(tempFolder);
        File[] files = tempFolderFolder.listFiles();
        for (File file : files) {
            if (!file.isDirectory()) {
                fileList.add(file.getAbsolutePath());
            }
        }
        
        Collections.sort(fileList);

        float percent = 100.0f
                * ((float) fileList.size() / this.urlStringList.size());
        System.out.println(String.format("%.2f", percent)
                + "% of pages successfully downloaded");

        return fileList;
    }

    private void convertImageFiles(List<String> fileList) {

        List<Thread> threads = new ArrayList<>();
        List<String> filesToConvert = new ArrayList<>();
        
        for (int index = 0; index < fileList.size(); index++) {
            String filename = fileList.get(index);
            String extension = FilenameUtils.getExtension(filename);
            
            if (extension.equals("webp")) {
                filesToConvert.add(filename);
            }
        }
        
        PageProgressPrinter cPrinter = PageProgressPrinter.getInstance();
        cPrinter.setTotalPages(filesToConvert.size());
        cPrinter.resetCounters();
        cPrinter.setMessageBefore("Convert Progress");
        
        for (String filename : filesToConvert) {
            threads.add(new Thread(new ConvertThread(filename)));
            if (threads.size() >= NUMBER_OF_THREADS) {
                this.executeThread(threads);
            }
        }
        
        if (threads.size() > 0) {
            this.executeThread(threads);
        }
        
        cPrinter.newLine();

        if (!fileList.isEmpty()) {
            String folderPath = fileList.get(0);
            folderPath = folderPath.substring(0,
                    folderPath.lastIndexOf(File.separator));
            File folder = new File(folderPath);
            File[] list = folder.listFiles();
            fileList.clear();
            for (File file : list) {
                if (!file.isDirectory()) {
                    fileList.add(file.getAbsolutePath());
                }
            }
            
            Collections.sort(fileList);
        }
    }

    private void executeThread(List<Thread> threads) {
        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        threads.clear();
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

    public abstract void fixUrl(Downloader downloader);

    protected abstract JsonPublication getJsonPublication();
}
