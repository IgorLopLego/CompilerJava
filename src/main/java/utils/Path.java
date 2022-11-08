package utils;

public class Path {
    public static final String EXAMPLES_DIR = System.getProperty("user.dir") + "/src/main/java/examples";

    public static final String TESTS_DIR = System.getProperty("user.dir") + "/src/test/java";

    public static String getTestsDirWithPath(String path) {
        return TESTS_DIR + path;
    }
}
