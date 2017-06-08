package getpublication.parser;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import getpublication.util.AnonymousUrlModifier;

public abstract class HtmlParser {
    
    protected Document doc = null;

    protected String urlSiteString = "";

    protected boolean anonymousMode = false;
    
    public HtmlParser(String urlSiteString, boolean anonymousMode) {
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
            this.doc = Jsoup.connect(this.urlSiteString).userAgent("Mozilla").get();
        } catch (IOException e) {
        	e.printStackTrace();
            System.out.println("unable to connect to "+this.urlSiteString);
            this.doc = null;
            return false;
        }

        return true;
    }
}
