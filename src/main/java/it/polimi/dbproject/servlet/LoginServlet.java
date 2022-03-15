package it.polimi.dbproject.servlet;

import it.polimi.dbproject.entities.EmployeeEntity;
import it.polimi.dbproject.entities.UserEntity;
import it.polimi.dbproject.services.EmployeeService;
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

import static com.mysql.cj.xdevapi.Type.JSON;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    Logger logger = Logger.getAnonymousLogger();

    @EJB
    private UserService us;
    @EJB
    private EmployeeService es;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (Objects.equals(request.getParameter("loginSucceed"), "false"))
            request.setAttribute("loginMessage", "The username or the password are incorrect");

        RequestDispatcher rs = request.getRequestDispatcher("index.jsp");
        rs.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String pass = request.getParameter("password");
        int id = -1;
        String toServlet = "login";
        HttpSession session = request.getSession();


        if (username.matches("emp.*[0-9]{3}")) {
            // It is logging an employee
            EmployeeEntity emp = null;
            try {
                emp = es.checkEmployee(username, pass);
                id = emp.getEmployee_id();
            } catch (Exception e) {
                logger.log(Level.SEVERE, "An exception was thrown", e);
            }

            if(emp != null) {
                request.getSession().setAttribute("employee_id", id);
                toServlet = "employeehomepage";
            } else {
                toServlet = "login?loginSucceed=false";
            }

        } else {
            // It is logging a user
            UserEntity user= null;
            try {
                user= us.checkUser(username, pass);
                id = user.getUser_id();
            } catch (Exception e) {
                logger.log(Level.SEVERE, "An exception was thrown", e);
            }

            if(user!= null) {
                session.setAttribute("user", user);
                toServlet = "homepage";
            } else {
                toServlet = "login?loginSucceed=false";
            }
        }

        response.sendRedirect(toServlet);

    }
}
