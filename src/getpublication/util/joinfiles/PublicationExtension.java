package getpublication.util.joinfiles;

public enum PublicationExtension {
    PDF {
        @Override
        public String toString() {
            return "pdf";
        }
    },
    CBZ {
        @Override
        public String toString() {
            return "cbz";
        }
    };
    
    @Override
    public abstract String toString();
}
