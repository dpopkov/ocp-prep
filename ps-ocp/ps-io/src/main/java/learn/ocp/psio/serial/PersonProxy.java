package learn.ocp.psio.serial;

import java.io.Serializable;

public class PersonProxy implements Serializable {

    private static final long serialVersionUID = 6152361966124335520L;

    private String name;

    public PersonProxy(String name) {
        this.name = name;
    }

    private Object readResolve() {
        String[] tokens = this.name.split("::");
        String nameValue = tokens[0];
        int age = Integer.parseInt(tokens[1]);
        return new PersonProxied(nameValue, age);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PersonProxy{" +
                "name='" + name + '\'' +
                '}';
    }
}
