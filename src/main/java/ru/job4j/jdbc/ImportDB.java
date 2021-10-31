package ru.job4j.jdbc;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {

    private Properties cfg;
    private String dump;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            rd.lines().forEach(i -> {
                String[] temp = i.split(";");
                if (temp.length == 2) {
                    users.add(new User(temp[0], temp[1]));
                }
            });
        }
        return users;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("jdbc.driver"));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password")
        )) {
            boolean result = false;
            for (User user : users) {
                try (PreparedStatement ps = cnt.prepareStatement("insert into users (name, email) values (?, ?)")) {
                    ps.setString(1, user.getName());
                    ps.setString(2, user.getEmail());
                    ps.execute();
                    result = ps.executeUpdate() > 0;
                    System.out.println(result);
                }
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }
    }


    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        try (FileInputStream in = new FileInputStream("./src/main/java/ru/job4j/jdbc/app.properties")) {
            cfg.load(in);
        }
        ImportDB db = new ImportDB(cfg, "./src/main/java/ru/job4j/jdbc/dump.txt");
        db.save(db.load());
    }
}