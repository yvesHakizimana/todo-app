package com.code.empcrud.todoapp.dao;

import com.code.empcrud.todoapp.model.LoginBean;
import com.code.empcrud.todoapp.utils.Dbutils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {

    private static String SELECT_USER_BY_UP = "SELECT * FROM todoapp.users where username = ? and password = ?;";

    public boolean validate(LoginBean loginBean){
        boolean status = false;
        Dbutils dbutils = new Dbutils();
        try(PreparedStatement statement = dbutils.connectToDb().prepareStatement(SELECT_USER_BY_UP);){

            statement.setString(1, loginBean.getUsername());
            statement.setString(2, loginBean.getPassword());
            ResultSet rs = statement.executeQuery();
            status = rs.next();

        } catch(SQLException ex){
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("ErrorCode: " + ex.getErrorCode());
            System.out.println("Message: " + ex.getMessage());
        }

        return status;
    }
}
