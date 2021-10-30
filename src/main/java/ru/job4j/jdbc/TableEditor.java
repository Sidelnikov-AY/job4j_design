package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor() throws ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException {
        Config config = new Config("app.properties");
        config.load();
        Class.forName(config.value("driver"));
        String url = config.value("url");
        String login = config.value("login");
        String password = config.value("password");
        try {
            connection = DriverManager.getConnection(url, login, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void createTable(String tableName) {
        String sql = String.format("create table if not exists %s ();", tableName);
        exec(sql);
   }

    public void dropTable(String tableName) {
        String sql = String.format("drop table %s;", tableName);
        exec(sql);
    }

    public void addColumn(String tableName, String columnName, String type) {
        String sql = String.format("alter table %s add %s %s;", tableName, columnName, type);
        exec(sql);
    }

    public void dropColumn(String tableName, String columnName) {
        String sql = String.format("alter table %s drop column %s;", tableName, columnName);
        exec(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        String sql = String.format("alter table %s rename column %s to %s;", tableName, columnName, newColumnName);
        exec(sql);
    }

    public void exec(String query) {
        try {
            Statement statement = connection.createStatement();
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) throws Exception {
        String dbName = "test_db";
        TableEditor te = new TableEditor();
        te.initConnection();
        te.createTable(dbName);
        te.dropTable(dbName);
        te.addColumn(dbName, "test", "varchar(255)");
        te.dropColumn(dbName, "test");
        te.renameColumn(dbName, "test", "test_column");

        DatabaseMetaData dbm = te.connection.getMetaData();
        try {
            ResultSet tables = dbm.getTables(null, null, dbName, null);
            if (tables.next()) {
                System.out.println("Table exist");
            } else {
                System.out.println("Table not exist");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(getTableScheme(te.connection, "test_db"));

    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}