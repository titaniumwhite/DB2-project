package it.polimi.dbproject.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CreatePackageServlet", value = "/createpackage")
public class CreatePackageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String services = (String)request.getParameter("mobilePhone");
        String optionalServices = (String)request.getParameter("minutes");
        String[] periods = request.getParameterValues("periods");
        System.out.println(name);
        System.out.println(services);
        System.out.println(optionalServices);
        System.out.println(periods);
    }
}
