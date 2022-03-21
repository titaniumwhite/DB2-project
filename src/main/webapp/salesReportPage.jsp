<%@ page import="it.polimi.dbproject.entities.OptionalServiceEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="javax.swing.text.html.Option" %>
<%@ page import="it.polimi.dbproject.entities.PeriodEntity" %>
<%@ page import="it.polimi.dbproject.entities.AvailableServicePackEntity" %>
<%@ page import="it.polimi.dbproject.entities.ServicePackEntity" %>
<%@ page import="it.polimi.dbproject.entities.queries.*" %><%--
  Created by IntelliJ IDEA.
  User: gabri
  Date: 09/03/2022
  Time: 08:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <style><%@include file="style.css"%></style>

</head>
<body style="background-color: #508bfc;">
<%
    List<AvailableServicePackEntity> availablePackages = (List<AvailableServicePackEntity>) request.getAttribute("availablePackages");
    ServicePackEntity choosenPackage = (ServicePackEntity)request.getAttribute("choosenPackage");

    PurchasesPerPackage purchasesPerPackage = (PurchasesPerPackage) request.getAttribute("purchasesPerPackage");
    PurchasesPerPackageAndPeriod purchasesPerPackageAndPeriod = (PurchasesPerPackageAndPeriod) request.getAttribute("purchasesPerPackageAndPeriod");

    List<PeriodEntity> periods = (List<PeriodEntity>) request.getAttribute("periods");

    SalesPerPackage salesPerPackage = (SalesPerPackage) request.getAttribute("salesPerPackage");

    AVG_numOptionServPerServPack averageOptionalProductsPerPackage = (AVG_numOptionServPerServPack) request.getAttribute("AVG_numOptionServPerServPack");

    List<Errors> errors = (List<Errors>) request.getAttribute("errors");
    List<PendingOrders> pendingOrders = (List<PendingOrders>) request.getAttribute("pendingOrders");
    //List<InsolventUsers> insolventUsers = (List<InsolventUsers>) request.getAttribute("insolventUsers");

    BestOptionalService bestOptionalProduct = (BestOptionalService) request.getAttribute("bestOptionalProduct");

%>



<nav class="navbar navbar-expand-sm bg-dark navbar-dark">

    <ul class="navbar-nav me-auto mb-2 mb-lg-0" style="display: inline-flex; width: 60%">
        <li class="nav-item"><a class="nav-link" href="./" style="color: white; float: left !important; display: flex">Logout</a></li>
    </ul>
    <ul class="navbar-nav me-auto mb-2 mb-lg-0" style="display: inline-flex; width: 2%">
        <li class="nav-item" style="color: white; padding: 0.5rem; text-align: right !important; display: flex">${username}</li>
    </ul>

</nav>

<section>
    <div class="container d-flex" style="justify-content: center; align-content: center; padding-top: 1.5rem">
        <div class="col-lg-12">
            <div class="card card-margin">
                <div class="card-header no-border">
                    <h5 class="card-title" style="text-align: center">Sales Report</h5>
                </div>

                <div class="row">

                    <form action="salesreport" method="post">
                        <label for="servicePackage">Choose a service package:</label>
                        <select name="servicePackage" id="servicePackage">
                            <%
                                for (AvailableServicePackEntity servicePackage: availablePackages) {
                            %>
                            <option value="<%=servicePackage.getAvailableServicePack_id()%>"><%=servicePackage.getName() %></option>
                            <%
                                }
                            %>
                        </select>
                        <br><br>

                        <label for="period">Choose a validity period:</label>
                        <select name="period" id="period">
                            <%
                                for (PeriodEntity period: periods) {
                            %>
                            <option value="<%=period.getPeriod_id()%>"><%=period.getDuration()%></option>
                            <%
                                }
                            %>
                        </select>
                        <br><br>
                        <button class="btn btn-primary" type="submit">Submit</button>
                    </form>
                </div>

                <div class="card-text"><h3>Total Purchases per Package</h3></div>
                <div class="card-text"><h3>Total Purchases per Package and Period</h3></div>
                <div class="card-text"><h3>Total Value of sales with Optional Products</h3></div>
                <div class="card-text"><h3>Total Value of sales without Optional Products</h3></div>
                <div class="card-text"><h3>Average number of Optional Services sold together with each Service Package</h3></div>
                <div class="card-text"><h3>List of Insolvent Users</h3></div>
                <div class="card-text"><h3>List of Suspended Orders</h3></div>
                <div class="card-text"><h3>List of Errors</h3></div>
                <div class="card-text"><h3>Best Seller Optional Service</h3></div>


            </div>
        </div>
    </div>

</section>
</body>


</html>
