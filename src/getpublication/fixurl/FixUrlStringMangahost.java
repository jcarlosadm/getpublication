package getpublication.fixurl;

import org.apache.commons.io.FilenameUtils;

public class FixUrlStringMangahost implements FixUrlString {

    private static final String FINAL_EXTENSION = ".png.webp";
    private static final String STRING_TO_BE_SET = "images";
    private static final String STRING_TO_BE_REPLACED = "mangas_files";
    private static final String MIDDLE_EXTENSION_TO_BE_SET = ".png.";
    private static final String MIDDLE_EXTENSION_TO_BE_REPLACED = ".webp.";

    @Override
    public String fix(String urlString) {
        if (urlString.contains(MIDDLE_EXTENSION_TO_BE_REPLACED)) {
            urlString = urlString.replace(MIDDLE_EXTENSION_TO_BE_REPLACED,
                    MIDDLE_EXTENSION_TO_BE_SET);
        } else if (urlString.contains(STRING_TO_BE_REPLACED)) {
            urlString = urlString.replace(STRING_TO_BE_REPLACED, STRING_TO_BE_SET);
            if (!urlString.contains(FINAL_EXTENSION)) {
                String extension = FilenameUtils.getExtension(urlString);
                urlString = urlString.replace("." + extension, FINAL_EXTENSION);
            }
        }

        return urlString;
    }

}
