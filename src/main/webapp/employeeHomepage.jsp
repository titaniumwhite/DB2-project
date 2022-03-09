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
<body>
<div class="container-fluid">
    <form action="createPackage" method="post">
        Package Name <input type="text" name="name"/><br/><br/>
        Services
        <div class="form-check form-switch">
            <input class="form-check-input" type="checkbox" role="switch" id="mobilePhone" onclick="enableDisableTextbox(this)">
            <label class="form-check-label" for="mobilePhone">Mobile Phone</label>
            <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon">Minutes</span>
                <input type="text" class="form-control" id="minutes" value=0 disabled>
            </div>
            <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon1">SMS</span>
                <input type="text" class="form-control" id="sms" value=0 disabled>
            </div>
            <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon2">Fee for extra minutes</span>
                <input type="text" class="form-control" id="fee_minutes" value=0 disabled>
            </div>
            <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon3">Fee for extra SMS</span>
                <input type="text" class="form-control" id="fee_sms" value=0 disabled>
            </div>
        </div>
        <div class="form-check form-switch">
            <input class="form-check-input" type="checkbox" role="switch" id="mobileInternet" onclick="enableDisableTextbox(this)">
            <label class="form-check-label" for="mobileInternet">Mobile Internet</label>
            <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon4">Gigas</span>
                <input type="text" class="form-control" id="gigas" value=0 disabled>
            </div>
            <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon5">Fee for extra gigas</span>
                <input type="text" class="form-control" id="fee_gigas" value=0 disabled>
            </div>
        </div>
        <div class="form-check form-switch">
            <input class="form-check-input" type="checkbox" role="switch" id="fixedPhone">
            <label class="form-check-label" for="fixedPhone">Fixed phone</label>
        </div>
        <div class="form-check form-switch">
            <input class="form-check-input" type="checkbox" role="switch" id="fixedInternet">
            <label class="form-check-label" for="fixedInternet">Fixed Internet</label>
        </div>
        <br>
        Validity Periods
        <%
            List<PeriodEntity> periods = (List<PeriodEntity>) request.getAttribute("periods");
            if (periods != null) {
                for (PeriodEntity p: periods) {
        %>
        <div class="form-check form-switch">
            <input class="form-check-input" type="checkbox" role="switch" id="periods">
            <label class="form-check-label" for="fixedPhone"><%=p.getDuration()%></label>
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
        %>
        <div class="form-check form-switch">
            <input class="form-check-input" type="checkbox" role="switch" id="optional">
            <label class="form-check-label" for="fixedPhone"><%=os.getName()%></label>
        </div>
        <%
            }}
        %>

        <input class="btn btn-primary" type="submit" value="Submit">



    </form>
    <br><br>

    <div id="overlay">
        <div class="card mx-auto" style="width: 600px;">
            <button type="button" class="btn-close" onclick="overlayOff()"></button>
            <form action="createOptionalService" method="post">
                <h5 class="card-header">Create an Optional Service</h5>

                <div class="input-group mb-3">
                    <span class="input-group-text" id="optionalServiceName">Name</span>
                    <input type="text" class="form-control" id="name">
                </div>
                <div class="input-group mb-3">
                    <span class="input-group-text" id="optionalServiceFee">Monthly Fee &euro;</span>
                    <input type="number" class="form-control" id="fee">
                </div>
                <input class="btn btn-primary" type="submit" value="Submit">

            </form>
        </div>
    </div>

    <div>
        <button type="button" class="btn btn-primary" onclick="overlayOn()">Create a new Optional Service</button>
    </div>
</div>
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
        console.log("Sto qua")
        document.getElementById("overlay").style.display = "block";
    }

    function overlayOff() {
        document.getElementById("overlay").style.display = "none";
    }
</script>

</html>
