package getpublication.json.publication;

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
