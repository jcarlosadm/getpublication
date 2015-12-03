package getpublication.util.joinfiles;

public class GetJoinFilesInstance {
    public static JoinFiles getInstance(String extension){
        if (extension.equals(PublicationExtension.CBZ.toString())) {
            return new JoinToCbz();
        } else if (extension.equals(PublicationExtension.PDF.toString())){
            return new JoinToPdf();
        }
        
        return null;
    }
}
