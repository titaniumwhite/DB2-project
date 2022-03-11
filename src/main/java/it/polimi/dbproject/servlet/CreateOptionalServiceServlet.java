package it.polimi.dbproject.servlet;

import it.polimi.dbproject.entities.OptionalServiceEntity;
import it.polimi.dbproject.services.EmployeeService;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CreateOptionalServiceServlet", value = "/createoptionalservice")
public class CreateOptionalServiceServlet extends HttpServlet {

    @EJB
    private EmployeeService es;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        int fee = Integer.parseInt(request.getParameter("fee"));

        es.createOptionalService(name, fee);
    }
}
