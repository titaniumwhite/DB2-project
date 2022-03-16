package it.polimi.dbproject.servlet;

import it.polimi.dbproject.entities.AvailableServicePackEntity;
import it.polimi.dbproject.entities.OptionalServiceEntity;
import it.polimi.dbproject.entities.PeriodEntity;
import it.polimi.dbproject.entities.ServicePackEntity;
import it.polimi.dbproject.services.UserService;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.html.Option;
import java.io.IOException;

import java.time.LocalDate;
import java.util.*;

@WebServlet(name = "buyServiceServlet", value = "/buyservice")

public class BuyServiceServlet extends HttpServlet{

    @EJB
    private UserService userService;

    private List<PeriodEntity> periods;
    private List<OptionalServiceEntity> optionalServices;
    String availablePackages;
    String toServlet = null;
    ServicePackEntity servicePack = null;
    String selectedPackages = null;

    @Override
    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response) throws IOException{

        HttpSession session = request.getSession();
        toServlet = "confirmationpage";

        AvailableServicePackEntity selectedPackage = (AvailableServicePackEntity)session.getAttribute("selectedPackage");

        String chosenPeriod = request.getParameter("chosenPeriod");
        String[] chosenOptionalServices = request.getParameterValues("chosenOptionalServices");
        String chosenStartDate = request.getParameter("chosenStartDate");

        PeriodEntity periodOfValidity = userService.retrievePeriodID(Integer.parseInt(chosenPeriod)).get();
        ArrayList<OptionalServiceEntity> optionalServices = new ArrayList<>();

        int totalAmountOptionalServices = 0;

        if(chosenOptionalServices.length != 0){
            for (String optServ : chosenOptionalServices)
                optionalServices.add(userService.retrieveOptionalServicePackByID(Integer.parseInt(optServ)).get());

            for (OptionalServiceEntity optionalService1: optionalServices)
                totalAmountOptionalServices = totalAmountOptionalServices + optionalService1.getMonthly_fee() * periodOfValidity.getDuration();
       }

        int costPackage = periodOfValidity.getMonthlyFee() * periodOfValidity.getDuration();

        LocalDate startLocalDate, endLocalDate;
        java.sql.Date startDateInSQL, endDateInSQL;

        startLocalDate = LocalDate.parse(chosenStartDate);
        endLocalDate = startLocalDate.plusMonths(periodOfValidity.getDuration());

        startDateInSQL = java.sql.Date.valueOf(startLocalDate);
        endDateInSQL = java.sql.Date.valueOf(endLocalDate);

        servicePack = new ServicePackEntity(startDateInSQL,
                endDateInSQL,
                costPackage,
                totalAmountOptionalServices,
                selectedPackage,
                periodOfValidity,
                optionalServices);

        session.setAttribute("servicePack", servicePack);
        toServlet = "confirmationpage";

        response.sendRedirect(toServlet);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();

        int package_id = Integer.parseInt(request.getParameter("package_id"));
        Optional<AvailableServicePackEntity> optionalServicePack = userService.retrieveAvailableServicePackByID(package_id);
        AvailableServicePackEntity selectedPackage = optionalServicePack.get();
        session.setAttribute("selectedPackage", selectedPackage);

        List<PeriodEntity> periods = userService.retrieveAllPeriods();
        request.setAttribute("periods", periods);

        ArrayList<OptionalServiceEntity> optionalServices = new ArrayList<>(userService.retrieveAllOptionalServices());
        ArrayList<OptionalServiceEntity> includedOptionalServices = new ArrayList<>(selectedPackage.getOptionalServices());

        optionalServices.removeAll(includedOptionalServices);

        request.setAttribute("optionalServices", optionalServices);

        RequestDispatcher dispatcher = request.getRequestDispatcher("buyServicePage.jsp");
        dispatcher.forward(request, response);
    }
}
