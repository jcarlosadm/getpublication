package getpublication.util.convert;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;

public class ConvertImage {
    public static boolean convert(File file, String outputFormat)
            throws IOException {

        BufferedImage image = ImageIO.read(file);

        String outputFileName = FilenameUtils.removeExtension(file.getPath())
                + "." + outputFormat;

        return ImageIO.write(image, outputFormat, new File(outputFileName));
    }
}
