package getpublication.project.mangahost;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import getpublication.parser.HtmlChapterParser;
import getpublication.parser.mangahost.MangahostChapterParser;
import getpublication.project.Project;
import getpublication.project.chapter.Chapter;
import getpublication.project.chapter.mangahost.MangahostChapter;
import getpublication.util.AnonymousUrlModifier;

public class MangahostProject extends Project {
    
    public MangahostProject(String name, String urlPart, boolean anonymousMode) {
        super(name, urlPart, anonymousMode);
    }
    
    @Override
    protected List<String> generateAllChapterNames(){
        List<String> chapterNameList = new ArrayList<>();
        
        Document doc = null;
        try {
            doc = Jsoup.connect(this.getUrlString()).get();
        } catch (IOException e) {
            System.out.println("unable to connect");
            return chapterNameList;
        }
        
        Elements elements = doc.getElementsByClass("btn-small");
        for (Element element : elements) {
            if (element.id() != "") {
                chapterNameList.add(element.id());
            }
        }
        
        return chapterNameList;
    }

    @Override
    protected String generateUrlString(String urlPart) {
        String urlString = "http://br.mangahost.com/manga/" + urlPart;
        if (this.getAnonymousMode()) {
            urlString = AnonymousUrlModifier.modifyUrlString(urlString);
        }
        
        return urlString;
    }

    @Override
    protected HtmlChapterParser getHtmlParser(String chapterName) {
        return new MangahostChapterParser
                (this.getUrlString()+"/"+chapterName, this.getAnonymousMode());
    }

    @Override
    protected Chapter getChapterGenerator(String title) {
        return new MangahostChapter(title);
    }
    
    
    /* TODO remove
     * public static void main(String[] args) {
        MangahostProject mProject = new MangahostProject("bleach", "bleach-br", false);
        if (mProject.downloadChapter("654", new DownloadFolder())) {
            System.out.println("yeah!");
        }
    }*/

}
