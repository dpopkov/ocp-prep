package learn.ocp.psio.serial;

import java.io.*;

public class PersonProxied implements Serializable {

    private String name;
    private int age;

    public PersonProxied(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private Object writeReplace() {
        return new PersonProxy(name + "::" + age);
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
