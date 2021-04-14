package learn.core2.ch09modules.openpkg;

public class Country {
    private String name;
    private double area;

    public Country() {
    }

    public Country(String name, double area) {
        this.name = name;
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", area='" + area + '\'' +
                '}';
    }
}
