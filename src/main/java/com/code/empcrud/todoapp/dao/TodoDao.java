package com.code.empcrud.todoapp.dao;

import com.code.empcrud.todoapp.model.Todo;

import java.sql.SQLException;
import java.util.List;

public interface TodoDao {
    boolean insertTodo(Todo todo) throws SQLException;
    Todo selectTodo(long todoId);
    List<Todo> selectAllTodos();
    boolean deleteTodo(int id) throws  SQLException;

    boolean updateTodo(Todo todo) throws  SQLException;
}
