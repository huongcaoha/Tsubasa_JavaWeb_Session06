package com.ra.session06.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDatabase {
    private static final String url = "jdbc:mysql://localhost:3306/tsubasa_javaweb_session06?createDatabaseIfNotExist=true";
    private static final String user = "root";
    private static final String password = "12345678";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url,user,password);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
