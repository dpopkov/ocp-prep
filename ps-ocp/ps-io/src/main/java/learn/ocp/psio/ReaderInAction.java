package learn.ocp.psio;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class ReaderInAction {

    public static void main(String[] args) throws IOException {
        final String pathToFile = ModulePaths.TXT + "modularity.txt";

//        readLinesOldWay(pathToFile);
//        readLinesNio(pathToFile);
        readLinesStream(pathToFile);
    }

    private static void readLinesStream(String pathToFile) throws IOException {
        Path path = Path.of(pathToFile);
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            reader.lines().forEach(System.out::println);
        }
    }

    private static void readLinesNio(String pathToFile) throws IOException {
        Path path = Path.of(pathToFile);
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            printLinesFrom(reader);
        }
    }

    private static void readLinesOldWay(String pathToFile) throws IOException {
        File file = new File(pathToFile);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            printLinesFrom(reader);
        }
    }

    private static void printLinesFrom(BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }
}
