package getpublication.db.json;

public enum PropertiesName {

    NAME_IN_URL {
        @Override
        public String toString() {
            return "nameInUrl";
        }
    };
    
    @Override
    public abstract String toString();
}
