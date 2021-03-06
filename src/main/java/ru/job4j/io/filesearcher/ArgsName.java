package ru.job4j.io.filesearcher;
import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException();
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        /* TODO parse args to values. */
        for (String arg : args) {
            String[] temp = arg.split("=");
            if (temp.length != 2 || temp[0].isEmpty() || temp[1].isEmpty() || !temp[0].startsWith("-")) {
                throw new IllegalArgumentException();
            }
            values.put(temp[0].substring(1), temp[1]);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public int size() {
        return values.size();
    }
}