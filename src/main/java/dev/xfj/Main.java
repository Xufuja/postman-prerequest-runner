package dev.xfj;

import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        Application application = new Application();
        String custom = """                                              
                pm = {           
                    globals: {
                        get: function (key) {
                          return Java.type(`dev.xfj.PostmanWrapper`).getGlobal(key);
                        }
                    },
                    environment: {
                        get: function (key) {
                          return Java.type(`dev.xfj.PostmanWrapper`).getEnvironment(key);
                        }
                    },
                    request: {
                        headers: [],
                        body: ""
                    }
                };
                """;
        PostmanWrapper.setGlobal("First", "Value");
        PostmanWrapper.setEnvironment("Ah", "Oh");

        application.loadJavaScript(custom, "custom");
        application.loadJavaScript(Path.of("a.js"));
        application.loadJavaScript("console.log(JSON.stringify(payload.createToken(), null, 4));", "a");
        application.loadJavaScript("console.log(pm.environment.get(\"Ah\"));", "b");
        application.loadJavaScript("console.log(pm.globals.get(\"First\"));", "c");
        application.loadJavaScript("console.log(pm.globals.get(\"b\"));", "d");
        //application.loadJavaScript(Path.of("CollectionStandardPreRequest.js"));
        //application.loadJavaScript(Path.of("StandardPreRequest.js"));
        String out = application.run();
        System.out.println(out);
    }
}
