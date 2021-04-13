package learn.ocp.progr2.ch11secur.prepostprocessing;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Employee implements Serializable {

    private static final Map<String, Employee> pool = new ConcurrentHashMap<>();

    private String name;
    private String ssn;

    private Employee() {
    }

    public synchronized static Employee getEmployee(String name) {
        Employee employee = pool.get(name);
        if (employee == null) {
            employee = new Employee();
            employee.setName(name);
            pool.put(name, employee);
        }
        return employee;
    }

    // this method is run after readObject()
    public synchronized Object readResolve() {
        Employee existing = pool.get(this.name);
        if (existing == null) {
            pool.put(this.name, this);
            return this;
        } else {
            existing.setName(this.name);
            existing.setSsn(this.ssn);
            return existing;
        }
    }

    // this method is run before writeObject() and
    // allows to replace the object that gets serialized
    public Object writeReplace() {
        Employee employee = pool.get(this.name);
        return employee != null ? employee : this;
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

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", ssn='" + ssn + '\'' +
                '}';
    }
}
