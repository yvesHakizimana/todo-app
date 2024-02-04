package com.code.empcrud.todoapp.dao;

import com.code.empcrud.todoapp.model.Todo;
import com.code.empcrud.todoapp.utils.Dbutils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TodoDaoImpl implements TodoDao{

    private static String INSERT_TODOS_SQL = "INSERT INTO todoapp.todos (title, username, description, target_date, is_done) VALUES (?, ?, ?, ?, ?);";
    private static String SELECT_TODO_BY_ID = "SELECT id, title, username, description, target_date, is_done from todoapp.todos where id = ? and username = ?;";
    private static String DELETE_TODO_BY_ID = "DELETE FROM todoapp.todos where id  = ? and username = ?;";
    private static String UPDATE_TODO_BY_ID = "UPDATE todoapp.todos set title = ?, description = ? , target_date = ? , is_done = ? where id = ? and username = ?";
    private static String SELECT_TODO_BY_U = "SELECT id, title, username, description, target_date, is_done from todoapp.todos where username = ?;";

    Dbutils dbutils = new Dbutils();
    @Override
    public boolean insertTodo(Todo todo) {
        boolean result = false;
        try(PreparedStatement statement = dbutils.connectToDb().prepareStatement(INSERT_TODOS_SQL);){
            statement.setString(1,todo.getTitle());
            statement.setString(2, todo.getUsername());
            statement.setString(3, todo.getDescription());
            statement.setDate(4, Dbutils.getSQLDate(todo.getTargetDate()));
            statement.setBoolean(5, todo.isStatus());

            result = statement.executeUpdate() > 0;

        } catch(SQLException ex){
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("ErrorCode: " + ex.getErrorCode());
            System.out.println("Message: " + ex.getMessage());
        }
        return  result;
    }

    @Override
    public Todo selectTodo(long todoId, String username) {
        Todo todo = null;
        try(PreparedStatement statement = dbutils.connectToDb().prepareStatement(SELECT_TODO_BY_ID);){
            statement.setLong(1, todoId);
            statement.setString(2, username);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                long id = rs.getLong("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                String user = rs.getString("username");
                LocalDate targetDate = rs.getDate("target_date").toLocalDate();
                boolean isDone = rs.getBoolean("is_done");
                todo = new Todo(id, title, user, description, targetDate, isDone);
            }

        } catch(SQLException ex){
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("ErrorCode: " + ex.getErrorCode());
            System.out.println("Message: " + ex.getMessage());
        }
        return todo;
    }


    protected  List<Todo> fetchTodos(ResultSet rs) throws  SQLException{
        List<Todo> todos = new ArrayList<>();
        while(rs.next()){
            long id = rs.getLong("id");
            String title = rs.getString("title");
            String description = rs.getString("description");
            String username = rs.getString("username");
            LocalDate targetDate = rs.getDate("target_date").toLocalDate();
            boolean isDone = rs.getBoolean("is_done");
            todos.add(new Todo(id, title, username, description, targetDate, isDone));
        }
        return todos;
    }

    @Override
    public boolean deleteTodo(int id, String username)  {
        boolean rowDeleted = false;
        try(PreparedStatement statement  = dbutils.connectToDb().prepareStatement(DELETE_TODO_BY_ID);){
            statement.setInt(1, id);
            statement.setString(2, username);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (SQLException ex){
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("ErrorCode: " + ex.getErrorCode());
            System.out.println("Message: " + ex.getMessage());
        }
        return rowDeleted;
    }

    @Override
    public boolean updateTodo(Todo todo){
        boolean rowUpdated = false;
        try(PreparedStatement statement = dbutils.connectToDb().prepareStatement(UPDATE_TODO_BY_ID);){
          statement.setString(1, todo.getTitle());
          statement.setString(2, todo.getDescription());
          statement.setDate(3, Dbutils.getSQLDate(todo.getTargetDate()));
          statement.setBoolean(4, todo.isStatus());
          statement.setLong(5, todo.getId());
          statement.setString(6, todo.getUsername());

          rowUpdated = statement.executeUpdate() > 0;
        } catch(SQLException ex){
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("ErrorCode: " + ex.getErrorCode());
            System.out.println("Message: " + ex.getMessage());
        }
        return rowUpdated;
    }

    public List<Todo> getTodosByUser(String username) {
        List<Todo> todos = new ArrayList<>();
        try(PreparedStatement statement  = dbutils.connectToDb().prepareStatement(SELECT_TODO_BY_U);){
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            todos  = fetchTodos(rs);

        } catch (SQLException ex){
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("ErrorCode: " + ex.getErrorCode());
            System.out.println("Message: " + ex.getMessage());
        }
        return todos;
    }
}
