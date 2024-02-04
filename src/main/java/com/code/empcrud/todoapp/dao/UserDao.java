package com.code.empcrud.todoapp.dao;

import com.code.empcrud.todoapp.model.User;
import com.code.empcrud.todoapp.utils.Dbutils;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao implements userDao {



    private static String INSERT_USER_SQL = "INSERT INTO todoapp.users  (first_name , last_name , username, password) VALUES " + " (?, ?, ?, ?)" ;


    @Override
    public boolean insertUser(User user, Dbutils dbutils) {
        dbutils = new Dbutils();
        boolean result = false;
        try(PreparedStatement statement  = dbutils.connectToDb().prepareStatement(INSERT_USER_SQL);){
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getUsername());
            statement.setString(4, user.getPassword());
            result = statement.executeUpdate()  > 0;

        } catch(SQLException ex){
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("ErrorCode: " + ex.getErrorCode());
            System.out.println("Message: " + ex.getMessage());
        }
        return result;
    }



}
