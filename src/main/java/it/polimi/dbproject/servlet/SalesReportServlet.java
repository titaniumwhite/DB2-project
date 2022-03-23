package it.polimi.dbproject.servlet;

import it.polimi.dbproject.entities.*;
import it.polimi.dbproject.entities.queries.*;
import it.polimi.dbproject.entities.queries.PendingOrders;
import it.polimi.dbproject.services.EmployeeService;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "SalesReportServlet", value = "/salesreport")
public class SalesReportServlet extends HttpServlet {

    @EJB
    private EmployeeService employeeService;

    private AvailableServicePackEntity availableServicePackChosen;
    private List<PeriodEntity> periods = null;
    private PurchasesPerPackage purchasesPerPackage = null;
    private PurchasesPerPackageAndPeriod purchasesPerPackageAndPeriod = null;
    private SalesPerPackage salesPerPackage = null;
    private AVG_numOptionServPerServPack avg_numOptionServPerServPack = null;
    String username = null;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        request.setAttribute("availableServicePack", employeeService.retrieveAllAvailableServicePackages());
        request.setAttribute("periods", employeeService.retrieveAllPeriods());
        username = (String) session.getAttribute("username");
        System.out.println(username);
        session.setAttribute("username", username);

        if (purchasesPerPackage != null) {
            request.setAttribute("purchasesPerPackage", purchasesPerPackage.getTotalOrder());
        }

        if (purchasesPerPackageAndPeriod != null) {
            request.setAttribute("purchasesPerPackageAndPeriod", purchasesPerPackageAndPeriod.getTotalNumber());
        }

        if (salesPerPackage != null) {
            request.setAttribute("salesPerPackage_no_opt", salesPerPackage.getTotalSalesNoOptional());
            request.setAttribute("salesPerPackage_with_opt", salesPerPackage.getTotalSalesWithOptional());
        }

        if (avg_numOptionServPerServPack != null) {
            request.setAttribute("avg_numOptionServPerServPack", avg_numOptionServPerServPack.getAverage());
        }

        List<InsolventUsers> insolventUsers = employeeService.retrieveAllInsolventUsers();
        request.setAttribute("insolventUsers", insolventUsers);

        List<PendingOrders> pendingOrders = employeeService.retrieveAllPendingOrders();
        request.setAttribute("pendingOrders", pendingOrders);

        List<ErrorEntity> errors = employeeService.retrieveAllErrors();
        request.setAttribute("errors", errors);

        BestOptionalService bestOptionalService = employeeService.retrieveBestOptionalProduct();
        request.setAttribute("bestOptionalService", bestOptionalService);

        RequestDispatcher dispatcher = request.getRequestDispatcher("salesReportPage.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String toServlet = "salesreport";

        String choosen_package = request.getParameter("choosen_package");
        String choosen_period = request.getParameter("choosen_period");

        if(choosen_package != null) {
            purchasesPerPackage = employeeService.purchasesPerPackage(Integer.parseInt(choosen_package));
            request.setAttribute("purchasesPerPackage", purchasesPerPackage);

            if (choosen_period != null) {
                purchasesPerPackageAndPeriod = employeeService.retrievePurchasesPerPackageAndPeriod(Integer.parseInt(choosen_package), Integer.parseInt(choosen_period));
                request.setAttribute("purchasesPerPackageAndPeriod", purchasesPerPackageAndPeriod);
            }

            salesPerPackage = employeeService.retrieveSalesPerPackage(Integer.parseInt(choosen_package));

            avg_numOptionServPerServPack = employeeService.retrieveAverageOptionalProductsPerPackage(Integer.parseInt(choosen_package));

        }

        response.sendRedirect(toServlet);

    }
}
