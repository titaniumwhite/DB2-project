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

    ServicePackEntity servicePack;
    boolean createOrder = true;
    String Id_OrderRejected;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        UserEntity user = (UserEntity) session.getAttribute("user");
        ServicePackEntity chosenServicePackage = (ServicePackEntity) session.getAttribute("servicePack");

        String confirm = request.getParameter("buttonConfirm");
        String toServlet;
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
                servicePack = userService.createServicePack(servicePack, user);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            order = userService.createOrder(new Timestamp(System.currentTimeMillis()), user, servicePack, isPlaceable);
        } else {
            order = userService.retrieveOrderThroughID(Integer.parseInt(Id_OrderRejected)).get();
            order = userService.orderUpdate(order, isPlaceable);
        }



        RequestDispatcher dispatcher = request.getRequestDispatcher("confirmationPage.jsp");
        dispatcher.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();


    }
}
