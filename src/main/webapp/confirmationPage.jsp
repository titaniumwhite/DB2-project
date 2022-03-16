<%@ page import="java.util.List" %>
<%@ page import="it.polimi.dbproject.entities.*" %><%--
  Created by IntelliJ IDEA.
  User: simbo
  Date: 10/03/2022
  Time: 16:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
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

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <%
        ServicePackEntity servicePack = (ServicePackEntity) session.getAttribute("servicePack");
        UserEntity user = (UserEntity) session.getAttribute("user");
    %>
    <ul class="navbar-nav me-auto mb-2 mb-lg-0" style="display: inline-flex; width: 90%">
        <li class="nav-item"><a class="nav-link" href="./" style="color: white; float: left !important; display: flex">Logout</a></li>
    </ul>
    <ul class="navbar-nav me-auto mb-2 mb-lg-0" style="display: inline-flex; width: 2%">
        <% if(user != null){ %>
        <li class="nav-item" style="color: white; padding: 0.5rem; text-align: right !important; display: flex"><%=user.getUsername()%></li>
        <% } else { %>
        <li class="nav-item" style="color: white; padding: 0.5rem; text-align: right !important; display: flex">Guest</li>
        <% } %>
    </ul>

</nav>

<section>
    <div class="container d-flex" style="justify-content: center; align-content: center; padding-top: 1.5rem">
        <div class="col-lg-8">
            <div class="card card-margin">
                <div class="card-header no-border">
                    <h5 class="card-title" style="text-align: center"><b>Package to buy: <%=servicePack.getAvailablePackage().getName()%></b></h5>


<div class="card-text">
    <form action="buyservice" method="post">
        <p style="font-size: large; text-align: center; justify-content: center"><b>Chosen period of subscription: </b></p>
        <div class="row"  style="justify-content: center">
        <div class="form-check">
            <p style="font-size: medium; text-align: center; justify-content: center"><%=servicePack.getChosenPeriod().getDuration()%> months</p>
        </div>
        </div>
        <p style="font-size: medium; text-align: center; justify-content: center"><b>Start date of subscription: </b></p>
        <div class="row"  style="justify-content: center">
        <div class="form-check">
            <p style="font-size: medium; text-align: center; justify-content: center"><%=servicePack.getStartDate()%></p>
        </div>
        </div>
        <p style="font-size: medium; text-align: center; justify-content: center"><b>End date of subscription: </b></p>
        <div class="row"  style="justify-content: center">
            <div class="form-check">
                <p style="font-size: medium; text-align: center; justify-content: center"><%=servicePack.getEndDate()%></p>
            </div>
        </div>

        <% if (servicePack.getSelectedOptionalServices().size() > 0) {%>
        <p style="font-size: medium; text-align: center; justify-content: center"><b>Additional optional services chosen:</b></p>
        <ul class="list-group">
            <% for (OptionalServiceEntity os: servicePack.getSelectedOptionalServices()) {%>
            <li class="list-group-item">
                <p style="font-size: medium; text-align: center; justify-content: center" name="chosenOptionalServices" id="chosenOptionalServices" ><%=os.getName()%> (<%=os.getMonthly_fee()%> &euro;/month)</p>
            </li>
            <% } %>
        </ul

        <p style="font-size: medium; text-align: center; justify-content: center"><b>Total cost for the duration of the subscription: </b></p>
        <div class="row"  style="justify-content: center">
            <div class="form-check">
                <p style="font-size: medium; text-align: center; justify-content: center"><%=servicePack.getTotalCost()%>&euro;</p>
            </div>
        </div>



        <%}%>
        </form>
            <div class="row" style="justify-content: center; padding-top: 1rem">
                <% if(user != null){ %>
                    <form action="confirmationpage" method="post">
                        <button class="btn btn-primary btn-lg btn-block" name="confirm" value="confirm" type="submit"> BUY (ACCEPTANCE) </button>
                        <div style="justify-content: center; padding-top: 1rem">
                        </div>
                        <button class="btn btn-primary btn-lg btn-block" name="confirm" value="reject" type="submit"> BUY (REJECT) </button>
                    </form>
                    <% } else { %>
                    <form action="login" method="post">
                        <button class="btn btn-primary btn-lg btn-block" type="submit"> LOGIN/REGISTER </button>
                    </form>
                    <% } %>
            </div>
</div>

</div>
</div>


</div>

</div>

</section>

</body>
</html>
