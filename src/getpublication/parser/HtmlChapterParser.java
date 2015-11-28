package getpublication.parser;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import getpublication.util.AnonymousUrlModifier;

public abstract class HtmlChapterParser {

    private boolean anonymousMode = false;

    private Document doc = null;

    private String urlSiteString = "";

    public HtmlChapterParser(String urlSiteString, boolean anonymousMode) {
        this.urlSiteString = urlSiteString;
        this.anonymousMode = anonymousMode;
    }

    public boolean connect() {
        if (this.doc != null) {
            return true;
        }

        if (this.anonymousMode) {
            this.urlSiteString = AnonymousUrlModifier
                    .modifyUrlString(this.urlSiteString);
        }

        try {
            this.doc = Jsoup.connect(this.urlSiteString).get();
        } catch (IOException e) {
            System.out.println("unable to connect to "+this.urlSiteString);
            this.doc = null;
            return false;
        }

        return true;
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
