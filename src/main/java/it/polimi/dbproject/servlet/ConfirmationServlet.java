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

    ServicePackEntity servicePack;
    boolean creationOfPackage = true;
    String IDOrderRejected;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        UserEntity user = (UserEntity) session.getAttribute("user");

        List<OrderEntity> orders = userService.retrieveAllOrdersOfUser(user.getUser_id());

        String result = request.getParameter("result");

        String toServlet;
        OrderEntity order;

        boolean isPlaceable;

        switch (result) {
            case "placeable":
                isPlaceable = true;
                break;

            case "notPlaceable":
                isPlaceable = false;
                break;

            case "random":
                isPlaceable = userService.randomPayment();
                break;

            default:
                throw new IllegalStateException("Unexpected result: " + result);
        }

        if(creationOfPackage) {
            try{
                servicePack = userService.addressServiceToUser(servicePack, user);
            } catch (SQLException throwables){
                throwables.printStackTrace();
            }
            order = userService.createOrder(new Timestamp(System.currentTimeMillis()), user, servicePack, isPlaceable);
        }
        else {
            order = userService.retrieveOrderThroughID(Integer.parseInt(IDOrderRejected)).get();
            order = userService.orderUpdate(order, isPlaceable);
        }

        if(!isPlaceable)
            user = userService.addFailedPayment(user);

        if(user.getFailedPayments() == 3){
            ErrorEntity error = new ErrorEntity(order.getCreation_ts(), user, order.getTotal_cost());
            userService.createError(error);
            user = userService.setFailedPayments(user);

        }

        if(userService.retrieveFailedOrderthroughUser(user.getUser_id()).size() >= 1)
            userService.userIsInsolvent(user, true);
        else
            userService.userIsInsolvent(user, false);

        if(userService.retrievePendingOrder(user.getUser_id()).size()>0) toServlet = "serviceAcquisition";
        else toServlet = "homepage";

        response.sendRedirect(toServlet);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        IDOrderRejected = request.getParameter("IDOrderRejected");

        if(IDOrderRejected != null) {
            servicePack = userService.retrieveOrderThroughID(Integer.parseInt(IDOrderRejected)).get().getChosenServicePackage();
            creationOfPackage = true;
        } else {
            servicePack = (ServicePackEntity) request.getSession(false).getAttribute("servicePack");
            creationOfPackage = true;
        }

        request.setAttribute("servicePack", servicePack);

        RequestDispatcher dispatcher = request.getRequestDispatcher("ConfirmationPage.jps");
        dispatcher.forward(request, response);
    }
}
