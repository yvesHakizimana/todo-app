package com.code.empcrud.todoapp.web.TodoController;

import com.code.empcrud.todoapp.dao.TodoDaoImpl;
import com.code.empcrud.todoapp.model.Todo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet( name = "EditScreen", value = "/editScreen")
public class editScreen extends HttpServlet {
    TodoDaoImpl todoDao;

    public void init(){
        this.todoDao =  new TodoDaoImpl();
    }

    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        Todo todo = todoDao.selectTodo(id, username);

        Cookie cookie = new Cookie("id", String.valueOf(id));
        response.addCookie(cookie);

        request.setAttribute("todo", todo);
        RequestDispatcher dispatcher = request.getRequestDispatcher("todo-form.jsp");
        dispatcher.forward(request, response);
    }

}
