package it.polimi.dbproject.servlet;

import it.polimi.dbproject.entities.AvailableServicePackEntity;
import it.polimi.dbproject.entities.EmployeeEntity;
import it.polimi.dbproject.entities.ErrorEntity;
import it.polimi.dbproject.entities.PeriodEntity;
import it.polimi.dbproject.entities.queries.*;
import it.polimi.dbproject.entities.queries.PendingOrders;
import it.polimi.dbproject.services.EmployeeService;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SalesReportServlet", value = "/salesreport")
public class SalesReportServlet extends HttpServlet {

    @EJB
    private EmployeeService employeeService;

    private PurchasesPerPackageEntity purchasesPerPackage;
    private PurchasesPerPackageAndPeriod purchasesPerPackageAndPeriod;
    private SalesPerPackage salesPerPackage;
    private AVG_numOptionServPerServPack AVG_numOptionServPerServPack;
    private AvailableServicePackEntity choosenPackage;
    private List<PeriodEntity> periods;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<AvailableServicePackEntity> availablePackages = employeeService.retrieveAllAvailableServicePackages();
        request.setAttribute("availablePackages", availablePackages);

        List<PeriodEntity> periods = employeeService.retrieveAllPeriods();
        request.setAttribute("periods", periods);

        //List<PurchasesPerPackageEntity> purchasesPerPackage = ???
        request.setAttribute("purchasesPerPackage", purchasesPerPackage);

        //request.setAttribute("choosenPackage", choosenPackage);

        //List<PurchasesPerPackageAndPeriod> purchasesPerPackageAndPeriods = ???
        request.setAttribute("purchagesPerPackageAndPeriod", purchasesPerPackageAndPeriod);

        request.setAttribute("salesPerPackage", salesPerPackage);

        request.setAttribute("AVG_numOptionServPerServPack", AVG_numOptionServPerServPack);

        //List<InsolventUsers> insolventUsers = employeeService.retrieveAllInsolventUsers();
        //request.setAttribute("insolventUsers", insolventUsers);

        //List<PendingOrders> pendingOrders = employeeService.retrieveAllPendingOrders();
        //request.setAttribute("pendingOrders", pendingOrders);

        //List<Errors> errors = employeeService.retrieveAllErrors();
        //request.setAttribute("errors", errors);

        //BestOptionalProduct bestOptionalProduct = employeeService.retrieveBestOptionalProduct();
        //request.setAttribute("bestOptionalProduct", bestOptionalProduct);

        RequestDispatcher dispatcher = request.getRequestDispatcher("salesReportPage.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int servicePackage_id = Integer.parseInt(request.getParameter("servicePackage"));
        int period_id = Integer.parseInt(request.getParameter("period"));
        String toServlet = "salesreport?package=" + servicePackage_id + "&period=" + period_id;

        System.out.println(servicePackage_id + "   " + period_id);

        int test = 6;

        if (test == 1) {
            // 1st
            PurchasesPerPackageEntity purchasesPerPackage = employeeService.purchasesPerPackage(servicePackage_id);
            System.out.println(purchasesPerPackage);
        } else if (test == 2) {
            // 2nd
            PurchasesPerPackageAndPeriod purchasesPerPackageAndPeriod = employeeService.retrievePurchasesPerPackageAndPeriod(servicePackage_id, period_id);
            System.out.println(purchasesPerPackageAndPeriod);
        } else if (test == 3) {


            // 3rd
        } else if (test == 4) {

            // 4th
            AVG_numOptionServPerServPack averageOptionalProductsPerPackage = employeeService.retrieveAverageOptionalProductsPerPackage(servicePackage_id);
            System.out.println(averageOptionalProductsPerPackage);
        } else if (test == 5) {

            // 5th
            List<PendingOrders> pendingOrders = employeeService.retrieveAllPendingOrders();
            System.out.println(pendingOrders);


            List<InsolventUsers> insolventUsers = employeeService.retrieveAllInsolventUsers();
            System.out.println(insolventUsers);

            List<Errors> errors = employeeService.retrieveAllErrors();
            System.out.println(errors);
        } else if (test == 6) {

            // 6th
            BestOptionalProduct bestOptionalProducts = employeeService.retrieveBestOptionalProduct();
            System.out.println(bestOptionalProducts);
        }



        response.sendRedirect(toServlet);
    }
}
