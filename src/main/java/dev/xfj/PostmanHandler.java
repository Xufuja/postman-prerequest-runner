package dev.xfj;

import java.util.HashMap;
import java.util.Map;

public class PostmanHandler {
    private static final Map<String, String> globals;
    private static final Map<String, String> environment;

    private PostmanHandler() {
    }

    static {
        globals = new HashMap<>();
        environment = new HashMap<>();
    }

    public static String getGlobal(String key) {
        return globals.get(key);
    }

    public static void setGlobal(String key, String value) {
        globals.put(key, value);
    }

    public static String getEnvironment(String key) {
        return environment.get(key);
    }

    public static void setEnvironment(String key, String value) {
        environment.put(key, value);
    }
}
