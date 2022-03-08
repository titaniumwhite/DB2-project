<%@ page import="java.sql.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="it.polimi.dbproject.entities.*" %>
<%@ page import="java.util.List" %><!doctype html>

<html lang="en">
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
    <% List<AvailableServicePackEntity> servicePackages = (List<AvailableServicePackEntity>) request.getAttribute("availableServicePackages");
    %>

    <h2>List of Service Package available</h2>
    <div class="card-deck">
        <div class="row">
    <%  if (servicePackages != null) {
            for (AvailableServicePackEntity sp: servicePackages) {%>
            <div class="col-6 col-md-4">
            <div class="card">
                    <h5 class="card-title"><%=sp.getName()%></h5>
                    <p class="card-text">
                        <ul>
                        <% for (ServiceEntity s: sp.getServices()) {%>
                        <li><%=s.getType()%></li>

                        <% } %>
                        </ul>
                    </p>
                    <a href="#" class="btn btn-primary">Go somewhere</a>
            </div>
            </div>
        <% }} %>
        </div>
    </div>




</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>


</body>
</html>