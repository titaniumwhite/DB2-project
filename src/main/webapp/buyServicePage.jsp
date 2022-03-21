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

    List<PeriodEntity> periods = (List<PeriodEntity>) request.getAttribute("periods");
    List<OptionalServiceEntity> optionalServices = (List<OptionalServiceEntity>) request.getAttribute("optionalServices");
    AvailableServicePackEntity selectedPackage = (AvailableServicePackEntity) request.getSession().getAttribute("selectedPackage");

    String username = null;

    if(request.getSession().getAttribute("user")!=null){
        user = (UserEntity) request.getSession().getAttribute("user");
        username = user.getUsername();
    }

%>

<section>
    <div class="container d-flex" style="justify-content: center; align-content: center; padding-top: 1.5rem">
        <div class="col-lg-8">
            <div class="card card-margin">
                <div class="card-header no-border">
                    <h5 class="card-title" style="text-align: center">Package to buy: <%=selectedPackage.getName()%></h5>
                </div>

                <div class="card-text">
                    <form action="buyservice" method="post">
                        <p style="font-size: medium; text-align: center; justify-content: center"><b>Choose the period of the subscription</b></p>
                        <div class="row"  style="padding-left: 1.5rem"></div>
                    <% for (PeriodEntity p: periods) {%>
                    <div class="form-check" style="padding-left: 3rem !important;" >
                        <input class="form-check-input" type="radio" name="chosenPeriod" id="chosenPeriod" value="<%=p.getPeriod_id()%>" required>
                        <label class="form-check-label" for="chosenPeriod">
                            <%=p.getDuration()%> months (<%=p.getMonthlyFee()%> &euro;/month)
                        </label>
                    </div>
                    <% } %>
                    </div>
                    <br> <br>
                    <%if (optionalServices.size() != 0) { %>
                    <p style="font-size: medium; text-align: center; justify-content: center">
                        <b>Choose additional optional services to include:</b>
                    </p>

                    <ul class="list-group">
                        <% for (OptionalServiceEntity os: optionalServices) {%>
                        <li class="list-group-item">
                            <input class="form-check-input me-1" name="chosenOptionalServices" id="chosenOptionalServices" type="checkbox" value="<%=os.getId()%>">
                            <%=os.getName()%> (<%=os.getMonthly_fee()%> &euro;/month)
                        </li>
                        <% } %>
                    <%} else {%>
                    </ul
                    <p style="font-size: medium; text-align: center; justify-content: center">
                        <b>For this package you can not include any other optional services.</b>
                    </p>
                <% } %>
                    <br><br>
                <p style="font-size: medium; text-align: center; justify-content: center"><b>Choose the start date</b></p>
                <div class="row" style="text-align: center">
                <div class="col" style="width: 25%; text-align: center; justify-content: center; align-items: center">
                        <input type="date" name="chosenStartDate" id="chosenStartDate" min="2022-03-24" required>
                </div>
                </div>
                    <br><br>
                <div class="row" style="text-align: center">
                    <div class="col" style="width: 25%; text-align: center; justify-content: center; align-items: center">
                        <input class="btn btn-outline-primary" type="submit" value="Confirm"/>
                    </div>
                </div>


                <br>
                    </form>



                </div>
            </div>

        </div>


        <br> <br>





    </div>

</section>
</body>
</html>

<script>
    src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"

    $(document).ready(function () {
        $('.it-date-datepicker').datepicker({
            inputFormat: ['dd/MM/yyyy'],
            outputFormat: 'dd/MM/yyyy',
        })
    })

</script>
