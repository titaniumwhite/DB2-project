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
    List<AvailableServicePackEntity> availableServicePack = (List<AvailableServicePackEntity>) request.getAttribute("availableServicePack");
    AvailableServicePackEntity availableServicePackChosen = (AvailableServicePackEntity) request.getAttribute("availableServicePackChosen");

    PurchasesPerPackage purchasesPerPackage = (PurchasesPerPackage) request.getAttribute("purchasesPerPackage");
    PurchasesPerPackageAndPeriod purchasesPerPackageAndPeriod = (PurchasesPerPackageAndPeriod) request.getAttribute("purchasesPerPackageAndPeriod");
    List<PeriodEntity> periods = (List<PeriodEntity>) request.getAttribute("periods");

    SalesPerPackage salesPerPackage = (SalesPerPackage) request.getAttribute("salesPerPackage");

    AVG_numOptionServPerServPack avg_numOptionServPerServPack = (AVG_numOptionServPerServPack) request.getAttribute("avg_numOptionServPerServPack");

    List<Errors> errors = (List<Errors>) request.getAttribute("errors");
    List<PendingOrders> pendingOrders = (List<PendingOrders>) request.getAttribute("pendingOrders");
    List<InsolventUsers> insolventUsers = (List<InsolventUsers>) request.getAttribute("insolventUsers");

    BestOptionalService bestOptionalService = (BestOptionalService) request.getAttribute("bestOptionalService");
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
    <div>
        <h1>SALES REPORT</h1>
        <div>
            <h2>TOTAL PURCHASE PER PACKAGE</h2>
            <form action="salesreport" method="post">
                <p> Choose a service package: </p>
                <select name="srvPackage" id="srvPackage">
                    <%
                        for (AvailableServicePackEntity a: availableServicePack) {
                    %>
                    <option value="<%=a.getAvailableServicePack_id()%>"><%=a.getName()%></option>
                    <%
                        }
                    %>
                </select>
                <br>
                <br>
                <button name="button" type="submit">SELECT PACKAGE</button>
                <br>
                <br>
                <%
                    if(purchasesPerPackage != null) {
                %>
                <p><%=purchasesPerPackage.getTotalOrder()%></p>
                <%
                    }
                %>
            </form>
        </div>
        <br>
        <br>
        <div>
            <h2>TOTAL PURCHASE PER PACKAGE AND PERIOD</h2>
            <form action="salesreport" method="post">
                <p> Choose a service package: </p>
                <select name="srvPackageWithPeriod" id="srvPackageWithPeriod">
                    <%
                        for (AvailableServicePackEntity a: availableServicePack) {
                    %>
                    <option value="<%=a.getAvailableServicePack_id()%>"><%=a.getName()%></option>
                    <%
                        }
                    %>
                </select>
                <br>
                <br>
                <button name="button" type="submit">SELECT PACKAGE</button>
                <br>
                <br>
            </form>
                <%
                    if(periods != null) {
                %>
                <br>
                <p>Package Selected: <%=availableServicePackChosen.getName()%></p>
                <form action="salesreport" method="post">
                    <br>
                    <br>
                    <p>Choose a period: </p>
                    <select name="period" id="period">
                        <%
                            for(PeriodEntity p: periods) {
                        %>
                        <option value="<%=p.getPeriod_id()%>"><%=p.toString()%></option>
                        <%
                            }
                        %>
                    </select>
                    <br>
                    <br>
                    <button type="submit">SELECT PERIOD</button>
                </form>
                <br>
                <br>
                <%
                    if(purchasesPerPackageAndPeriod != null){
                %>
                <p><%=purchasesPerPackageAndPeriod.getTotalNumber()%></p>
                <%
                    }}
                %>
        </div>
        <br>
        <br>
        <div>
            <h2> TOTAL SALES PER PACKAGE WITH AND WITHOUT OPTIONAL SERVICE </h2>
            <form action="salesreport" method="post">
                <p>Choose a service pack: </p>
                <select name="srvPackageWithOptProducts" id="srvPackageWithOptProducts">
                    <%
                        for (AvailableServicePackEntity a: availableServicePack) {
                    %>
                    <option value="<%=a.getAvailableServicePack_id()%>"><%=a.getName()%></option>
                    <%
                        }
                    %>
                </select>
                <br>
                <br>
                <button name="button" type="submit">SELECT PACKAGE</button>
                <%
                    if(salesPerPackage != null) {
                %>
                <p>Sales No Optional<%=salesPerPackage.getTotalSalesNoOptional()%></p>
                <p>Sales With Optional<%=salesPerPackage.getTotalSalesWithOptional()%></p>
                <%
                    }
                %>
            </form>
        </div>
        <br>
        <br>
        <div>
            <h2> AVERAGE NUMBER OF OPTIONAL SERVICE SOLD WITH EACH PACKAGE </h2>
            <form action="salesreport" method="post">
                <p>Choose a service pack: </p>
                <select name="srvPackageAvg" id="srvPackageAvg">
                    <%
                        for (AvailableServicePackEntity a: availableServicePack) {
                    %>
                    <option value="<%=a.getAvailableServicePack_id()%>"><%=a.getName()%></option>
                    <%
                        }
                    %>
                </select>
                <br>
                <br>
                <button name="button" type="submit">SELECT PACKAGE</button>
                <%
                    if(avg_numOptionServPerServPack != null) {
                %>
                <p><%=avg_numOptionServPerServPack.getAverage()%></p>
                <%
                    }
                %>
            </form>
        </div>
        <br>
        <br>
        <div>
            <h2>INSOLVENT USERS</h2>
        </div>
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">User</th>
            </tr>
            </thead>
            <tbody>
            <%
                if(insolventUsers != null){
                    for (InsolventUsers iu: insolventUsers) {
            %>
            <tr>
                <th scope="row"><%=iu.getUser().getUsername()%></th>
            </tr>
            <%
                }}
            %>
            </tbody>
        </table>

        <div>
            <h2>SUSPENDED ORDERS</h2>
        </div>
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">User</th>
                <th scope="col">Date & Hour</th>
            </tr>
            </thead>
            <tbody>
            <%
                if(pendingOrders != null){
                    for (PendingOrders po: pendingOrders) {
            %>
            <tr>
                <th scope="row"><%=po.getOrder().getOwner().getUsername()%></th>
                <th scope="row"><%=po.getOrder().getCreation_ts()%></th>
            </tr>
            <%
                    }}
            %>
            </tbody>
        </table>

        <div>
            <h2>USER ERRORS</h2>
        </div>
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">Username</th>
                <th scope="col">#Errors</th>
                <th scope="col">Date & Hour Last Rejection</th>
            </tr>
            </thead>
            <tbody>
            <%
                if(errors != null){
                    for (Errors e: errors) {
            %>
            <tr>
                <th scope="row"><%=e.getError().getOwner().getUsername()%></th>
                <th scope="row"><%=e.getError().getOwner().getErrors().size()%></th>
                <th scope="row"><%=e.getError().getTs()%></th>
            </tr>
            <%
                    }}
            %>
            </tbody>
        </table>
        <br>
        <br>

        <div>
            <h2> BEST SELLER OPTIONAL SERVICE</h2>
            <div>
                <%
                    if(bestOptionalService != null){
                %>
                <p><%=bestOptionalService.getOptionalService().getName()%></p>
                <%
                    } else {
                %>
                <p> No Optional Service has been sold yet!</p>
                <%
                    }
                %>
            </div>
        </div>
    </div>
</section>
</body>


</html>
