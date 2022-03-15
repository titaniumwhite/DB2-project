package it.polimi.dbproject.servlet;

import it.polimi.dbproject.entities.ErrorEntity;
import it.polimi.dbproject.entities.ServicePackEntity;
import it.polimi.dbproject.entities.UserEntity;
import it.polimi.dbproject.entities.OrderEntity;
import it.polimi.dbproject.services.UserService;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@WebServlet(name = "confirmationPage", value = "/confirmationPage")
public class ConfirmationServlet extends HttpServlet {

    @EJB
    private UserService userService;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        UserEntity user = (UserEntity) session.getAttribute("user");


    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();


    }
}
