package getpublication.parser;

import java.util.List;
import java.util.Map;

public abstract class HtmlNewsParser extends HtmlParser {

    public HtmlNewsParser(String urlSiteString, boolean anonymousMode) {
        super(urlSiteString, anonymousMode);
    }

    public Map<String, HtmlNewsObjectInfo> getNewsListByProject() {
        if (!this.connect()) {
            return null;
        }

        return this.getMap();
    }

    public List<HtmlNewsObjectInfo> getNewsList() {
        if (!this.connect()) {
            return null;
        }

        return this.getList();
    }

    protected abstract Map<String, HtmlNewsObjectInfo> getMap();

    protected abstract List<HtmlNewsObjectInfo> getList();
}
