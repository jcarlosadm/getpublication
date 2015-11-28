package getpublication.parser.mangahost;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import getpublication.parser.HtmlChapterParser;
import getpublication.util.AnonymousUrlModifier;

public class MangahostChapterParser extends HtmlChapterParser {

    public MangahostChapterParser(String urlSiteString, boolean anonymousMode) {
        super(urlSiteString, anonymousMode);
    }

    @Override
    protected String parseAndGetTitle(Document doc) {
        return doc.select("meta[property=og:title]").get(0).attr("content");
    }

    @Override
    protected List<String> parseAndGetUrlStrings(Document doc,
            boolean anonymousMode) {
        Elements elements = doc.getElementsByTag("script");
        String text = null;
        String[] lines = null;
        for (Element element : elements) {
            for (DataNode dataNode : element.dataNodes()) {
                if (dataNode.getWholeData().contains("var pages = ")) {
                    lines = dataNode.getWholeData().split("\\r?\\n");
                    break;
                }
            }
        }
        
        for (String line : lines) {
            if (line.contains("var pages = ")) {
                int beginIndex = line.indexOf("var pages = ") + "var pages = ".length();
                int finalIndex = line.indexOf("}];", beginIndex) + "}];".length() - 1;
                text = line.substring(beginIndex, finalIndex);
                break;
            }
        }
        
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = null;
        try {
             jsonArray = (JSONArray) parser.parse(text);
        } catch (ParseException e) {
            System.out.println("failed to parse string to json array");
            return null;
        }
        
        List<String> urlStrings = new ArrayList<>();
        for (Object object : jsonArray) {
            JSONObject jsonObject = (JSONObject) object;
            
            String url = (String) jsonObject.get("url");
            if (anonymousMode) {
                url = AnonymousUrlModifier.modifyUrlString(url);
            }
            urlStrings.add(url);
        }
        
        return urlStrings;
    }
    
    /* TODO remove
     * public static void main(String[] args) {
        MangahostChapterParser mParser = new MangahostChapterParser
                ("http://br.mangahost.com/manga/fairy-tail-br/442", false);
        
        System.out.println(mParser.getTitle());
        
        List<String> urlStrings = mParser.getUrlStrings();
        if (urlStrings != null) {
            for (String urlString : urlStrings) {
                System.out.println(urlString);
            }
        }
    }*/
}
