package learn.ocp.psio.serial;

import learn.ocp.psio.ModulePaths;

import java.io.*;

public class ReadingDataObjects {

    public static void main(String[] args) {
        final String fileName = "person.bin";
        final String filePath = ModulePaths.BIN + fileName;
        File file = new File(filePath);

        try (InputStream in = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(in)) {
            Person p1 = (Person) ois.readObject();
            System.out.println("p1 = " + p1);
            Person p2 = (Person) ois.readObject();
            System.out.println("p2 = " + p2);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
