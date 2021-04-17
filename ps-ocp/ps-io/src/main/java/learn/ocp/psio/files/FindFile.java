package learn.ocp.psio.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FindFile {
    public static void main(String[] args) {
        Path startDir = Path.of("");
        Path target = Path.of(FindFile.class.getSimpleName() + ".java");

        try {
            Files.find(startDir, 10,
                    (path, attributes) -> path.endsWith(target))
                        .findFirst()
                        .ifPresent(p -> System.out.println("Found: " + p));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
