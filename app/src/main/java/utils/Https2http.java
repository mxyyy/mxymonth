package utils;

public class Https2http {
    public static String Https2https(String url) {
        return url.replace("https", "http");
    }
}
