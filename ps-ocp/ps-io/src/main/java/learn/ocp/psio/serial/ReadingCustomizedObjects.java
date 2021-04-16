package learn.ocp.psio.serial;

import learn.ocp.psio.ModulePaths;

import java.io.*;

public class ReadingCustomizedObjects {

    public static void main(String[] args) {
        final String fileName = "person-write-object2.bin";
        final String filePath = ModulePaths.BIN + fileName;
        File file = new File(filePath);

        try (InputStream in = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(in)) {
            PersonReadWrite p1 = (PersonReadWrite) ois.readObject();
            System.out.println("p1 = " + p1);
            PersonReadWrite p2 = (PersonReadWrite) ois.readObject();
            System.out.println("p2 = " + p2);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
