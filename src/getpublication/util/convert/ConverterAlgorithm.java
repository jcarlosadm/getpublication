package getpublication.util.convert;

import java.io.File;
import java.io.IOException;

public interface ConverterAlgorithm {
    public boolean convert(File file, ImageFormats outputFormat) throws IOException;
}
