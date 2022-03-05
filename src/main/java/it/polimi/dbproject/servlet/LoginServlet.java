package it.polimi.dbproject.servlet;

import it.polimi.dbproject.entities.UserEntity;
import it.polimi.dbproject.services.UserService;

import java.io.*;
import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginServlet extends HttpServlet {

    Logger logger = Logger.getAnonymousLogger();

    @EJB
    private UserService userService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        String email = request.getParameter("username");
        String pass = request.getParameter("password");
        UserEntity u = null;

        try {
            u = userService.checkUser(email, pass);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "An exception was thrown", e);
        }

        if(u != null) {
            RequestDispatcher rs = request.getRequestDispatcher("home.jsp");
            rs.forward(request, response);
        } else {
            out.println("The username or the password are incorrect");
            RequestDispatcher rs = request.getRequestDispatcher("index.jsp");
            rs.forward(request, response);
        }
    }
}