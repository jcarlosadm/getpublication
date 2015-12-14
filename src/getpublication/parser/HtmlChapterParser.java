package getpublication.parser;

import java.util.List;

import org.jsoup.nodes.Document;

public abstract class HtmlChapterParser extends HtmlParser {

    public HtmlChapterParser(String urlSiteString, boolean anonymousMode) {
        super(urlSiteString, anonymousMode);
    }

    public String getTitle() {
        if (!this.connect()) {
            return "";
        }

        return this.parseAndGetTitle(this.doc);
    }

    public List<String> getUrlStrings() {
        if (!this.connect()) {
            return null;
        }

        return this.parseAndGetUrlStrings(this.doc, this.anonymousMode);
    }
    
    public String getUrlSiteString(){
        return this.urlSiteString;
    }

    protected abstract String parseAndGetTitle(Document doc);

    protected abstract List<String> parseAndGetUrlStrings(Document doc,
            boolean anonymousMode);

}
