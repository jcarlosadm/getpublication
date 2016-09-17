package getpublication.util.convert;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;

import getpublication.util.pageProgress.PageProgressPrinter;

public class ConvertThread implements Runnable {

    private String filename = "";
    
    private ConverterAlgorithm converterAlgorithm = null;

    public ConvertThread(String filename) {
        this.filename = filename;
    }
    
    public void setConvertAlgorithm (ConverterAlgorithm cAlgorithm){
        this.converterAlgorithm = cAlgorithm;
    }

    @Override
    public void run() {
        String extension = FilenameUtils.getExtension(filename);
        PageProgressPrinter cPrinter = PageProgressPrinter.getInstance();
        
        if (extension.equals("webp")) {
            try {
                File file = new File(filename);
                ConvertImage.setConverterAlgorithm(this.converterAlgorithm);
                ConvertImage.convert(file, ImageFormats.PNG);
                file.delete();
                cPrinter.printProgress(1);
            } catch (IOException e) {
                System.out.println("\rfail to convert file " + filename);
            }
        }
    }

}
