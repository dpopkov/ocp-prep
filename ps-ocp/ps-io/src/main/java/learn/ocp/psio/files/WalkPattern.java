package learn.ocp.psio.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class WalkPattern {

    public static void main(String[] args) {
        Path start = Path.of("");

        try {
            Files.walk(start)
                    .collect(Collectors.partitioningBy(p -> Files.isDirectory(p)))
                    .forEach((k, v) ->
                            System.out.println("Count " + (k ? "directories" : "files") + " = " + v.size()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
