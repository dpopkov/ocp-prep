package learn.ocp.psio.files;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class WalkFileTreePattern {

    public static void main(String[] args) {
        Path start = Path.of("");

        var visitor = new FileVisitor<Path>() {
            private long countFiles = 0L;
            private long countDirectories = 0L;

            public long getCountFiles() {
                return countFiles;
            }

            public long getCountDirectories() {
                return countDirectories;
            }

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                System.out.println("Directory: " + dir);
                countDirectories++;
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                System.out.println("File: " + file);
                countFiles++;
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
                return FileVisitResult.CONTINUE;
            }
        };
        try {
            Files.walkFileTree(start, visitor);
            System.out.println("Visited " + visitor.getCountFiles() + " files");
            System.out.println("Visited " + visitor.getCountDirectories() + " directories");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
