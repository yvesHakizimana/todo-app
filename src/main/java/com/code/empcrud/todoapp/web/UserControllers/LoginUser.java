package com.code.empcrud.todoapp.web.UserControllers;

import com.code.empcrud.todoapp.dao.LoginDao;
import com.code.empcrud.todoapp.model.LoginBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet( name = "Login-user", value = "/login-user")
public class LoginUser extends HttpServlet {
    LoginDao loginDao;

    public void init(){
        this.loginDao = new LoginDao();
    }

    public void authenticate(HttpServletRequest request, HttpServletResponse res) throws IOException, ServletException{
        String username= request.getParameter("uname");
        String password =  request.getParameter("passwd");
        LoginBean loginBean = new LoginBean();

        loginBean.setUsername(username);
        loginBean.setPassword(password);

        boolean result = loginDao.validate(loginBean);
        if(result)
        {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            res.sendRedirect("fetch-todo");
        }
        else {
            String message = "Username or password is invalid";
            request.setAttribute("message", message);
           RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
           dispatcher.forward(request, res);

        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
        authenticate(req, res);
    }
}
