package ru.job4j.io.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Arrays;

public class Car {
    private boolean isUsed;
    private int year;
    private String model;
    private final Engine engine;
    private final String[] options;

    public Car(boolean isUsed, int year, String  model, Engine engine, String... options) {
        this.isUsed = isUsed;
        this.year = year;
        this.model = model;
        this.engine = engine;
        this.options = options;
    }


    @Override
    public String toString() {
        return "Car{"
                +
                "isUsed=" + isUsed
                +
                ", year=" + year
                +
                ", model='" + model + '\''
                +
                ", engine=" + engine
                +
                ", options=" + Arrays.toString(options)
                +
                '}';
    }

    public static void main(String[] args) {
        Car vaz = new Car(true, 1990, "2109", new Engine(false, 1.5D),
                "leather seats", "fog lights");

        final Gson gson = new GsonBuilder().create();
        System.out.println(vaz);
        System.out.println(gson.toJson(vaz));

        final Car vazFromJson = gson.fromJson(gson.toJson(vaz), Car.class);
        System.out.println(vazFromJson);
    }
}
