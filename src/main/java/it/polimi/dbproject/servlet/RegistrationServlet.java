package it.polimi.dbproject.servlet;

import it.polimi.dbproject.entities.UserEntity;
import it.polimi.dbproject.services.UserService;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "RegistrationServlet", value = "/registration")
public class RegistrationServlet extends HttpServlet {
    Logger logger = Logger.getAnonymousLogger();

    @EJB
    private UserService us;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        String message;
        if (request.getParameter("registrationFailed") != null) {
            message = "The username or the email already exists. Try registering again.";
            request.setAttribute("registrationMessage", message);
        }
        else if (request.getParameter("registrationError") != null) {
            message = "An error occurred. Try registering again.";
            request.setAttribute("registrationMessage", message);
        }
        else if (request.getParameter("registrationDone") != null) {
            message = "The registration was successful. Now you can sign in.";
            request.setAttribute("registrationMessage", message);
        }
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");
        String toServlet = "registration";

        UserEntity u = null;
        String guest = request.getParameter("guest");
        HttpSession session = request.getSession();

        try {
            u = us.createUser(username, first_name, last_name, email, password);

            if (u != null && !Objects.equals(guest, "guest")) toServlet = "registration?registrationDone=true";
            else if (u != null && Objects.equals(guest, "guest")) {
                toServlet = "confirmationpage";
                session.setAttribute("user", u);
            }
            else toServlet = "registration?registrationError=true";

        } catch (Exception e) {
            logger.log(Level.SEVERE, "An exception was thrown", e);
        }

        response.sendRedirect(toServlet);
    }
}
