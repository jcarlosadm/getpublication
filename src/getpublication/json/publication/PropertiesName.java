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
    },
    LAST_CHAPTER {
        @Override
        public String toString() {
            return "lastChapter";
        }
    }, 
    CONVERT_IMAGES {
        @Override
        public String toString() {
            return "convertImages";
        }
    };
    
    @Override
    public abstract String toString();
}
