package getpublication.project;

import java.util.List;

import getpublication.folders.DownloadFolder;
import getpublication.parser.HtmlChapterParser;
import getpublication.project.chapter.Chapter;

public abstract class Project {

    private String urlString;

    private String name;

    private boolean anonymousMode = false;

    private List<String> chapterNames = null;

    public Project(String name, String urlPart, boolean anonymousMode) {
        this.name = name;
        this.urlString = this.generateUrlString(urlPart);
        this.anonymousMode = false;
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

        return this.chapterNames.contains(chapter);
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
        this.checkFileInTempFolder(title);
        if (this.checkFileInDownloadFolder(folder, title)) {
            System.out.println("chapter already exists");
            return true;
        }

        List<String> urlStrings = htmlChapterParser.getUrlStrings();
        if (urlStrings == null) {
            System.out.println(
                    "no urls found in " + htmlChapterParser.getUrlSiteString());
            return false;
        }

        Chapter chapter = this.getChapterGenerator(title);
        chapter.addUrlStringList(urlStrings);
        chapter.download(folder);

        return true;
    }

    

    private void checkChapterNameListExists() {
        if (this.chapterNames == null) {
            this.chapterNames = this.generateAllChapterNames();
        }
    }

    protected abstract String generateUrlString(String urlPart);

    protected abstract List<String> generateAllChapterNames();

    protected abstract Chapter getChapterGenerator(String title);

    protected abstract HtmlChapterParser getHtmlParser(String chapterName);
    
    protected abstract boolean checkFileInDownloadFolder(DownloadFolder folder,
            String title);

    protected abstract void checkFileInTempFolder(String title);
}
