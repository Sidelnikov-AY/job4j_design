package ru.job4j.collection.map;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

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
        User user1 = new User("Иван", 1,  date);
        User user2 = new User("Иван", 1,  date);
        Map<User, Object> map1 = new HashMap<>();
        Map<User, Object> map2 = new HashMap<>();
        map1.put(user1, new Object());
        map1.put(user2, new Object());
        System.out.println(map1);
        System.out.println(map2);
    }
}
