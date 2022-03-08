package it.polimi.dbproject.servlet;

import it.polimi.dbproject.entities.AvailableServicePackEntity;
import it.polimi.dbproject.services.UserService;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "customerHomepageServlet", value = "/homepage")
public class customerHomepageServlet extends HttpServlet {

    @EJB
    UserService us;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("customerHomepage.jsp");

        List<AvailableServicePackEntity> availableServicePackages = us.getAllServicePackages();
        request.setAttribute("availableServicePackages", availableServicePackages);

        System.out.println(availableServicePackages);

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
