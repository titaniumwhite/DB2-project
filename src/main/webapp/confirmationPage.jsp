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
        UserEntity user = (UserEntity) request.getSession().getAttribute("user");
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
<%
    ServicePackEntity servicePack = (ServicePackEntity) request.getAttribute("servicePack");

%>
<section>
    <div class="container d-flex" style="justify-content: center; align-content: center; padding-top: 1.5rem">
        <div class="col-lg-4">
            <div class="card card-margin">
                <div class="card-header no-border">
                    <h5 class="card-title" style="text-align: center">Package to buy: <%=servicePack.getAvailablePackage().getName()%></h5>


<div class="card-text">
    <form action="buyservice" method="post">
        <p style="font-size: medium; text-align: center; justify-content: center"><b>Chosen period of subscription: </b></p>
        <div class="row"  style="padding-left: 1.5rem">
        <div class="form-check" style="padding-left: 3rem !important;">
            <p><%=servicePack.getChosenPeriod().getDuration()%> months (<%=servicePack.getChosenPeriod().getMonthlyFee()%> &euro;/month)</p>
        </div>
</div>

</form>

</div>

</div>
</div>


</div>


<br> <br>





</div>

</section>
    <%
        if(user!=null){
    %>
    <div>
       <form action="confirmationPage" method="post">
           <button name="result" value="placeable" type="submit">BUY SERVICE</button>
           <button name="result" value="notPlaceable" type="submit">BUY SERVICE</button>
       </form>
    </div>
    <br>
    <br>
    <form action="confirmationPage" method="post">
        <button name="result" value="random" type="submit">BUY SERVICE (Da vedere a che serve)</button>
    </form>

    <%
        }
        else {
    %>
    <form action="login" method="get">
        <button type="submit">LOGIN/REGISTER</button>
    </form>
    <%
        }
    %>
</div>
</body>
</html>
