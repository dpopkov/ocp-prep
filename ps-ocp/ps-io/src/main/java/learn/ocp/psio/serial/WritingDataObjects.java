package learn.ocp.psio.serial;

import learn.ocp.psio.ModulePaths;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WritingDataObjects {

    public static void main(String[] args) {
        final String fileName = "person.bin";
        final String filePath = ModulePaths.BIN + fileName;
        File file = new File(filePath);

        Person p1 = new Person("Linda", 32);
        Person p2 = new Person("David", 27);

        try (OutputStream out = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(out)) {
            oos.writeObject(p1);
            oos.writeObject(p2);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            System.out.println(fileName + ": " + Files.size(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
