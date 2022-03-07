package it.polimi.dbproject.servlet;

import it.polimi.dbproject.entities.UserEntity;
import it.polimi.dbproject.services.UserService;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    Logger logger = Logger.getAnonymousLogger();

    @EJB
    private UserService us;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (Objects.equals(request.getParameter("loginSucceed"), "false"))
            request.setAttribute("loginMessage", "The username or the password are incorrect");

        RequestDispatcher rs = request.getRequestDispatcher("index.jsp");
        rs.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("username");
        String pass = request.getParameter("password");
        UserEntity u = null;
        String toServlet = "login";

        try {
            u = us.checkUser(email, pass);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "An exception was thrown", e);
        }

        if(u != null) {
            toServlet = "homepage";
        } else {
            toServlet = "login?loginSucceed=false";
        }

        response.sendRedirect(toServlet);

    }
}
