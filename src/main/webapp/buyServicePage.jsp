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
    AvailableServicePackEntity selectedPackage = (AvailableServicePackEntity) request.getSession().getAttribute("selectedPackage");

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
                    <h5 class="card-title">Package to buy: <%=selectedPackage.getName()%></h5>
                </div>

                <div class="card-text">
                    <form action="buyservice" method="post">
                    <h2>Choose the period of the subscription</h2>
                    <% for (PeriodEntity p: periods) {%>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="periodRadio" id="periodRadio">>
                        <label class="form-check-label" for="periodRadio">
                            <%=p.getDuration()%> months (<%=p.getMonthlyFee()%> &euro;/month)
                        </label>
                    </div>
                    <% } %>

                    <br> <br>

                    Choose the optional services

                    <ul class="list-group">
                        <% for (OptionalServiceEntity os: optionalServices) {%>
                        <li class="list-group-item">
                            <input class="form-check-input me-1" type="checkbox" value="">
                            <%=os.getName()%> (<%=os.getMonthly_fee()%> &euro;/month)
                        </li>
                        <% } %>

                    </ul

                    <br><br>


                    <label>Choose the start date:
                        <input type="date" name="startDate" required>
                    </label>

                    <br><br>

                    <input class="btn btn-outline-primary" type="submit" value="Confirm"/>

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
