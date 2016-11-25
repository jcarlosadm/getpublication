package getpublication.parser;

import java.util.Map;

public abstract class HtmlSearchParser extends HtmlParser {
    
    private String backupUrlString = "";
    
    public HtmlSearchParser(String urlSiteString, boolean anonymousMode) {
        super(urlSiteString, anonymousMode);
    }
    
    public Map<String, HtmlNewsObjectInfo> getSearchResult(String searchTerm) {
        this.backupUrlString = this.urlSiteString;
        
        searchTerm = searchTerm.replace(" ", "%20");
        this.changeUrlString(searchTerm);
        if (!this.connect()) {
            this.urlSiteString = this.backupUrlString;
            this.doc = null;
            return null;
        }
        
        Map<String, HtmlNewsObjectInfo> searchResult = this.getSearch();
        
        this.urlSiteString = this.backupUrlString;
        this.doc = null;
        
        return searchResult;
    }
    
    protected abstract void changeUrlString(String searchTerm);
    
    protected abstract Map<String, HtmlNewsObjectInfo> getSearch();
    
}
