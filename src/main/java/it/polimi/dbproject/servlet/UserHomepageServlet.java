package it.polimi.dbproject.servlet;

import it.polimi.dbproject.entities.AvailableServicePackEntity;
import it.polimi.dbproject.entities.OrderEntity;
import it.polimi.dbproject.entities.UserEntity;
import it.polimi.dbproject.services.UserService;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "UserHomepageServlet", value = "/homepage")
public class UserHomepageServlet extends HttpServlet {

    @EJB
    UserService us;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("userHomepage.jsp");

        int user_id = Integer.parseInt(request.getParameter("id"));
        Optional<UserEntity> optionalUser = us.retrieveUserThroughID(user_id);
        String username = "";
        List<OrderEntity> orders = null;


        UserEntity user = optionalUser.get();
        username = user.getUsername();
        orders = user.getOrders();

        request.setAttribute("username", username);
        request.setAttribute("orders", orders);

        List<AvailableServicePackEntity> availableServicePackages = us.getAllServicePackages();
        request.setAttribute("availableServicePackages", availableServicePackages);

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
