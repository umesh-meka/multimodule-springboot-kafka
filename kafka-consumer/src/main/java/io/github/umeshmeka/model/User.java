package io.github.umeshmeka.model;

public class User {
    private String name;
    private String city;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public User(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public User() {}

    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
