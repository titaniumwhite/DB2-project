package it.polimi.dbproject.servlet;

import it.polimi.dbproject.entities.AvailableServicePackEntity;
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
    private AverageOptionalProductsPerPackage averageOptionalProductsPerPackage;
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

        request.setAttribute("averageOptionalProductsPerPackage", averageOptionalProductsPerPackage);

        List<InsolventUsers> insolventUsers = employeeService.retrieveAllInsolventUsers();
        request.setAttribute("insolventUsers", insolventUsers);

        List<PendingOrders> pendingOrders = employeeService.retrieveAllPendingOrders();
        request.setAttribute("pendingOrders", pendingOrders);

        List<Errors> errors = employeeService.retrieveAllErrors();
        request.setAttribute("errors", errors);

        BestOptionalProduct bestOptionalProduct = employeeService.retrieveBestOptionalProduct();
        request.setAttribute("bestOptionalProduct", bestOptionalProduct);

        RequestDispatcher dispatcher = request.getRequestDispatcher("salesReportPage.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servicePackage = request.getParameter("servicePackage");

        String servicePackagePerPeriod = request.getParameter("servicePackagePerPeriod");

    }
}
