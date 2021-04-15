package learn.ocp.psio;

import java.io.*;
import java.util.zip.GZIPInputStream;

public class ReadingPrimitives {

    @SuppressWarnings("InfiniteLoopStatement")
    public static void main(String[] args) {
        File file = new File(ModulePaths.BIN + "ints.bin.gz");

        try (FileInputStream in = new FileInputStream(file);
             GZIPInputStream gzip = new GZIPInputStream(in);
             DataInputStream din = new DataInputStream(gzip)) {
            try {
                while (true) {
                    int v = din.readInt();
                    System.out.println(v);
                }
            } catch (EOFException eof) {
                // end-of-file reached
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
