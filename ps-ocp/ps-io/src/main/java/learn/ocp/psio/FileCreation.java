package learn.ocp.psio;

import java.io.File;
import java.io.IOException;

public class FileCreation {

    public static void main(String[] args) {
        File file = new File(ModulePaths.TXT +"modularity.txt");
        checkExists(file);

        File nope = new File(ModulePaths.TXT + "nope.txt");
        checkExists(nope);
        try {
            boolean created = nope.createNewFile();
            System.out.println("File created: " + created);
        } catch (IOException e) {
            e.printStackTrace();
        }
        checkExists(nope);
    }

    private static void checkExists(File file) {
        System.out.println("File " + file.getName() + " exists: " + file.exists());
    }
}
