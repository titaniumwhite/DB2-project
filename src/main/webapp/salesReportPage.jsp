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
    List<PeriodEntity> periods = (List<PeriodEntity>) request.getAttribute("periods");

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
        <form action="salesreport" method="post">
            <p> Choose a service package: </p>
            <select name="choosen_package" id="srvPackage">
                <%
                    for (AvailableServicePackEntity a: availableServicePack) {
                %>
                <option value="<%=a.getAvailableServicePack_id()%>"><%=a.getName()%></option>
                <%
                    }
                %>
            </select>
            <p>Choose a period: </p>
            <select name="choosen_period" id="period">
                <%
                    for(PeriodEntity p: periods) {
                %>
                <option value="<%=p.getPeriod_id()%>"><%=p.getDuration()%></option>
                <%
                    }
                %>
            </select>
            <br>
            <br>
            <br>
            <button name="button" type="submit">SELECT PACKAGE</button>
        </form>

        <div>
            <h2>TOTAL PURCHASE PER PACKAGE</h2>
            <p>${purchasesPerPackage}</p>

        </div>
        <br>
        <br>
        <div>
            <h2>TOTAL PURCHASE PER PACKAGE AND PERIOD</h2>
            <p>${purchasesPerPackageAndPeriod}</p>

        </div>
        <br>
        <br>
        <div>
            <h2> TOTAL SALES PER PACKAGE WITH AND WITHOUT OPTIONAL SERVICE </h2>
            <p>${salesPerPackage_no_opt}</p>
            <p>${salesPerPackage_with_opt}</p>

        </div>
        <br>
        <br>
        <div>
            <h2> AVERAGE NUMBER OF OPTIONAL SERVICE SOLD WITH EACH PACKAGE </h2>
            <p>${avg_numOptionServPerServPack}</p>

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
