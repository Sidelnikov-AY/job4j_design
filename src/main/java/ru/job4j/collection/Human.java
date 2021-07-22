package ru.job4j.collection;

import java.util.Objects;

public class Human {
    int age;
    float weight;
    int children;
    String name;
    boolean driversLicense;

    public Human(int age, float weight, int children, String name, boolean driversLicense) {
        this.age = age;
        this.weight = weight;
        this.children = children;
        this.name = name;
        this.driversLicense = driversLicense;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Human human = (Human) o;

        if (age != human.age) {
            return false;
        }
        if (Float.compare(human.weight, weight) != 0) {
            return false;
        }
        if (children != human.children) {
            return false;
        }
        if (driversLicense != human.driversLicense) {
            return false;
        }
        return name != null ? name.equals(human.name) : human.name == null;
    }

    @Override
    public int hashCode() {
        int result = age;
        result = 31 * result + (weight != +0.0f ? Float.floatToIntBits(weight) : 0);
        result = 31 * result + children;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (driversLicense ? 1 : 0);
        return result;
    }
}
