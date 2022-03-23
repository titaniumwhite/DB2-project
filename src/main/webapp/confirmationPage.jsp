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
        ServicePackEntity servicePack = (ServicePackEntity) session.getAttribute("servicePack");
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

<section>
    <div class="container d-flex" style="justify-content: center; align-content: center; padding-top: 1.5rem">
        <div class="col-lg-8">
            <div class="card card-margin">
                <div class="card-header no-border">
                    <h5 class="card-title" style="text-align: center"><b>Package to buy: <%=servicePack.getAvailablePackage().getName()%></b></h5>


<div class="card-text">
    <form action="buyservice" method="post">
        <p style="font-size: large; text-align: center; justify-content: center"><b>Chosen period of subscription: </b></p>
        <div class="row"  style="justify-content: center">
        <div class="form-check">
            <p style="font-size: medium; text-align: center; justify-content: center"><%=servicePack.getChosenPeriod().getDuration()%> months</p>
        </div>
        </div>
        <p style="font-size: medium; text-align: center; justify-content: center"><b>Start date of subscription: </b></p>
        <div class="row"  style="justify-content: center">
        <div class="form-check">
            <p style="font-size: medium; text-align: center; justify-content: center"><%=servicePack.getStartDate()%></p>
        </div>
        </div>
        <p style="font-size: medium; text-align: center; justify-content: center"><b>End date of subscription: </b></p>
        <div class="row"  style="justify-content: center">
            <div class="form-check">
                <p style="font-size: medium; text-align: center; justify-content: center"><%=servicePack.getEndDate()%></p>
            </div>
        </div>

        <% if (servicePack.getSelectedOptionalServices().size() > 0) {%>
        <p style="font-size: medium; text-align: center; justify-content: center"><b>Additional optional services chosen:</b></p>
        <ul class="list-group">
            <% for (OptionalServiceEntity os: servicePack.getSelectedOptionalServices()) {%>
            <li class="list-group-item">
                <p style="font-size: medium; text-align: center; justify-content: center" name="chosenOptionalServices" id="chosenOptionalServices" ><%=os.getName()%> (<%=os.getMonthly_fee()%> &euro;/month)</p>
            </li>
            <br>
            <% } %>
        </ul
        <br>
        <p style="font-size: medium; text-align: center; justify-content: center"><b>Total cost for the duration of the subscription: </b></p>
        <div class="row"  style="justify-content: center">
            <div class="form-check">
                <p style="font-size: medium; text-align: center; justify-content: center"><%=servicePack.getTotalCost()%>&euro;</p>
            </div>
        </div>



        <%}%>
        </form>
            <div class="row" style="text-align: center">

                <% if(user != null){ %>
                <div class="col" style="width: 25%; text-align: center; justify-content: center; align-items: center">
                    <button class="btn btn-primary" type="submit" onclick="actScheduleOverlayOn()"> BUY (ACCEPTANCE) </button>
                </div>
                        <div style="justify-content: center; padding-top: 1rem">
                        </div>
                    <form action="confirmationpage" method="post">
                        <button class="btn btn-danger" name="confirm" value="reject" type="submit"> BUY (REJECT) </button>
                    </form>
                    <% } else { %>

                        <button class="btn btn-primary" type="submit" onclick="overlayOn()" > LOGIN/REGISTER </button>

                    <% } %>
            </div>
        </div>
                </div>


</div>
    </div>
</div>

    <div id="overlay2" style=" position: absolute; height: auto;">
        <div class="container" style="height: auto">
            <div class="row h-100 justify-content-center align-items-center">
                <div class="card mx-auto my-auto" style="height: auto; width: 600px;">
                    <div class="card-body p-33 text-center">
                        <h3 class="mb-3"><i>Activation Schedule</i></h3>
                        <h2 class="mb-2"><b>PAYMENT HAS BEEN CONFIRMED</b></h2>
                        <p style="font-size: large; text-align: center; justify-content: center"><i>The following package will be activated the: </i><b><%=servicePack.getStartDate()%>:</b></p>
                        <div class="row"  style="justify-content: center">
                                <p style="font-size: medium; padding: 0"><%=servicePack.getAvailablePackage().getName()%></p>
                        </div>
                        <div class="row"  style="justify-content: center">
                            <% if (servicePack.getSelectedOptionalServices().size() > 0) {%>
                            <p style="font-size: large; text-align: center; justify-content: center"><i>The same day the following optional services will be activated:</i></p>
                            <ul class="list-group">
                                <% for (OptionalServiceEntity os: servicePack.getSelectedOptionalServices()) {%>

                                    <p style="font-size: medium; text-align: center; justify-content: center" name="chosenOptionalServices" ><%=os.getName()%></p>

                                <% }} %>
                            </ul
                        </div>
                        <br>
                        <form action="confirmationpage" method="post">
                            <button type="submit" class="btn btn-outline-primary" name="confirm" value="confirm" onclick="actScheduleOverlayOff()">Return to homepage</button>
                        </form>

                    </div>
                </div>
            </div>
        </div>
    </div>


</section>




<div id="overlay" style=" position: absolute; height: auto;">
    <div class="container" style="height: auto">
        <div class="row h-100 justify-content-center align-items-center">
            <div class="card mx-auto my-auto" style="height: auto; width: 600px;">
                <div style="text-align: left">
                    <button type="button" class="btn btn-outline-danger" onclick="overlayOff()">x</button>
                </div>
                <div class="card-body p-33 text-center">

                    <h3 class="mb-3">Sign in</h3>

                    ${registrationMessage}
                    ${loginMessage}

                    <br>
                    <br>

                    <form action="login" method="post">

                        <div class="form-outline mb-4">
                            <input type="text" name="username" id="typeUsername" class="form-control form-control-lg" />
                            <label class="form-label" for="typeUsername">Username</label>

                            <input type="password" id="typePassword" name="password" class="form-control form-control-lg"  />
                            <label class="form-label" for="typePassword">Password</label>

                        </div>

                        <div class="form-outline mb-4"></div>


                        <button class="btn btn-primary btn-lg btn-block" type="submit" name="guest" value="guest">Login</button>
                        <br>
                        <hr/>
                    </form>

                    If you don't have an account yet
                    <br>
                    <br>

                    <form action="registration" method="post">

                        <div class="input-group mb-3">
                            <span class="input-group-text" id="username">Username</span>
                            <input type="text" name="username" class="form-control"  placeholder="Username" required/>
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-text" id="email">Email</span>
                            <input type="email" name="email" class="form-control"  placeholder="Email" required/>
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-text" id="first_name">First name</span>
                            <input type="text" name="first_name" class="form-control"  placeholder="First Name" required/>
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-text" id="last_name">Last name</span>
                            <input type="text" name="last_name" class="form-control"  placeholder="Last Name" required/>
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-text" id="password">Password</span>
                            <input type="password" name="password" class="form-control"  placeholder="Password" required/>
                        </div>

                        <button class="btn btn-primary btn-lg btn-block" type="submit" name="guest" value="guest">Submit</button>

                    </form>
                </div>
            </div>
        </div>
    </div>
</div>


</body>
<script>
    function overlayOn() {
        console.log(document.getElementById("overlay"))
        document.getElementById("overlay").style.display = "block";
        console.log(document.getElementById("overlay").style.display)
    }

    function overlayOff() {
        document.getElementById("overlay").style.display = "none";
    }

    function actScheduleOverlayOn() {
        document.getElementById("overlay2").style.display = "block";
    }

    function actScheduleOverlayOff() {
        document.getElementById("overlay2").style.display = "none";
    }
</script>

</html>
