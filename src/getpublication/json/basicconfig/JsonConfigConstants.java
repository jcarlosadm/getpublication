package getpublication.json.basicconfig;

public enum JsonConfigConstants {
    
    DOWNLOADFOLDER {
        @Override
        public String toString() {
            return "downloadFolder";
        }
    };
    
    @Override
    public abstract String toString();
}
