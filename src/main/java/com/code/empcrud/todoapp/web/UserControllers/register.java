package com.code.empcrud.todoapp.web.UserControllers;

import com.code.empcrud.todoapp.dao.UserDao;
import com.code.empcrud.todoapp.model.User;
import com.code.empcrud.todoapp.utils.Dbutils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Register-User", value = "/register-user")
public class register extends HttpServlet {

    UserDao userDao;
    Dbutils dbutils;

    public void init(){
        this.userDao = new UserDao();
    }

    public void registerUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        // Extracting the  user parameters from submitted data from the form data.
        String firstName = request.getParameter("fname");
        String lastName = request.getParameter("lname");
        String username = request.getParameter("uname");
        String password = request.getParameter("passwd");

        // Creating the new User
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setPassword(password);


        boolean result = userDao.insertUser(user, dbutils);
        if(result){
            request.setAttribute("NOTIFICATION", "User registered Successfully");
            RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
            dispatcher.forward(request, response);
        }
        else{
            PrintWriter out = response.getWriter();
            out.println("The was an error a microsecond to be resolved...");
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        // Forwarding the request to the responsible method of  handling it......
        registerUser(request, response);
    }

}
