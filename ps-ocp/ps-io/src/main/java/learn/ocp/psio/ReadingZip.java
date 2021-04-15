package learn.ocp.psio;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ReadingZip {

    @SuppressWarnings("InfiniteLoopStatement")
    public static void main(String[] args) {
        String zipPath = ModulePaths.ZIP + "archive.zip";
        File file = new File(zipPath);

        try (InputStream in = new FileInputStream(file);
             ZipInputStream zip = new ZipInputStream(in);
             DataInputStream dis = new DataInputStream(zip)) {
            ZipEntry entry = zip.getNextEntry();
            while (entry != null) {
                if (entry.isDirectory()) {
                    System.out.println("Reading directory " + entry);
                } else {
                    System.out.println("Reading file " + entry);
                    if (entry.getName().endsWith(".bin")) {
                        try {
                            while (true) {
                                int v = dis.readInt();
                                System.out.println(v);
                            }
                        } catch (EOFException eof) {
                            // end-of-file reached
                        }
                    } else if (entry.getName().endsWith(".txt")) {
                        String content = dis.readUTF();
                        System.out.println("content = " + content);
                    }
                }
                entry = zip.getNextEntry();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
