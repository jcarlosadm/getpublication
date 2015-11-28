package getpublication.util.folder.chooser;

import javax.swing.JFileChooser;

public class DialogFolderChooser {
    public static String pickFolder() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.showSaveDialog(null);
        
        return fileChooser.getSelectedFile().getPath();
    }
}
