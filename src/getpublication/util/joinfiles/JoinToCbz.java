package getpublication.util.joinfiles;

import java.io.File;
import java.util.List;

import getpublication.folders.UserFolder;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

public class JoinToCbz implements JoinFiles {

    @Override
    public boolean join(List<String> fileList, String finalFileName) {
        String path = UserFolder.getPathToTempFolder() + File.separator
                + finalFileName;

        try {
            ZipFile zipFile = new ZipFile(path);

            ZipParameters zipParameters = new ZipParameters();
            zipParameters.setCompressionMethod(Zip4jConstants.COMP_STORE);
            zipParameters
                    .setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);

            for (String filename : fileList) {
                zipFile.addFile(new File(filename), zipParameters);
            }
        } catch (ZipException e) {
            System.out.println("failed to create zip file");
            return false;
        }
        
        return true;
    }

}
