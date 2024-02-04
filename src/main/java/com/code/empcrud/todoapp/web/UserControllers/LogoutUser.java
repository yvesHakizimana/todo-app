package com.code.empcrud.todoapp.web.UserControllers;

import javax.naming.Name;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LogoutUser", value = "/logout-user")
public class LogoutUser extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
     HttpSession session = request.getSession();
     session.removeAttribute("username");
     session.invalidate();
     response.sendRedirect("login.jsp");
    }
}
