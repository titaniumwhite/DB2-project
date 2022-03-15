package it.polimi.dbproject.servlet;

import it.polimi.dbproject.entities.*;
import it.polimi.dbproject.services.EmployeeService;
import it.polimi.dbproject.services.UserService;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "EmployeeHomepageServlet", value = "/employeehomepage")
public class EmployeeHomepageServlet extends HttpServlet {

    @EJB
    EmployeeService employeeService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("employeeHomepage.jsp");
        HttpSession session = request.getSession();
        EmployeeEntity employee;
        try {
            employee = (EmployeeEntity) session.getAttribute("employee");
            request.setAttribute("username", employee.getUsername());

        } catch (NumberFormatException e) {
            //the user accessed as a guest
            System.out.println("EXCEPTION");

        }

        List<OptionalServiceEntity> optionalServices = employeeService.getAllOptionalServices();
        request.setAttribute("optionalServices", optionalServices);

        List<PeriodEntity> periods = employeeService.getAllPeriods();
        request.setAttribute("periods", periods);


        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
