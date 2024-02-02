package com.code.empcrud.todoapp.utils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

public class Dbutils {

    private static String jdbcUrl = "jdbc:postgresql://localhost:5432/webapp";
    private static String jdbcUsername = "postgres";
    private static String jdbcPassword = "P0S1tiv@!";

    public Connection connectToDb() throws SQLException{
        Connection conn = null;
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
            if(conn != null)
                System.out.println("Successfully connected to the daatabase");
        } catch(SQLException ex){
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("ErrorCode: " + ex.getErrorCode());
            System.out.println("Message: " + ex.getMessage());
        } catch(ClassNotFoundException ex){
            throw new RuntimeException("ClassNotFoundException");
        }

        return conn;
    }

    public static Date getSQLDate(LocalDate date) {
        return java.sql.Date.valueOf(date);
    }

    public static LocalDate getUtilDate(Date sqlDate) {
        return sqlDate.toLocalDate();
    }

}
