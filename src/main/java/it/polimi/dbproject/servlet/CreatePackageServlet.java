package it.polimi.dbproject.servlet;

import it.polimi.dbproject.entities.AvailableServicePackEntity;
import it.polimi.dbproject.entities.ServiceEntity;
import it.polimi.dbproject.services.EmployeeService;
import it.polimi.dbproject.services.UserService;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "CreatePackageServlet", value = "/createpackage")
public class CreatePackageServlet extends HttpServlet {

    @EJB
    private EmployeeService es;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String[] mobilePhone = (String [])request.getParameterValues("mobilePhone");
        String[] mobileInternet = (String [])request.getParameterValues("mobileInternet");
        String[] fixedPhone = (String [])request.getParameterValues("fixedPhone");
        String[] fixedInternet = (String [])request.getParameterValues("fixedInternet");
        List<ServiceEntity> services = new ArrayList<>();

        System.out.println(name);
        System.out.println(java.util.Arrays.toString(mobilePhone));
        System.out.println(java.util.Arrays.toString(mobileInternet));
        System.out.println(java.util.Arrays.toString(fixedPhone));
        System.out.println(java.util.Arrays.toString(fixedInternet));


        if (mobilePhone != null && Objects.equals(mobilePhone[0], "on")) {
            services.add(
                    new ServiceEntity("mobile phone",
                    Integer.parseInt(mobilePhone[1]),
                    Integer.parseInt(mobilePhone[2]),
                    0,
                    Integer.parseInt(mobilePhone[3]),
                    Integer.parseInt(mobilePhone[4]),
                    0)
            );
        }

        if (mobileInternet != null && Objects.equals(mobileInternet[0], "on")) {
            services.add(
                    new ServiceEntity("mobile internet",
                    0,
                    0,
                    Integer.parseInt(mobileInternet[1]),
                    0,
                    0,
                    Integer.parseInt(mobileInternet[2]))
            );
        }

        if (fixedPhone != null && Objects.equals(fixedPhone[0], "on")) {
            services.add(new ServiceEntity("fixed phone", 0, 0, 0, 0, 0, 0));
        }

        if (fixedInternet != null && Objects.equals(fixedInternet[0], "on")) {
            services.add(new ServiceEntity("fixed internet", 0, 0, 0, 0, 0, 0));
        }

        es.createAvailableServicePack(name, services);
    }
}
