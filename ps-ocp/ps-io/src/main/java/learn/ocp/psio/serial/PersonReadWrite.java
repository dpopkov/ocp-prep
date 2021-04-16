package learn.ocp.psio.serial;

import java.io.*;

public class PersonReadWrite implements Serializable {

    private static final long serialVersionUID = -7795977428976581379L;

    private transient String name;
    private transient int age;

    public PersonReadWrite(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        DataOutputStream dos = new DataOutputStream(oos);
        dos.writeUTF(name + "::" + age);
    }

    private void readObject(ObjectInputStream ois) throws IOException {
        String s = ois.readUTF();
        String[] tokens = s.split("::");
        this.name = tokens[0];
        this.age = Integer.parseInt(tokens[1]);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
