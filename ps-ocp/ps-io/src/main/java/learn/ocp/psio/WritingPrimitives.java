package learn.ocp.psio;

import java.io.*;
import java.util.stream.IntStream;
import java.util.zip.GZIPOutputStream;

public class WritingPrimitives {

    public static void main(String[] args) {
        File file = new File(ModulePaths.BIN + "ints.bin.gz");

        try (OutputStream out = new FileOutputStream(file);
             GZIPOutputStream gzip = new GZIPOutputStream(out);
             DataOutputStream dos = new DataOutputStream(gzip)) {
            IntStream.range(1, 1001).forEach(x -> {
                try {
                    dos.writeInt(x);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
//            dos.writeUTF("Hello");
//            dos.writeInt(10);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(file.getAbsolutePath() + " size = " + file.length() + " bytes");
    }
}
