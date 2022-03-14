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

    <ul class="navbar-nav me-auto mb-2 mb-lg-0" style="display: inline-flex; width: 90%">
        <li class="nav-item"><a class="nav-link" href="./" style="color: white; float: left !important; display: flex">Logout</a></li>
    </ul>
    <ul class="navbar-nav me-auto mb-2 mb-lg-0" style="display: inline-flex; width: 2%">
        <li class="nav-item" style="color: white; padding: 0.5rem; text-align: right !important; display: flex">${username}</li>
    </ul>

</nav>

<section>
    <div class="container">
        <%List<OrderEntity> userOrders = (List<OrderEntity>) request.getAttribute("userOrders");
        %>
        <h2>My Orders</h2>
        <div class="card-deck">
            <div class="row">
                <%
                    if(userOrders.size() != 0 && userOrders != null) {
                        System.out.println(userOrders);
                        for (OrderEntity o: userOrders){%>
                    <div class="col-lg-4 d-flex align-items-stretch">
                <div class="card card-margin">
                    <div class="card-header no-border">
                        <h5 class="card-title"><%=o.getOrder_id()%></h5>
                    </div>
                </div>
                        <% }} else {%>
                        <p>You have not placed any order yet!</p>
                        <% }
                %>

            </div>
        </div>
        </div>



    <div class="container">
    <% List<AvailableServicePackEntity> servicePackages = (List<AvailableServicePackEntity>) request.getAttribute("availableServicePackages");
    %>

    <h2>Service Packages</h2>
    <div class="card-deck">
        <div class="row">
    <%  if (servicePackages != null) {
            for (AvailableServicePackEntity sp: servicePackages) {%>
            <div class="col-lg-4 d-flex align-items-stretch"  style="padding-bottom: calc(1.5rem * 1.5);">
                <div class="card card-margin">
                    <div class="card-header no-border">
                        <h5 class="card-title"><%=sp.getName()%></h5>
                    </div>
                    <div class="card-body pt-0">
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
                        </div>
                    </ul>
                    </p>
                <% int package_id = sp.getAvailableServicePack_id();%>
                <% int user_id =(int)request.getAttribute("user_id"); %>
                <a href="buyservice?user_id=<%=user_id%>&package_id=<%=package_id%>" class="btn btn-primary"><c:out value="<%=user_id%>"/><c:out value="<%=package_id%>"/> Buy </a>
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