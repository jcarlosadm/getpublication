package getpublication.util.joinfiles;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

import getpublication.folders.UserFolder;

public class JoinToPdf implements JoinFiles {

    @Override
    public boolean join(List<String> fileList, String finalFileName) {
        
        Document document = new Document();
        PdfWriter writer = null;

        String path = UserFolder.getPathToTempFolder() + File.separator
                + finalFileName;
        try {
            FileOutputStream fos = new FileOutputStream(path);
            writer = PdfWriter.getInstance(document, fos);
            writer.open();
            document.setMargins(5, 5, 5, 5);

            for (String fileName : fileList) {
                Image image = Image.getInstance(fileName);
                document.setPageSize(new Rectangle(image.getWidth(), image.getHeight()));
                image.scaleToFit(document.getPageSize());
                
                if (!document.isOpen()) {
                    document.open();
                    document.add(image);
                } else {
                    document.newPage();
                    document.add(image);
                }
            }
            document.close();
            writer.close();
        } catch (Exception e) {
            document.close();
            writer.close();
            return false;
        }
        
        return true;
    }

}
