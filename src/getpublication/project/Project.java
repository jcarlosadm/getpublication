package getpublication.project;

import java.io.File;
import java.util.List;

import getpublication.folders.DownloadFolder;
import getpublication.folders.UserFolder;
import getpublication.parser.HtmlChapterParser;
import getpublication.project.chapter.Chapter;
import getpublication.util.convert.ConverterAlgorithm;
import getpublication.util.logs.LogFileWriter;

public abstract class Project {

    private String urlString;

    private String name;

    protected boolean anonymousMode = false;

    private List<String> chapterNames = null;

    private ConverterAlgorithm converterAlgorithm = null;

    public Project(String name, String urlPart, boolean anonymousMode) {
        this.name = name.replaceAll("/", "_");
        this.urlString = this.generateUrlString(urlPart);
        this.anonymousMode = anonymousMode;
    }

    public void setConvertImageAlgorithm(ConverterAlgorithm cAlgorithm) {
        this.converterAlgorithm = cAlgorithm;
    }

    public String getName() {
        return this.name;
    }

    public String getUrlString() {
        return this.urlString;
    }

    public boolean getAnonymousMode() {
        return this.anonymousMode;
    }

    public boolean chapterExists(String chapter) {
        this.checkChapterNameListExists();

        return (this.chapterNames == null ? false
                : this.chapterNames.contains(chapter));
    }

    public List<String> getAllChapterNames() {
        this.checkChapterNameListExists();

        return this.chapterNames;
    }

    public boolean downloadChapter(String chapterName, DownloadFolder folder,
            boolean convertChapter) {
        this.checkChapterNameListExists();
        LogFileWriter logFileWriter = LogFileWriter.getInstance(null);

        HtmlChapterParser htmlChapterParser = this.getHtmlParser(chapterName);
        if (!htmlChapterParser.connect()) {
            try {
                logFileWriter.writeLog("failed to connect to " + chapterName);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }

        String title = htmlChapterParser.getTitle();
        Chapter chapter = this.getChapterGenerator(title, convertChapter);
        chapter.setConvertImageAlgorithm(this.converterAlgorithm);
        String finalFileName = chapter.getFinalFileName();

        this.checkFileInTempFolder(finalFileName);
        if (this.checkFileInDownloadFolder(folder, finalFileName)) {
            System.out.println("chapter already exists");
            try {
                logFileWriter
                        .writeLog("chapter " + chapterName + " already exists");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }

        List<String> urlStrings = htmlChapterParser.getUrlStrings();
        if (urlStrings == null) {
            System.out.println(
                    "no urls found in " + htmlChapterParser.getUrlSiteString());
            try {
                logFileWriter.writeLog("no urls found in "
                        + htmlChapterParser.getUrlSiteString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }

        chapter.addUrlStringList(urlStrings);
        chapter.download(folder);

        return true;
    }

    private void checkChapterNameListExists() {
        if (this.chapterNames == null) {
            this.chapterNames = this.generateAllChapterNames();
        }
    }

    private boolean checkFileInDownloadFolder(DownloadFolder folder,
            String title) {
        File file = new File(folder.getPath() + File.separator + title);
        if (file.exists()) {
            return true;
        }

        return false;
    }

    private void checkFileInTempFolder(String title) {
        File file = new File(
                UserFolder.getPathToTempFolder() + File.separator + title);
        if (file.exists()) {
            file.delete();
        }
    }

    protected abstract String generateUrlString(String urlPart);

    protected abstract List<String> generateAllChapterNames();

    protected abstract Chapter getChapterGenerator(String title,
            boolean convertChapter);

    protected abstract HtmlChapterParser getHtmlParser(String chapterName);
}
