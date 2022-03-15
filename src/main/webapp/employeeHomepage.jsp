<%@ page import="it.polimi.dbproject.entities.OptionalServiceEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="javax.swing.text.html.Option" %>
<%@ page import="it.polimi.dbproject.entities.PeriodEntity" %><%--
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

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">

    <ul class="navbar-nav me-auto mb-2 mb-lg-0" style="display: inline-flex; width: 60%">
        <li class="nav-item"><a class="nav-link" href="./" style="color: white; float: left !important; display: flex">Logout</a></li>
    </ul>

    <a href="employeehomepage/salesreport" class="btn btn-outline-primary" role="button" >Sales Report</a>

    <button type="button" class="btn btn-outline-primary" onclick="overlayOn()">New Optional Service</button>

    <ul class="navbar-nav me-auto mb-2 mb-lg-0" style="display: inline-flex; width: 2%">
        <li class="nav-item" style="color: white; padding: 0.5rem; text-align: right !important; display: flex">${username}</li>
    </ul>

</nav>

<section>
    <div class="container d-flex" style="justify-content: center; align-content: center; padding-top: 1.5rem">
        <div class="col-lg-8">
            <div class="card card-margin">
                <div class="card-header no-border">
                    <h5 class="card-title" style="text-align: center">Create a new Service Package</h5>
                </div>
                <div class="card-text">
    <form action="createpackage" method="post">
        <br><br>
        Package Name <input type="text" name="name"/><br/><br/>
        Services
        <div class="form-check form-switch">
            <input class="form-check-input" name="mobilePhone" type="checkbox" role="switch" id="mobilePhone" onclick="enableDisableTextbox(this)">
            <label class="form-check-label" for="mobilePhone">Mobile Phone</label>
            <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon">Minutes</span>
                <input type="text" class="form-control" name="mobilePhone" id="minutes" value=0 disabled>
            </div>
            <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon1">SMS</span>
                <input type="text" class="form-control" name="mobilePhone" id="sms" value=0 disabled>
            </div>
            <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon2">Fee for extra minutes</span>
                <input type="text" class="form-control" name="mobilePhone" id="fee_minutes" value=0 disabled>
            </div>
            <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon3">Fee for extra SMS</span>
                <input type="text" class="form-control" name="mobilePhone" id="fee_sms" value=0 disabled>
            </div>
        </div>

        <div class="form-check form-switch">
            <input class="form-check-input" name="mobileInternet" type="checkbox" role="switch" id="mobileInternet" onclick="enableDisableTextbox(this)">
            <label class="form-check-label" for="mobileInternet">Mobile Internet</label>
            <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon4">Gigas</span>
                <input type="text"  name="mobileInternet"  class="form-control" id="gigas" value=0 disabled>
            </div>
            <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon5">Fee for extra gigas</span>
                <input type="text" class="form-control" name="mobileInternet" id="fee_gigas" value=0 disabled>
            </div>
        </div>
        <div class="form-check form-switch">
            <input class="form-check-input" name="fixedPhone" type="checkbox" role="switch" id="fixedPhone">
            <label class="form-check-label" for="fixedPhone">Fixed phone</label>
        </div>
        <div class="form-check form-switch">
            <input class="form-check-input" name="fixedInternet" type="checkbox" role="switch" id="fixedInternet">
            <label class="form-check-label" for="fixedInternet">Fixed Internet</label>
        </div>
        <br>
        Validity Periods
        <%
            List<PeriodEntity> periods = (List<PeriodEntity>) request.getAttribute("periods");
            if (periods != null) {
                for (PeriodEntity p: periods) {
                    int d = p.getDuration();
        %>
        <div class="form-check form-switch">
            <input class="form-check-input" name="periods" value=<%=d%> type="checkbox" role="switch" id="periods">
            <label class="form-check-label" for="fixedPhone"><%=d%></label>
        </div>
        <%
                }}
        %>
        <br>
        Optional Services
        <%
            List<OptionalServiceEntity> optionalServices = (List<OptionalServiceEntity>) request.getAttribute("optionalServices");
            if (optionalServices != null) {
                for (OptionalServiceEntity os: optionalServices) {
                    String n = os.getName();
        %>
        <div class="form-check form-switch">
            <input class="form-check-input" name="optionalServices" value=<%=n%> type="checkbox" role="switch" id="optional">
            <label class="form-check-label" for="optional"><%=n%></label>
        </div>
        <%
            }}
        %>
        <%String x = (String)request.getAttribute("creationPackageMessage");%>
        <br>${x}
        <input class="btn btn-primary" type="submit" value="Submit">
                </div>
            </div>
        </div>
    </div>

</section>


    </form>
    <br><br>

    <div id="overlay">
        <div class="container h-100">
            <div class="row h-100 justify-content-center align-items-center">
        <div class="card mx-auto my-auto" style="width: 600px;">
            <button type="button" class="btn-close" onclick="overlayOff()"></button>
            <form action="createoptionalservice" method="post">
                <h5 class="card-header">Create an Optional Service</h5>

                <div class="input-group mb-3">
                    <span class="input-group-text" id="optionalServiceName">Name</span>
                    <input type="text" name="name" class="form-control" id="name">
                </div>
                <div class="input-group mb-3">
                    <span class="input-group-text" id="optionalServiceFee">Monthly Fee &euro;</span>
                    <input type="number" name="fee" class="form-control" id="fee">
                </div>

                <input class="btn btn-primary" type="submit" value="Submit">

            </form>
        </div>
            </div>
        </div>
    </div>

</div>
</section>
</body>

<script type="text/javascript">
    function enableDisableTextbox(clickedSwitch) {
        let isChecked = clickedSwitch.checked;
        if(clickedSwitch.id === "mobilePhone") {
            let minutes = document.getElementById("minutes");
            minutes.disabled = !isChecked;
            minutes.value = 0;

            let sms = document.getElementById("sms");
            sms.disabled = !isChecked;
            sms.value = 0;

            let fee_minutes = document.getElementById("fee_minutes");
            fee_minutes.disabled = !isChecked;
            fee_minutes.value = 0;

            let fee_sms = document.getElementById("fee_sms");
            fee_sms.disabled = !isChecked;
            fee_sms.value = 0;
        } else if(clickedSwitch.id === "mobileInternet") {
            let gigas = document.getElementById("gigas");
            gigas.disabled = !isChecked;
            gigas.value = 0;

            let fee_gigas = document.getElementById("fee_gigas");
            fee_gigas.disabled = !isChecked;
            fee_gigas.value = 0;

        }
    }

    function overlayOn() {
        document.getElementById("overlay").style.display = "block";
    }

    function overlayOff() {
        document.getElementById("overlay").style.display = "none";
    }
</script>

</html>
