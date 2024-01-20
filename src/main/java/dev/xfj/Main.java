package dev.xfj;

import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        Application application = new Application();
        PostmanWrapper.setEnvironment("protocol", "https");

        application.loadJavaScript(Path.of("CollectionStandardPreRequest.js"));
        application.loadJavaScript(Path.of("StandardPreRequest.js"));

        String inline = """
                config.apiCall = [\"POST\", \"/tokens\"]; 
                console.log(JSON.stringify(payload.createToken(), null, 4));
                """;

        application.loadJavaScript(inline, "a");
        System.out.println(application.run());
    }
}
