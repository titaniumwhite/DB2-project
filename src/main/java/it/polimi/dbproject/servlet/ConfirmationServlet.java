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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        List<OrderEntity> userOrdersList = (List<OrderEntity>)session.getAttribute("userOrders");
        List<OrderEntity> pendingOrdersList = (List<OrderEntity>)session.getAttribute("pendingOrders");

        ArrayList<OrderEntity> userOrders = new ArrayList<>(userOrdersList);
        ArrayList<OrderEntity> pendingOrders = new ArrayList<>(pendingOrdersList);

        String confirm = request.getParameter("confirm");
        String toServlet = "homepage";
        OrderEntity order = null;
        Optional<OrderEntity> optionalOrder = null;
        int order_id = -1;

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
                if (servicePack.getUser() == null) {
                    servicePack = userService.createServicePack(servicePack, user);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


            if (session.getAttribute("order_id") != null) {
                order_id = (int) session.getAttribute("order_id");
                optionalOrder = userService.retrieveOrderThroughID(order_id);
            }

            boolean alreadyExist = false;

            if (optionalOrder != null && optionalOrder.isPresent()) {
                order = optionalOrder.get();
                alreadyExist = true;
            }


            if (alreadyExist && isPlaceable) {

                pendingOrders.remove(order);
                userOrders.add(order);
                session.setAttribute("userOrders", userOrders);
                session.setAttribute("pendingOrders", pendingOrders);

                userService.updateOrder(order, true);
            } else if (!alreadyExist){
                System.out.println("entro qua222 e creo nuovo ordine");
                order = userService.createOrder(new Timestamp(System.currentTimeMillis()), user, servicePack, isPlaceable);
            }


        } else {
            System.out.println("entro qua 3");

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
            session.setAttribute("order_id", Id_OrderRejected);


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

            session.setAttribute("order_id", order_id);
            session.setAttribute("rebuy_servicepack_id", service_pack_id);

            servicePack = userService.retrieveServicePackThroughId(service_pack_id).get();

            session.setAttribute("servicePack", servicePack);

            RequestDispatcher dispatcher = request.getRequestDispatcher("confirmationPage.jsp");
            dispatcher.forward(request, response);
        }
    }
}
