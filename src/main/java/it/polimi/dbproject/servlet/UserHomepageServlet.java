package it.polimi.dbproject.servlet;

import it.polimi.dbproject.entities.*;
import it.polimi.dbproject.services.UserService;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@WebServlet(name = "UserHomepageServlet", value = "/homepage")
public class UserHomepageServlet extends HttpServlet {

    @EJB
    UserService userService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("userHomepage.jsp");
        HttpSession session = request.getSession();
        UserEntity user = null;
        List<OrderEntity> userOrders = null;
        List<OrderEntity> pendingOrders = null;
        List<ErrorEntity> userErrors = null;


        if (Objects.equals(request.getParameter("guest"), "guest")) {
            session.removeAttribute("user");
        } else {
            try {
                user = (UserEntity) session.getAttribute("user");
                if (user != null) {
                    request.setAttribute("username", user.getUsername());

                    userOrders = userService.retrieveAllOrdersOfUser(user.getUser_id());
                    request.setAttribute("userOrders", userOrders);

                    pendingOrders = userService.retrievePendingOrder(user.getUser_id());
                    request.setAttribute("pendingOrders", pendingOrders);

                    userErrors = userService.retrieveAllErrorsOfUser(user.getUser_id());
                    request.setAttribute("userErrors", userErrors);

                    System.out.println(userErrors.toString());
                } else {

                }
            } catch (NumberFormatException e) {
                //the user accessed as a guest
                request.setAttribute("user", user);
            }
        }

        List<AvailableServicePackEntity> availableServicePackages = userService.getAllServicePackages();
        request.setAttribute("availableServicePackages", availableServicePackages);



        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*HttpSession session = request.getSession();

        String toServlet = "confirmationpage";
        String reOrder = request.getParameter("reOrder");
        System.out.println("ciaoooo");
        long order_id = Long.parseLong(request.getParameter("order_id"));

        if(reOrder.equals("reOrder")){
                System.out.println("weeeeee");
                //System.out.println("order_id = " + order_id);
                Optional<ServicePackEntity> optionalServicePack = userService.retrieveServicePackThroughOrderId(order_id);
                ServicePackEntity servicePack = optionalServicePack.get();
                System.out.println(servicePack);
                session.setAttribute("servicePack", servicePack);
                RequestDispatcher dispatcher = request.getRequestDispatcher("confirmationPage.jsp");
                dispatcher.forward(request, response);
                response.sendRedirect(toServlet);
        }*/
    }
}
