<%@ page import ="java.sql.*" %>
<%@ page import ="java.util.*" %>
<%@ page import ="it.polimi.dbproject.entities.*" %>
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

    <ul class="navbar-nav me-auto mb-2 mb-lg-0" style="display: inline-flex; width: 90%">
        <li class="nav-item"><a class="nav-link" href="./" style="color: white; float: left !important; display: flex">Logout</a></li>
    </ul>
    <ul class="navbar-nav me-auto mb-2 mb-lg-0" style="display: inline-flex; width: 2%">
        <li class="nav-item" style="color: white; padding: 0.5rem; text-align: right !important; display: flex">${username}</li>
    </ul>

</nav>

<%

    List<PeriodEntity> periods = (List<PeriodEntity>) request.getAttribute("periods");
    List<OptionalServiceEntity> optionalServices = (List<OptionalServiceEntity>) request.getAttribute("optionalServices");
    String selectedPackage = (String) request.getAttribute("servicePack");

    UserEntity user;
    String username = null;

    if(request.getSession().getAttribute("user")!=null){
        user = (UserEntity) request.getSession().getAttribute("user");
        username = user.getUsername();
    }
%>


<section>
    <div class="container">
        <div class="col-lg-4 d-flex align-items-stretch"  style="padding-bottom: calc(1.5rem * 1.5);">
            <div class="card card-margin">
                <div class="card-header no-border">
                    <h5 class="card-title">Service Package to buy</h5>
                </div>
                <div class="card-body pt-0">
                    <ul>
                        <li><%String type=selectedPackage.getType();%>
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
            </div>

        </div>

    </div>

</section>

</body>
</html>
