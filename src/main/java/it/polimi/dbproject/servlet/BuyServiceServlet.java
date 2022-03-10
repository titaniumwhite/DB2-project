package it.polimi.dbproject.servlet;

import it.polimi.dbproject.entities.AvailableServicePackEntity;
import it.polimi.dbproject.entities.OptionalServiceEntity;
import it.polimi.dbproject.entities.PeriodEntity;
import it.polimi.dbproject.entities.ServicePackEntity;
import it.polimi.dbproject.services.UserService;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
    protected  void doPost(HttpServletRequest request,
                           HttpServletResponse response) throws IOException{

        HttpSession session = request.getSession();
        toServlet = "buyServicePage";

        if(request.getParameter(("selectServicePackBTN")) != null){
            System.out.println(request.getParameter("selectServicePackBTN"));
            availablePackages = request.getParameter("servicePack");

            //DA CONTINUARE DOPO AVER SCRITTO LA USER SERVICE //
            selectedPackages = userService.retrieveAvailableServicePackByID(Integer.parseInt(availablePackages)).get().getName();
            periods = userService.retrieveServicePeriodID(Integer.parseInt(availablePackages));
            optionalServices = userService.retrieveOptionalOfAvailablePackage(Integer.parseInt(availablePackages));
        }
        else if(request.getParameter("buttonToConfirm") != null){

            String period = request.getParameter("period");

            String[] optionalService = request.getParameterValues("optionalService");
            String startDate = request.getParameter("startDate");

            LocalDate startLocalDate, endLocalDate;
            java.sql.Date startDateInSQL, endDateInSQL;

            AvailableServicePackEntity availableServicePack = userService.retrieveAvailableServicePackByID(Integer.parseInt(availablePackages)).get();
            PeriodEntity periodOfValidity = userService.retrievePeriodID(Integer.parseInt(period)).get();

            ArrayList<OptionalServiceEntity> optionalServices = null;
            int totalAmountOptionalServices = 0;

            if(optionalService != null){
                optionalServices = new ArrayList<>();
                for (String optServ : optionalService){
                    optionalServices.add(userService.retrieveOptionalServicePackByID(Integer.parseInt(optServ)).get());
                }
                for (OptionalServiceEntity optionalService1: optionalServices)
                    totalAmountOptionalServices = totalAmountOptionalServices + optionalService1.getMonthly_fee() * periodOfValidity.getDuration();
            }

            int costPackage = periodOfValidity.getMonthlyFee() * periodOfValidity.getDuration();
        }
    }
}
