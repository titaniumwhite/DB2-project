package it.polimi.dbproject.servlet;

import it.polimi.dbproject.entities.AvailableServicePackEntity;
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
        String toServlet = "employeehomepage";

        try {
            OptionalServiceEntity os = es.createOptionalService(name, fee);

            if (os != null) toServlet = "employeehomepage?createoptionalserviceDone=true";
            else toServlet = "employeehomepage?createoptionalserviceError=true";
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect(toServlet);
    }
}
