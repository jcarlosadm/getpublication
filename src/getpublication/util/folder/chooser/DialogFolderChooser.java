package getpublication.util.folder.chooser;

import java.io.File;

import javax.swing.JFileChooser;

public class DialogFolderChooser {
    public static String pickFolder() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.showSaveDialog(null);
        
        File file = fileChooser.getSelectedFile();
        if (file == null) {
            return "";
        }
        
        return file.getPath();
    }
}
