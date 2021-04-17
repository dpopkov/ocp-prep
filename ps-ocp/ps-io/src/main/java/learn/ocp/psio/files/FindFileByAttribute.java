package learn.ocp.psio.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;

public class FindFileByAttribute {
    public static void main(String[] args) {

        Path startDir = Path.of("");

        final Instant twoHoursAgo = Instant.now().minus(Duration.ofHours(2));
        try {
            Files.find(startDir, 10,
                    (path, attributes) -> attributes.lastModifiedTime().toInstant().isAfter(twoHoursAgo))
                    .forEach(p -> System.out.println("Found: " + p));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
