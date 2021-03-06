<%@ page import="java.sql.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="it.polimi.dbproject.entities.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Objects" %>
<%@ page import="org.w3c.dom.html.HTMLTableCaptionElement" %>
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
            List<OrderEntity> userOrders = (List<OrderEntity>) request.getAttribute("userOrders");
        %>
    <h2><b>My Orders</b></h2>
    <div class="row">
        <%
            if( user != null && userOrders != null && userOrders.size() != 0  ) {
                System.out.println(userOrders);
                for (OrderEntity o: userOrders){%>
        <div class="col-sm-6">
            <div class="card border-success">
                <div class="card-header">
                    <h5 class="card-title">ID ORDER: <%=o.getOrder_id()%></h5>
                </div>
                <div class="card-body text-success">
                    <p class="card-text">Created: <%=o.getCreation_ts()%></p>
                    <p class="card-text">Total Cost: <%=o.getTotal_cost()%></p>
                </div>
            </div>
            <br>
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

        <%
                List<OrderEntity> pendingOrders = (List<OrderEntity>) request.getSession().getAttribute("pendingOrders");
        %>
    <h2><b>My Pending Orders</b></h2>
    <div class="row">
        <%
            if( user != null && pendingOrders != null && pendingOrders.size() != 0 ) {
                System.out.println(userOrders);
                for (OrderEntity o: pendingOrders){%>
        <div class="col-sm-6">
            <div class="card border-warning">
                <div class="card-header">
                    <h5 class="card-title">ID ORDER: <%=o.getOrder_id()%></h5>
                    <% int order_id = o.getOrder_id(); int service_pack_id = o.getChosenServicePackage().getServicePack_id(); %>
                </div>
                <div class="card-body text-warning">
                    <!-- <p class="card-text">Chosen Service Package: <%//=o.getChosenServicePackage().getAvailablePackage().getName()%></p> -->
                    <p class="card-text">Created: <%=o.getCreation_ts()%></p>
                    <p class="card-text">Total Cost: <%=o.getTotal_cost()%></p>
                    <form action="homepage" method="post"  >
                        <a href="confirmationpage?order=<%=order_id%>&service=<%=service_pack_id%>" class="btn btn-outline-warning" onclick="document.getElementById('myform').submit();"><c:out value="<%=order_id%>"/>Re-Order</a>
                    </form>
                </div>
            </div>
            <br>
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

            <%
                 List<ErrorEntity> userErrors = (List<ErrorEntity>) request.getAttribute("userErrors");
            %>
        <h2><b>My Errors</b></h2>
        <div class="row">
            <% if (user != null && userErrors.size() != 0) { %>
            <% for (ErrorEntity o: userErrors){%>
            <div class="col-sm-6">
                <div class="card border-danger">
                    <div class="card-header">
                        <h5 class="card-title"><%=o.getError_id()%></h5>
                    </div>
                    <div class="card-body text-danger">
                        <p class="card-text">Created: <%=o.getTs()%></p>
                        <p class="card-text">Last Rejection Cost: <%=o.getTot_number()%></p>
                    </div>
                </div>
                <br>
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
                            <p style="text-align: center; justify-content: center; position: center;"><i>CONGRATULATION! You do not have any error.</i></p>
                        </div>
                    </div>
                </div>
            </div>
            <% }%>
        </div>
        <br><br>

            <%
                List<AvailableServicePackEntity> servicePackages = (List<AvailableServicePackEntity>) request.getSession().getAttribute("availableServicePackages");
            %>
            <h2><b>Available Service Packages</b></h2>
            <div class="row">
                <%  if (servicePackages != null) {
                    for (AvailableServicePackEntity sp: servicePackages) {%>
                <div class="col-sm-6">
                    <div class="card">
                        <div class="card-header">
                            <h5 class="card-title"><%=sp.getName()%></h5>
                        </div>
                        <div class="card-body">
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
                    <br>
                </div>
                <% }} else {%>
                <div class="col d-flex align-items-stretch"  style="padding-bottom: calc(1.5rem * 1.5);">
                    <div class="card-deck">
                        <div class="card card-margin">
                            <div class="card-body p-0.5">
                                <p style="text-align: center; justify-content: center; position: center;"><i>There are no available package!</i></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        <% } %>

</section>


</body>

</html>