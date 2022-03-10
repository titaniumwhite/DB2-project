package it.polimi.dbproject.servlet;

import it.polimi.dbproject.entities.AvailableServicePackEntity;
import it.polimi.dbproject.entities.OptionalServiceEntity;
import it.polimi.dbproject.entities.PeriodEntity;
import it.polimi.dbproject.entities.ServiceEntity;
import it.polimi.dbproject.services.EmployeeService;
import it.polimi.dbproject.services.UserService;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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

        String[] periodsToOffer = (String[])request.getParameterValues("periods");
        String[] optionalServicesToOffer = (String[])request.getParameterValues("optionalServices");

        System.out.println(Arrays.toString(periodsToOffer));
        System.out.println(Arrays.toString(optionalServicesToOffer));

        List<ServiceEntity> services = new ArrayList<>();

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

        List<PeriodEntity> availablePeriods = es.getAllPeriods();
        List<PeriodEntity> periods = new ArrayList<>();
        if (periodsToOffer != null) {
            for (int i = 0; i < periodsToOffer.length; i++) {
                for (int j = 0; j < availablePeriods.size(); j++) {
                    if (availablePeriods.get(j).getDuration() == Integer.parseInt(periodsToOffer[i])) {
                        periods.add(availablePeriods.get(j));
                    }
                }
            }
        }

        List<OptionalServiceEntity> availableOptionalServices = es.getAllOptionalServices();
        List<OptionalServiceEntity> optionalServices = new ArrayList<>();
        if (optionalServicesToOffer != null) {
            for (int i = 0; i < optionalServicesToOffer.length; i++) {
                for (int j = 0; j < availableOptionalServices.size(); j++) {
                    if (Objects.equals(availableOptionalServices.get(j).getName(), optionalServicesToOffer[i])) {
                        optionalServices.add(availableOptionalServices.get(j));
                    }
                }
            }
        }

        es.createAvailableServicePack(name, services, periods, optionalServices);
    }
}
