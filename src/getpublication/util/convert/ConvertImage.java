package getpublication.util.convert;

import java.io.File;
import java.io.IOException;

public class ConvertImage {
    
    private static ConverterAlgorithm converterAlgorithm = null;
    
    public static void setConverterAlgorithm(ConverterAlgorithm algorithm) {
        converterAlgorithm = algorithm;
    }
    
    public static boolean convert(File file, ImageFormats outputFormat)
            throws IOException {

        if (converterAlgorithm != null) {
            return converterAlgorithm.convert(file, outputFormat);
        }
        
        return false;
    }
}
