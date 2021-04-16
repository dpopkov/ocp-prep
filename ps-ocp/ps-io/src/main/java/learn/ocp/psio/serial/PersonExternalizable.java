package learn.ocp.psio.serial;

import java.io.*;

public class PersonExternalizable implements Externalizable {

    private static final long serialVersionUID = 3576421536544823147L;

    private String name;
    private int age;

    public PersonExternalizable() {
    }

    public PersonExternalizable(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        String pk = name + "::" + age;
        out.write(pk.getBytes());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException {
        byte[] buffer = new byte[1000];
        int numBytes = in.read(buffer);
        String s = new String(buffer, 0, numBytes);
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
