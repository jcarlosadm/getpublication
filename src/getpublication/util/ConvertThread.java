package getpublication.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;

public class ConvertThread implements Runnable {

    private String filename = "";

    public ConvertThread(String filename) {
        this.filename = filename;
    }

    @Override
    public void run() {
        String extension = FilenameUtils.getExtension(filename);
        if (extension.equals("webp")) {
            try {
                File file = new File(filename);
                ConvertImage.convert(file, "png");
                file.delete();
                System.out.print("\rfile " + FilenameUtils.getName(filename)
                        + " converted from webp to png");
            } catch (IOException e) {
                System.out.println("\rfail to convert file " + filename);
            }
        }
    }

}
