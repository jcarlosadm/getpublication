package getpublication.parser;

import java.util.Map;

public abstract class HtmlSearchParser extends HtmlParser {
    
    private String backupUrlString = "";
    
    public HtmlSearchParser(String urlSiteString, boolean anonymousMode) {
        super(urlSiteString, anonymousMode);
    }
    
    public Map<String, String> getSearchResult(String searchTerm) {
        this.backupUrlString = this.urlSiteString;
        
        searchTerm = searchTerm.replace(" ", "%20");
        this.changeUrlString(searchTerm);
        if (!this.connect()) {
            this.urlSiteString = this.backupUrlString;
            this.doc = null;
            return null;
        }
        
        Map<String, String> searchResult = this.getSearch();
        
        this.urlSiteString = this.backupUrlString;
        this.doc = null;
        
        return searchResult;
    }
    
    protected abstract void changeUrlString(String searchTerm);
    
    protected abstract Map<String, String> getSearch();
    
}
