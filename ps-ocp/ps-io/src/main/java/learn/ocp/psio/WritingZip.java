package learn.ocp.psio;

import java.io.*;
import java.util.stream.IntStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class WritingZip {

    public static void main(String[] args) {
        String zipPath = ModulePaths.ZIP + "archive.zip";
        File file = new File(zipPath);

        try (OutputStream out = new FileOutputStream(file);
             ZipOutputStream zip = new ZipOutputStream(out);
             DataOutputStream dos = new DataOutputStream(zip)) {
            ZipEntry binDirEntry = new ZipEntry("bin/");
            zip.putNextEntry(binDirEntry);

            ZipEntry binFileEntry = new ZipEntry("bin/ints.bin");
            zip.putNextEntry(binFileEntry);

            IntStream.range(1, 1001).forEach(x -> {
                try {
                    dos.writeInt(x);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            ZipEntry txtDirEntry = new ZipEntry("txt/");
            zip.putNextEntry(txtDirEntry);

            ZipEntry txtFileEntry = new ZipEntry("txt/file.txt");
            zip.putNextEntry(txtFileEntry);

            dos.writeUTF("This is some text content");

            zip.closeEntry();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
