package getpublication.project;

import java.io.File;
import java.util.List;

import getpublication.folders.DownloadFolder;
import getpublication.folders.UserFolder;
import getpublication.parser.HtmlChapterParser;
import getpublication.project.chapter.Chapter;

public abstract class Project {

    private String urlString;

    private String name;

    protected boolean anonymousMode = false;

    private List<String> chapterNames = null;

    public Project(String name, String urlPart, boolean anonymousMode) {
        this.name = name;
        this.urlString = this.generateUrlString(urlPart);
        this.anonymousMode = anonymousMode;
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

        return (this.chapterNames == null ? false : this.chapterNames.contains(chapter));
    }

    public List<String> getAllChapterNames() {
        this.checkChapterNameListExists();

        return this.chapterNames;
    }

    public boolean downloadChapter(String chapterName, DownloadFolder folder) {
        this.checkChapterNameListExists();

        HtmlChapterParser htmlChapterParser = this.getHtmlParser(chapterName);
        if (!htmlChapterParser.connect()) {
            return false;
        }

        String title = htmlChapterParser.getTitle();
        Chapter chapter = this.getChapterGenerator(title);
        String finalFileName = chapter.getFinalFileName();
        
        this.checkFileInTempFolder(finalFileName);
        if (this.checkFileInDownloadFolder(folder, finalFileName)) {
            System.out.println("chapter already exists");
            return true;
        }

        List<String> urlStrings = htmlChapterParser.getUrlStrings();
        if (urlStrings == null) {
            System.out.println(
                    "no urls found in " + htmlChapterParser.getUrlSiteString());
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
            String title){
        File file = new File(folder.getPath() + File.separator
                + title);
        if (file.exists()) {
            return true;
        }

        return false;
    }

    private void checkFileInTempFolder(String title){
        File file = new File(UserFolder.getPathToTempFolder() + File.separator
                + title);
        if (file.exists()) {
            file.delete();
        }
    }

    protected abstract String generateUrlString(String urlPart);

    protected abstract List<String> generateAllChapterNames();

    protected abstract Chapter getChapterGenerator(String title);

    protected abstract HtmlChapterParser getHtmlParser(String chapterName);
}
