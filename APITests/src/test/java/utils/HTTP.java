package utils;

public class HTTP {

    public static class Status {
        public static final int OK = 200;
        public static final int BAD_REQUEST = 400;
        public static final int NO_CONTENT = 204;
        public static final int INTERNAL_SERVER_ERROR = 500;
    }

    public static class ContentType {
        public static final String JSON = "application/json";
    }
}
