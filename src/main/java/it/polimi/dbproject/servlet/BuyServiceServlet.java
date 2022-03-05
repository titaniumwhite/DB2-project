package it.polimi.dbproject.servlet;

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

import java.util.*;

@WebServlet("/buyServicePage")

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
            //selectedPackages = userService. //
        }
    }
}
