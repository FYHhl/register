package com.example.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DButil {
    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Properties properties=new Properties();
        properties.load(DButil.class.getClassLoader().getResourceAsStream("jdbc.properties"));
        String url = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");
        return DriverManager.getConnection(url,username,password);
    }
}
