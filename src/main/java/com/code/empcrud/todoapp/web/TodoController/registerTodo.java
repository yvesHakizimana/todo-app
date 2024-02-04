package com.code.empcrud.todoapp.web.TodoController;

import com.code.empcrud.todoapp.dao.TodoDao;
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
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "registerTodo", value = "/register-todo")
public class registerTodo extends HttpServlet {

    TodoDao todoDao;
    public void init(){
        this.todoDao = new TodoDaoImpl();
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
        registertodo(req, res);
    }

    public void registertodo(HttpServletRequest req, HttpServletResponse res) throws  IOException, ServletException{
        String title = req.getParameter("title");
        String description = req.getParameter("desc");
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("username");

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate targetDate = LocalDate.parse(req.getParameter("targetDate"), df);

        boolean status =  Boolean.parseBoolean(req.getParameter("isDone"));
        Todo newTodo = new Todo(title, username,description, targetDate, status);
        boolean result = todoDao.insertTodo(newTodo);
        if(result){

            res.sendRedirect("fetch-todo");
        }
        else {
            PrintWriter out = res.getWriter();
            out.println("Wait a bit while resolving the issue");
        }

    }

}
