package dev.xfj;

import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        Application application = new Application();
        PostmanHandler.setEnvironment("protocol", "https");
        PostmanHandler.setEnvironment("IEP_API_SECRET_KEY", "test");

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
