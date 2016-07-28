package getpublication.util.downloader;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;

public class Downloader {

    private URL url = null;

    public void setUrl(String urlString) throws MalformedURLException {
        urlString = urlString.replaceAll(" ", "%20");
        this.url = new URL(urlString);
    }
    
    public String getUrlString(){
        return this.url.toString();
    }

    public void run(File outputFile) throws IOException {
        FileUtils.copyURLToFile(this.url, outputFile);
    }
    
}
