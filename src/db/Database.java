package db;

import java.sql.Connection;

public class Database {

    private static final String URL = "jdbc:sqlite:db/users.db";

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = java.sql.DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

}
