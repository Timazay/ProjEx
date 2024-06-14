package javaexam.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class AbstractDAO {
    protected static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    protected static final String URL = "jdbc:mysql://localhost:3306/coursedb";
    public static final String LOGIN = "root";
    public static final String PASSWORD = "********";
    protected Connection connection;

    public void connect() {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }

    public void release() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }
}

