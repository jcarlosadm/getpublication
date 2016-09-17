package getpublication.util.convert;

public enum ImageFormats {
    JPEG {
        @Override
        public String toString() {
            return "jpeg";
        }
    },
    JPG {
        @Override
        public String toString() {
            return "jpg";
        }
    },
    WEBP {
        @Override
        public String toString() {
            return "webp";
        }
    },
    PNG {
        @Override
        public String toString() {
            return "png";
        }
    };
    
    public abstract String toString();
}
