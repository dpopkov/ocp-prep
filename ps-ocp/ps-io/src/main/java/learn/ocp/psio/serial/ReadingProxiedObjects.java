package learn.ocp.psio.serial;

import learn.ocp.psio.ModulePaths;

import java.io.*;

public class ReadingProxiedObjects {

    public static void main(String[] args) {
        final String fileName = "person-proxied.bin";
        final String filePath = ModulePaths.BIN + fileName;
        File file = new File(filePath);

        try (InputStream in = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(in)) {
            PersonProxied p1 = (PersonProxied) ois.readObject();
            System.out.println("p1 = " + p1);
            PersonProxied p2 = (PersonProxied) ois.readObject();
            System.out.println("p2 = " + p2);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
