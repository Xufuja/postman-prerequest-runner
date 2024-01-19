package dev.xfj;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.HostAccess;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Application {
    public Application() throws IOException {
        Context rootDomain = Context.newBuilder()
                .allowExperimentalOptions(true)
                .allowIO(true)
                .option("engine.WarnInterpreterOnly", "false")
                .option("js.esm-eval-returns-exports", "true")
                .allowHostAccess(HostAccess.ALL)
                .allowHostClassLookup(className -> true).build();

        String result;
        try (InputStream inputStream = Files.newInputStream(Path.of("a.js"))) {
            byte[] bytes = inputStream.readAllBytes();
            result = new String(bytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            //Log.error("Could not open file: " + filePath);
            throw new RuntimeException(e);
        }
        Source source = Source.newBuilder("js", result, "a.js")
                //.mimeType("application/javascript+module")
                .build();
        Value value = rootDomain.eval(source);
    }
}
