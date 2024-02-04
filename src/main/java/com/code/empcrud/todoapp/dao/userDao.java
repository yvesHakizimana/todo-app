package com.code.empcrud.todoapp.dao;

import com.code.empcrud.todoapp.model.User;
import com.code.empcrud.todoapp.utils.Dbutils;

public interface userDao {
    boolean insertUser(User user, Dbutils dbutils);
}
