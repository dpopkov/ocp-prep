package learn.ocp.psio;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.Locale;

public class WritingCharacters {

    public static void main(String[] args) {
        Path path = Paths.get(ModulePaths.TXT + "some-text.txt");
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            PrintWriter pw = new PrintWriter(writer);
            pw.print("Hello I/O ");
            pw.printf(Locale.US, "%1$td %1$tB %1$tY %1$tH:%1$tM%n", LocalDateTime.now());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
