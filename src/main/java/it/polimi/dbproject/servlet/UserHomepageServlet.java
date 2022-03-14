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
    UserService userService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("userHomepage.jsp");

        String username = "guest";
        String errorMessageSize = "There are no order here";
        List<OrderEntity> userOrders = null;
        int user_id = -1;

        try {
            user_id = Integer.parseInt((String) request.getSession().getAttribute("user_id"));
            Optional<UserEntity> optionalUser = userService.retrieveUserThroughID(user_id);

            UserEntity user = optionalUser.get();

            username = user.getUsername();
            request.setAttribute("username", username);

            if (user_id != -1) {
                userOrders = userService.retrieveAllOrdersOfUser(user.getUser_id());


                request.setAttribute("userOrders", userOrders);
                System.out.println(userOrders);
                if (userOrders.size() == 0) {
                    request.setAttribute("errorMessageSize", errorMessageSize);
                }
            }

        } catch (NumberFormatException e) {
            //the user accessed as a guest
            user_id = -1;
            request.getSession().setAttribute("user_id", user_id);
        }

        List<AvailableServicePackEntity> availableServicePackages = userService.getAllServicePackages();
        request.setAttribute("availableServicePackages", availableServicePackages);
        request.setAttribute("user_id", user_id);

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
