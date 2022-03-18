<%@ page import="java.sql.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="it.polimi.dbproject.entities.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Objects" %>
<!doctype html>

<html lang="en">
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

<section>
<div class="container">
        <%
            List<ErrorEntity> userErrors = (List<ErrorEntity>) request.getAttribute("userErrors");
        %>
        <% if (user != null && userErrors.size() != 0) { %>
        <div class="card-deck">
            <h2><b>My Errors</b></h2>
            <div class="row" style="width: auto !important;">
                <% for (ErrorEntity o: userErrors){%>
                <div class="col ">
                    <div class="card card-margin">
                        <div class="card-header no-border">
                            <h5 class="card-title"><%=o.getError_id()%></h5>
                            <div class="card-body p-0.5">
                                <p>Created: <%=o.getTs()%></p>
                                <p>Last Rejection Cost: <%=o.getTot_number()%></p>
                            </div>
                        </div>
                    </div>
                    <% }%>
                </div>
            </div>
        </div>
        <% }%>

        <%
            List<OrderEntity> userOrders = (List<OrderEntity>) request.getAttribute("userOrders");
        %>
        <h2><b>My Orders</b></h2>
        <div class="card-deck">
            <div class="row" style="width: auto !important;">
                <%
                    if( user != null && userOrders != null && userOrders.size() != 0  ) {
                        System.out.println(userOrders);
                        for (OrderEntity o: userOrders){%>
                    <div class="col ">
                <div class="card card-margin">
                    <div class="card-header no-border">
                        <h5 class="card-title"><%=o.getOrder_id()%></h5>
                        <div class="card-body p-0.5">
                            <p>Created: <%=o.getCreation_ts()%></p>
                            <p>Total Cost: <%=o.getTotal_cost()%></p>
                        </div>
                    </div>
                </div>
                        <% }} else if (user == null){%>
                        <div class="col d-flex align-items-stretch"  style="padding-bottom: calc(1.5rem * 1.5);">
                        <div class="card-deck">
                            <div class="card card-margin">
                                <div class="card-body p-0.5">
                                    <p style="text-align: center; justify-content: center; position: center;"><i>You need to login to place an order!</i></p>
                                </div>
                            </div>
                        </div>
                        </div>
                        <% } else {%>
                        <div class="col d-flex align-items-stretch"  style="padding-bottom: calc(1.5rem * 1.5);">
                            <div class="card-deck">
                                <div class="card card-margin">
                                    <div class="card-body p-0.5">
                                        <p style="text-align: center; justify-content: center; position: center;"><i>You have not placed any order yet!</i></p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <% } %>

            </div>
        </div>
        </div>

            <section>
                <div class="container">
                    <%
                        List<OrderEntity> pendingOrders = (List<OrderEntity>) request.getAttribute("pendingOrders");
                    %>
                    <h2><b>My Pending Orders</b></h2>
                    <div class="card-deck">
                        <div class="row" style="width: auto !important;">
                            <%
                                if( user != null && pendingOrders != null && pendingOrders.size() != 0 ) {
                                    System.out.println(userOrders);
                                    for (OrderEntity o: pendingOrders){%>
                            <div class="col">
                                <div class="card card-margin">
                                    <div class="card-header no-border">

                                        <h5 class="card-title"><% long order_id = o.getOrder_id();%></h5>
                                        <div class="card-body p-0.5">
                                            <p>Created: <%=o.getCreation_ts()%></p>
                                            <p>Total Cost: <%=o.getTotal_cost()%></p>
                                            <form action="homepage" method="post" >
                                                <a><button class="btn btn-outline-primary" name="reOrder"value="reOrder" type="submit"><c:out value="<%=order_id%>"/>Re-Order</button></a>
                                            </form>
                                        </div>

                                    </div>
                                </div>
                                <% }} else if (user == null){%>
                                <div class="col d-flex align-items-stretch"  style="padding-bottom: calc(1.5rem * 1.5);">
                                    <div class="card-deck">
                                        <div class="card card-margin">
                                            <div class="card-body p-0.5">
                                                <p style="text-align: center; justify-content: center; position: center;"><i>You need to login to place an order!</i></p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <% } else {%>
                                <div class="col d-flex align-items-stretch"  style="padding-bottom: calc(1.5rem * 1.5);">
                                    <div class="card-deck">
                                        <div class="card card-margin">
                                            <div class="card-body p-0.5">
                                                <p style="text-align: center; justify-content: center; position: center;"><i>CONGRATULATION! You do not have any pending order.</i></p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <% } %>

                            </div>
                        </div>
                    </div>

    <br>
                    <br>
    <div class="container">
    <% List<AvailableServicePackEntity> servicePackages = (List<AvailableServicePackEntity>) request.getAttribute("availableServicePackages");
    %>

        <h2><b>Available Service Packages</b></h2>
    <div class="card-deck">
        <div class="row" style="width: auto !important;">
    <%  if (servicePackages != null) {
            for (AvailableServicePackEntity sp: servicePackages) {%>
            <div class="col align-items-stretch"  style="padding-bottom: calc(1.5rem * 1.5);">
                <div class="card card-margin">
                    <div class="card-header no-border">
                        <h5 class="card-title"><%=sp.getName()%></h5>
                    </div>
                    <div class="card-body pt-0">
                        Included services:
                        <ul>
                        <% for (ServiceEntity s: sp.getServices()) {%>
                            <li><%String type=s.getType();%>
                                <%=type%>
                            <% if (Objects.equals(type, "mobile phone") ) {
                                int minutes = s.getMinutes();
                                int sms = s.getSms();
                                int extra_minutes = s.getExtraMinutes_fee();
                                int extra_sms = s.getExtraSms_fee();
                            %>: <ul>
                                    <li><%=minutes%> minutes (afterwards <%=extra_minutes%>&euro;/min)</li>
                                    <li><%=sms%> sms (afterwards <%=extra_sms%>&euro;/sms)</li>
                                </ul>
                            <%}%>
                            <% if (Objects.equals(type, "mobile internet") ) {
                                int gigas = s.getGigas();
                                int extra_gigas = s.getExtraGigas_fee();
                            %>: <%=gigas%> gigas (afterwards <%=extra_gigas%>&euro;/giga) </li>



                        <% }} %>
                        </ul>

                        Included optional services:
                        <ul>
                            <% for (OptionalServiceEntity o: sp.getOptionalServices()) {%>
                            <li><%=o.getName()%></li>
                            <%}%>
                        </ul>

                        </div>
                    </p>

                    <% int package_id = sp.getAvailableServicePack_id();%>
                    <a href="buyservice?package_id=<%=package_id%>" class="btn btn-primary"><c:out value="<%=package_id%>"/> Buy </a>
                </div>

            </div>

            <% }} %>

        </div>
    </div>

    </div>

                </div>
</section>


</body>

</html>