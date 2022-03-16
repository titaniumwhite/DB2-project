<%@ page import="it.polimi.dbproject.entities.OrderEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="it.polimi.dbproject.entities.UserEntity" %>
<%@ page import="it.polimi.dbproject.entities.ServiceEntity" %>
<%@ page import="it.polimi.dbproject.entities.OptionalServiceEntity" %><%--
  Created by IntelliJ IDEA.
  User: simbo
  Date: 16/03/2022
  Time: 17:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Activation Of Service</title>
</head>

    <%
        List<OrderEntity> pendingOrders = (List<OrderEntity>) request.getAttribute("pendingOrders");
    %>
<body>

    <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
        <%
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

    <h1><b>Pending Orders:</b></h1>
    <br>
    <h2><b>Pending Orders:</b></h2>
    <div class="card-deck">
        <div class="row">
            <%  if (pendingOrders != null) {
                for (OrderEntity o: pendingOrders) {%>
            <div class="col-lg-4 d-flex align-items-stretch"  style="padding-bottom: calc(1.5rem * 1.5);">
                <div class="card card-margin">
                    <div class="card-header no-border">
                        <h5 class="card-title"><%=o.getOrder_id()%></h5>
                    </div>
                    <div class="card-body pt-0">
                        Chosen Service Package:
                            <%=o.getChosenServicePackage().getAvailablePackage().getName()%>
                        Included optional services:
                        <ul>
                            <% for (OptionalServiceEntity os: o.getChosenServicePackage().getSelectedOptionalServices()) {%>
                            <li><%=os.getName()%></li>
                            <%}%>
                        </ul>

                    </div>
                    </p>
                    <p style="font-size: large; text-align: center; justify-content: center"><b>Chosen period of subscription: </b></p>
                    <div class="row"  style="justify-content: center">
                        <div class="form-check">
                            <p style="font-size: medium; text-align: center; justify-content: center"><%=o.getChosenServicePackage().getChosenPeriod().getDuration()%>months</p>
                        </div>
                    </div>
                    <p style="font-size: large; text-align: center; justify-content: center"><b>Chosen period of subscription: </b></p>
                    <div class="row"  style="justify-content: center">
                        <div class="form-check">
                            <p style="font-size: medium; text-align: center; justify-content: center"><%=o.getTotal_cost()%></p>
                        </div>
                    </div>
                    <p style="font-size: large; text-align: center; justify-content: center"><b>Chosen period of subscription: </b></p>
                    <div class="row"  style="justify-content: center">
                        <div class="form-check">
                            <p style="font-size: medium; text-align: center; justify-content: center"><%=o.getChosenServicePackage().getStartDate()%></p>
                        </div>
                    </div>
                    <p style="font-size: large; text-align: center; justify-content: center"><b>Chosen period of subscription: </b></p>
                    <div class="row"  style="justify-content: center">
                        <div class="form-check">
                            <p style="font-size: medium; text-align: center; justify-content: center"><%=o.getChosenServicePackage().getEndDate()%></p>
                        </div>
                    </div>
                </div>
            </div>

            <% }} %>

        </div>
    </div>

    </div>

</body>
</html>
