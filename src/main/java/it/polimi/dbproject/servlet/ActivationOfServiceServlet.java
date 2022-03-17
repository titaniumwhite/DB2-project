package it.polimi.dbproject.servlet;

import it.polimi.dbproject.entities.OrderEntity;
import it.polimi.dbproject.entities.UserEntity;
import it.polimi.dbproject.services.UserService;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ActivationOfServiceServlet", value = "/homepage")
public class ActivationOfServiceServlet extends HttpServlet {

    @EJB
    UserService userService;
    HttpSession session;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        session = request.getSession();

        UserEntity user = (UserEntity) session.getAttribute("user");

        List<OrderEntity> pendingOrders = userService.retrievePendingOrder(user.getUser_id());

        request.setAttribute("pendingOrders", pendingOrders);

        RequestDispatcher dispatcher = request.getRequestDispatcher("userHomepage.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String toServlet;
        toServlet = "homepage";
        response.sendRedirect(toServlet);
    }
}
