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
    <ul class="navbar-nav me-auto mb-2 mb-lg-0" style="display: inline-flex; width: 2%">
        <li class="nav-item" style="color: white; padding: 0.5rem; text-align: right !important; display: flex">${username}</li>
    </ul>

</nav>

<section>
    <div class="container d-flex" style="justify-content: center; align-content: center; padding-top: 1.5rem">
        <div class="col-lg-8">
            <div class="card card-margin">
                <div class="card-header no-border">
                    <h5 class="card-title" style="text-align: center">Sales Report</h5>
                </div>
                <div class="card-text"></div>
            </div>
        </div>
    </div>

</section>
</body>


</html>
