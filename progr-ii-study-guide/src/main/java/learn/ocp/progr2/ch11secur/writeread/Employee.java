package learn.ocp.progr2.ch11secur.writeread;

import java.io.*;

public class Employee implements Serializable {
    private String name;
    private String ssn;
    private int age;

    private static final ObjectStreamField[] serialPersistentFields = {
            new ObjectStreamField("name", String.class),
            new ObjectStreamField("ssn", String.class)
    };

    private static String simpleNaiveEncrypt(String input) {
        char[] chars = input.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) (chars[i] + 1);
        }
        return String.valueOf(chars);
    }

    private static String simpleNaiveDecrypt(String input) {
        char[] chars = input.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) (chars[i] - 1);
        }
        return String.valueOf(chars);
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        ObjectOutputStream.PutField fields = s.putFields();
        fields.put("name", name);
        fields.put("ssn", simpleNaiveEncrypt(ssn));
        s.writeFields();
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        ObjectInputStream.GetField fields = s.readFields();
        this.name = (String) fields.get("name", null);
        this.ssn = simpleNaiveDecrypt((String) fields.get("ssn", null));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", ssn='" + ssn + '\'' +
                ", age=" + age +
                '}';
    }


}
