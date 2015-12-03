package getpublication.json.publication;

public enum PropertiesName {

    NAME_IN_URL {
        @Override
        public String toString() {
            return "nameInUrl";
        }
    },
    PUBLICATION_EXTENSION {
        @Override
        public String toString() {
            return "publicationExtension";
        }
    };
    
    @Override
    public abstract String toString();
}
