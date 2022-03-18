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

@WebServlet(name = "confirmationPage", value = "/confirmationpage")
public class ConfirmationServlet extends HttpServlet {

    @EJB
    private UserService userService;
    private ServicePackEntity servicePack;
    boolean createOrder = true;
    String Id_OrderRejected;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        UserEntity user = (UserEntity) session.getAttribute("user");
        servicePack = (ServicePackEntity) session.getAttribute("servicePack");

        String confirm = request.getParameter("confirm");
        String toServlet = "homepage";
        OrderEntity order;

        boolean isPlaceable;

        switch (confirm) {
            case "confirm":
                isPlaceable = true;
                break;

            case "reject":
                isPlaceable = false;
                break;

            default:
                throw new IllegalStateException("EXCEPTION STATE: " + confirm);
        }

        if(createOrder){
            try {
                if (servicePack.getUser() == null)
                servicePack = userService.createServicePack(servicePack, user);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            order = userService.createOrder(new Timestamp(System.currentTimeMillis()), user, servicePack, isPlaceable);
        } else {
            order = userService.retrieveOrderThroughID(Integer.parseInt(Id_OrderRejected)).get();
            order = userService.orderUpdate(order, isPlaceable);
        }

        if(!isPlaceable) user = userService.addFailedPayment(user);

        if(user.getFailedPayments() == 3){
            ErrorEntity error = new ErrorEntity(order.getCreation_ts(), user, order.getTotal_cost());
            userService.createError(error);
            user = userService.setFailedPayments(user);
        }

        if(userService.retrieveFailedOrderthroughUser(user.getUser_id()).size()>=1)
            userService.userIsInsolvent(user, true);
        else
            userService.userIsInsolvent(user, false);

        response.sendRedirect(toServlet);

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if (request.getParameter("order") == null ) {

            Id_OrderRejected = request.getParameter("OrderRejected");

            if (Id_OrderRejected != null) {
                servicePack = userService.retrieveOrderThroughID(Integer.parseInt(Id_OrderRejected)).get().getChosenServicePackage();
                createOrder = false;
            } else {
                servicePack = (ServicePackEntity) request.getSession(false).getAttribute("servicePack");
                createOrder = true;
            }

            request.setAttribute("servicePack", servicePack);
            RequestDispatcher dispatcher = request.getRequestDispatcher("confirmationPage.jsp");
            dispatcher.forward(request, response);

        } else {
            int order_id = Integer.parseInt(request.getParameter("order"));
            int service_pack_id = Integer.parseInt(request.getParameter("service"));
            System.out.println("Spero funzioni " + order_id + "  " + service_pack_id);

            servicePack = userService.retrieveServicePackThroughId(service_pack_id).get();

            session.setAttribute("servicePack", servicePack);

            RequestDispatcher dispatcher = request.getRequestDispatcher("confirmationPage.jsp");
            dispatcher.forward(request, response);
        }
    }
}
