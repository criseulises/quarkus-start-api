package quarkus;

import java.util.Objects;

public class Temperature {
    private String city;
    private int min;
    private int max;

    public Temperature(){}

    public Temperature(String city, int min, int max) {
        this.city = city;
        this.min = min;
        this.max = max;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Temperature that = (Temperature) o;
        return min == that.min && max == that.max && Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, min, max);
    }

    @Override
    public String toString() {
        return "Temperature{" +
                "city='" + city + '\'' +
                ", min=" + min +
                ", max=" + max +
                '}';
    }
}
