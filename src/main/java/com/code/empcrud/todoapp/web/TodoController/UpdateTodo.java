package com.code.empcrud.todoapp.web.TodoController;

import com.code.empcrud.todoapp.dao.TodoDaoImpl;
import com.code.empcrud.todoapp.model.Todo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet( name = "UpdateTodo", value = "/update-todo")
public class UpdateTodo extends HttpServlet {
    TodoDaoImpl todoDao;

    public void init(){
        this.todoDao = new TodoDaoImpl();
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
        int id = 0;
        String title = req.getParameter("title");
        String description = req.getParameter("desc");
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate targetDate = LocalDate.parse(req.getParameter("targetDate"), df);
        boolean status =  Boolean.parseBoolean(req.getParameter("isDone"));

        // Getting the id
        Cookie[] cookies = req.getCookies();
        for(Cookie c: cookies)
            if(c.getName().equals("id"))
                id = Integer.parseInt(c.getValue());


        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("username");
        Todo newTodo = new Todo(id, title, username,description, targetDate, status);
        
        boolean result = todoDao.updateTodo(newTodo);
        if(result)
            res.sendRedirect("fetch-todo");
        else
        {
            PrintWriter out = res.getWriter();
            out.println("There is a glitch to be resolved in microsecond");
        }
    }
}
