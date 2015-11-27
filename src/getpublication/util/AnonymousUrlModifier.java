package getpublication.util;

public class AnonymousUrlModifier {
    
    private static final String ANONYMOUS_SITE = 
            "http://anonymouse.org/cgi-bin/anon-www.cgi/";
    
    public static String modifyUrlString(String urlString){
        return ANONYMOUS_SITE+urlString;
    }
    
}
