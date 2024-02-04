package com.code.empcrud.todoapp.web.TodoController;

import com.code.empcrud.todoapp.dao.TodoDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet( name = "DeleteTodo", value = "/delete-todo")
public class DeleteTodo extends HttpServlet {
    TodoDaoImpl todoDao;

    public void init(){
        this.todoDao = new TodoDaoImpl();
    }

    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
        int id = Integer.parseInt(req.getParameter("id"));
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("username");
        System.out.println(username);
        boolean result = todoDao.deleteTodo(id, username);
        if(result)
            res.sendRedirect("fetch-todo");
        else{
            PrintWriter out = res.getWriter();
            out.println("There is a glitch in wait a second");
        }
    }

}
