package ru.java.collections.swing.userapp;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseConfig {
    private static final Properties props = new Properties();


    static {
        try (InputStream is = DatabaseConfig.class.getClassLoader().getResourceAsStream("database.properties")) {
            props.load(is);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getDbUrl() {
        return props.getProperty("db.url");
    }

    public static String getUsername() {
        return props.getProperty("db.username");
    }

    public static String getPassword() {
        return props.getProperty("db.password");
    }
}