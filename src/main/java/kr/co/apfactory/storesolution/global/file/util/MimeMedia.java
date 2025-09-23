package kr.co.apfactory.storesolution.global.file.util;

import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

public class MimeMedia {
    private static Map<String, MediaType> mediaMap;

    static {
        mediaMap = new HashMap<String, MediaType>();
        mediaMap.put("JPG", MediaType.IMAGE_JPEG);
        mediaMap.put("GIF", MediaType.IMAGE_GIF);
        mediaMap.put("PNG", MediaType.IMAGE_PNG);
        mediaMap.put("JPEG", MediaType.IMAGE_JPEG);
    }

    public static MediaType getMediaType(String type) {
        return mediaMap.get(type.toUpperCase());
    }
}
