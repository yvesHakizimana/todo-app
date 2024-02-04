package com.code.empcrud.todoapp.web.TodoController;

import com.code.empcrud.todoapp.dao.TodoDaoImpl;
import com.code.empcrud.todoapp.model.Todo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet( name = "FetchTodo", value = "/fetch-todo")
public class FetchTodo extends HttpServlet {

    TodoDaoImpl todoDao;
    public void init(){
        this.todoDao = new TodoDaoImpl();
    }
    public void service(HttpServletRequest request, HttpServletResponse response) throws
            IOException, ServletException{
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        List<Todo> todos = todoDao.getTodosByUser(username);
        request.setAttribute("todos", todos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("todo-list.jsp");
        dispatcher.forward(request, response);
    }
}
