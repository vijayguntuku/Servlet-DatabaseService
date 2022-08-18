package org.example.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnectionUtility {

    private static String MYSQL_HOST = "jdbc:mysql://localhost:3306/dbstorage";
    private static String MYSQL_USER_NAME = "root";
    private static String MYSQL_PASSWORD = "Vijay123$";

    private static Connection connection;
    public static Connection getConnectionInSingleTon() {
        if(connection == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(MYSQL_HOST, MYSQL_USER_NAME, MYSQL_PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("SQL Exception while Creating Mysql Connection " + MYSQL_HOST + " With User " + MYSQL_USER_NAME);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static Connection getConnection() {
        if (connection == null) {
            synchronized (MySQLConnectionUtility.class) {
                if(connection == null) {
                    try {
                        connection = DriverManager.getConnection(MYSQL_HOST, MYSQL_USER_NAME, MYSQL_PASSWORD);
                    } catch (SQLException e) {
                        e.printStackTrace();
                        System.out.println("SQL Exception while Creating Mysql Connection " + MYSQL_HOST + " With User " + MYSQL_USER_NAME);
                    }
                }
            }
        }
        return connection;
    }

    public static Connection getConnectionNonSingleTon() {
        Connection mySQLConnection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            mySQLConnection = DriverManager.getConnection(MYSQL_HOST, MYSQL_USER_NAME, MYSQL_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exception while Creating Mysql Connection " + MYSQL_HOST + " With User " + MYSQL_USER_NAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return mySQLConnection;
    }

    public static void closeConnection(Connection connection){
        try{
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
