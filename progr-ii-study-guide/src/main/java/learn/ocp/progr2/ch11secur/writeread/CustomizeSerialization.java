package learn.ocp.progr2.ch11secur.writeread;

import java.io.*;

public class CustomizeSerialization {

    private static final String IO_DIR = "io";
    private static final String EMPLOYEE_FILENAME = "employee.ser";

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        writeEmployee();
        readEmployee();
    }

    private static void readEmployee() throws IOException, ClassNotFoundException {
        File file = new File(IO_DIR, EMPLOYEE_FILENAME);
        if (file.exists()) {
            System.out.println("Reading from " + file);
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                final Object object = in.readObject();
                Employee employee = (Employee) object;
                System.out.println("Read employee:");
                System.out.println(employee);
            }
        } else {
            System.out.println("File " + file + " does not exist");
        }
    }

    private static void writeEmployee() throws IOException {
        Employee employee = new Employee();
        employee.setName("Jane");
        employee.setSsn("12345678");
        employee.setAge(42);

        File file = new File(IO_DIR, EMPLOYEE_FILENAME);
        write(employee, file);
    }

    private static void write(Employee employee, File file) throws IOException {
        if (file.exists()) {
            System.out.println("File " + file + " exists");
        } else {
            System.out.println("Writing object to file " + file);
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
                out.writeObject(employee);
            }
        }
    }
}
