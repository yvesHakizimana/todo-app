package com.code.empcrud.todoapp.dao;

import com.code.empcrud.todoapp.model.Todo;

import java.sql.SQLException;
import java.util.List;

public interface TodoDao {
    boolean insertTodo(Todo todo);
    Todo selectTodo(long todoId, String username);

    boolean deleteTodo(int id, String username);

    boolean updateTodo(Todo todo) ;
}
