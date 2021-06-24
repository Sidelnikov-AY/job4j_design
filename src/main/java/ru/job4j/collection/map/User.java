package ru.job4j.collection.map;

import java.sql.SQLOutput;
import java.util.*;

import static java.util.Objects.hash;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        Calendar date = new GregorianCalendar(1990, 01, 01);
        User user1 = new User("Иван", 1, date);
        User user2 = new User("Иван", 1, date);
        Map<User, Object> map = new HashMap<>();
        map.put(user1, new Object());
        map.put(user2, new Object());
        if (user1.equals(user2)) {
            System.out.println("юзеры(ключи) одинаковые");
        }
        for (User key : map.keySet()) {
            Object value = map.get(key);
            System.out.println(key + " : " + value);
        }
    }
}
