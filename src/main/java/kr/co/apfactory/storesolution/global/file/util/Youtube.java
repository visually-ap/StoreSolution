package kr.co.apfactory.storesolution.global.file.util;

public class Youtube {
    public static final int DEFAULT_FULLHD = 0;
    public static final int DEFAULT_LARGE = 1;
    public static final int DEFAULT_MIDDLE = 2;
    public static final int DEFAULT_THUMBNAIL = 3;

    // 유튜브 동영상 주소로부터 썸네일 가져오기
    public String getYoutubeThumbnail(String source, int Option) {
        String thumbnailUrl = "https://i.ytimg.com/vi/" + source + "/";
        switch (Option) {
            case DEFAULT_FULLHD:
                thumbnailUrl += "maxresdefault.jpg";
                break;
            case DEFAULT_LARGE:
                thumbnailUrl += "hqdefault.jpg";
                break;
            case DEFAULT_MIDDLE:
                thumbnailUrl += "mqdefault.jpg";
                break;
            case DEFAULT_THUMBNAIL:
                thumbnailUrl += "default.jpg";
                break;
        }
        return thumbnailUrl;
    }

    public String getMovieId(String source) {
        String movieId = source.replace("https://www.youtube.com/embed/", "");
        movieId = movieId.replace("http://www.youtube.com/embed/", "");
        movieId = movieId.replace("https://www.youtube.com/watch?v=", "");
        movieId = movieId.replace("http://www.youtube.com/watch?v=", "");
        movieId = movieId.replace("https://youtu.be/", "");
        movieId = movieId.replace("http://youtu.be/", "");
        return movieId;
    }

    public String getMovieSource(String movieId) {
        return "<iframe width=\"1280\" height=\"720\" src=\"https://www.youtube.com/embed/" + movieId + "\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>";
    }
}
