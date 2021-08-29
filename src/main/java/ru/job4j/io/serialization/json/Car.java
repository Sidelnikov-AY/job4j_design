package ru.job4j.io.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public boolean isUsed() {
        return isUsed;
    }

    public int getYear() {
        return year;
    }

    public String getModel() {
        return model;
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

        JSONObject jsonEngine = new JSONObject("{\"turbo\":\"false\", \"volume\":\"1.5\"}");
        List<String> list = new ArrayList<>();
        list.add("leather seats");
        list.add("fog lights");
        JSONArray jsonOptions = new JSONArray(list);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isUsed", vaz.isUsed());
        jsonObject.put("Year", vaz.getYear());
        jsonObject.put("Model", vaz.getModel());
        jsonObject.put("Engine", jsonEngine);
        jsonObject.put("Options", jsonOptions);
        System.out.println("==JSONObject==");
        System.out.println(jsonObject.toString());
        System.out.println(new JSONObject(vaz).toString());
    }
}
