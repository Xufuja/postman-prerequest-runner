package dev.xfj;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.HostAccess;
import org.graalvm.polyglot.Source;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Application {
    private final ByteArrayOutputStream output;
    private final Context polyglot;
    private final List<Source> scripts;

    public Application() {
        this.output = new ByteArrayOutputStream();
        this.polyglot =  Context.newBuilder()
                .allowExperimentalOptions(true)
                .allowIO(true)
                .option("engine.WarnInterpreterOnly", "false")
                .option("js.esm-eval-returns-exports", "true")
                .allowHostAccess(HostAccess.ALL)
                .allowHostClassLookup(className -> true)
                .build();
        this.scripts = new ArrayList<>();
    }

    public void loadJavaScript(Path path) {
        String result;
        try (InputStream inputStream = Files.newInputStream(path)) {
            byte[] bytes = inputStream.readAllBytes();
            result = new String(bytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            this.scripts.add(Source.newBuilder("js", result, path.toString()).mimeType("application/javascript").build());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void loadJavaScript(String input, String name) {
        try {
            this.scripts.add(Source.newBuilder("js", input, name).mimeType("application/javascript").build());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public String run() {
        String script = scripts.stream().map(Source::getCharacters).collect(Collectors.joining());

        try {
            output.reset();
            this.polyglot.eval(Source.newBuilder("js", script, "script").mimeType("application/javascript").build());
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

        return output.toString();
    }
}
