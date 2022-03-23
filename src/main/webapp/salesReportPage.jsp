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
    <a href="employeehomepage" class="btn btn-outline-primary" role="button" >Back</a>
    <ul class="navbar-nav me-auto mb-2 mb-lg-0" style="display: inline-flex; width: 2%">
        <li class="nav-item" style="color: white; padding: 0.5rem; text-align: right !important; display: flex">${username}</li>
    </ul>

</nav>

<section>
    <div>
        <div class="container d-flex" style="justify-content: center; align-content: center; text-align: center; padding-top: 1.5rem">
            <div class="card" style="width: 75%">
                <div class="card-body">
        <h1>SALES REPORT</h1>
                    <br>
        <form action="salesreport" method="post">
            <div class="input-group mb-3" style="text-align: center; justify-content: center; align-content: center">
                <div class="input-group-prepend">
                    <label class="input-group-text" for="srvPackage">Choose a Service Pack</label>
                </div>
            <select class="custom-select" name="choosen_package" id="srvPackage">
                <%
                    for (AvailableServicePackEntity a: availableServicePack) {
                %>
                <option value="<%=a.getAvailableServicePack_id()%>"><%=a.getName()%></option>
                <%
                    }
                %>
            </select>
            </div>
            <div class="input-group mb-3" style="text-align: center; justify-content: center; align-content: center">
                <div class="input-group-prepend">
                    <label class="input-group-text" for="period">Choose a period: </label>
                </div>
            <select class="custom-select" name="choosen_period" id="period">
                <%
                    for(PeriodEntity p: periods) {
                %>
                <option value="<%=p.getPeriod_id()%>"><%=p.getDuration()%></option>
                <%
                    }
                %>
            </select>
            </div>
            <button class="btn btn-primary" name="button" type="submit">SUBMIT</button>
        </form>
                </div>
            </div>
        </div>
        <div class="container d-flex" style="justify-content: center; align-content: center; text-align: center; padding-top: 1.5rem">
            <div class="card" style="width: 75%">
                <div class="card-body">
            <h2 class="card-title">TOTAL PURCHASE OF SELECTED PACKAGE</h2>
                    <p style="font-size: large"><b><i>${purchasesPerPackage}</i></b></p>
                </div>
            </div>
        </div>
        <br>
        <div class="container d-flex" style="justify-content: center; align-content: center; text-align: center; padding-top: 1.5rem">
            <div class="card" style="width: 75%">
                <div class="card-body">
            <h2 class="card-title">TOTAL PURCHASE OF SELECTED PACKAGE AND PERIOD</h2>
                    <p style="font-size: large"><b><i>${purchasesPerPackageAndPeriod}</i></b></p>
                </div>
            </div>
        </div>
        <br>
        <div class="container d-flex" style="justify-content: center; align-content: center; text-align: center; padding-top: 1.5rem">
            <div class="card" style="width: 75%">
                <div class="card-body">
            <h2 class="card-title"> TOTAL SALES OF SELECTED PACKAGE WITHOUT AND WITH OPTIONAL SERVICE </h2>
                    <p style="font-size: large"><b>Without: <i>${salesPerPackage_no_opt}</i></b></p>
                    <p style="font-size: large"><b>With: <i>${salesPerPackage_with_opt}</i></b></p>
                </div>
            </div>
        </div>
        <br>
        <div class="container d-flex" style="justify-content: center; align-content: center; text-align: center; padding-top: 1.5rem">
            <div class="card" style="width: 75%">
                <div class="card-body">
            <h2 class="card-title"> AVERAGE OPTIONAL SERVICE SOLD WITH SELECTED PACKAGE </h2>
                    <p style="font-size: large"><b><i>${avg_numOptionServPerServPack}</i></b></p>
                </div>
            </div>
        </div>
        <br>
        <div class="container d-flex" style="justify-content: center; align-content: center; padding-top: 1.5rem">
            <div class="card" style="width: 75%">
                <div class="card-body">
            <h2 class="card-title">INSOLVENT USERS</h2>
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
            </div>
        </div>
    </div>
    <br>
        <div class="container d-flex" style="justify-content: center; align-content: center; padding-top: 1.5rem">
            <div class="card" style="width: 75%">
                <div class="card-body">
            <h2 class="card-title">SUSPENDED ORDERS</h2>
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
                </div>
            </div>
        </div>
        <br>
        <div class="container d-flex" style="justify-content: center; align-content: center; padding-top: 1.5rem">
        <div class="card" style="width: 75%">
            <div class="card-body">
            <h2 class="card-title">USER ERRORS</h2>
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">Username</th>
                <th scope="col">Error Package Cost</th>
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
                <th scope="row"><%=e.getError().getTot_number()%></th>
                <th scope="row"><%=e.getError().getTs()%></th>
            </tr>
            <%
                    }}
            %>
            </tbody>
        </table>
            </div>
        </div>
        </div>
        <br>

        <div class="container d-flex" style="justify-content: center; align-content: center; text-align: center; padding-top: 1.5rem">
            <div class="card" style="width: 75%">
                <div class="card-body">
            <h2 class="card-title"> BEST SELLER OPTIONAL SERVICE</h2>
            <h4 class="card-subtitle mb-2 text-muted"> The best seller optional service is that among all optional services which has recorder the highest income for the Company</h4>
            <div>
                <%
                    if(bestOptionalService != null){
                %>
                <p style="font-size: large"><b><i><%=bestOptionalService.getOptionalService().getName()%></i></b></p>
                <%
                    } else {
                %>
                <p style="font-size: large"><i>No Optional Service has been sold yet!</i></p>
                <%
                    }
                %>
            </div>
                </div>
            </div>
        </div>
        <br>
        <br>
    </div>
</section>
</body>


</html>
