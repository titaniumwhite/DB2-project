<%@ page import ="java.sql.*" %>
<%@ page import ="java.util.*" %>
<%@ page import ="it.polimi.dbproject.entities.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>TELCO COMPANY</title>
</head>
<body>

<%

    List<PeriodEntity> periods = (List<PeriodEntity>) request.getAttribute("periods");
    List<OptionalServiceEntity> optionalServices = (List<OptionalServiceEntity>) request.getAttribute("optionalServices");
    String selectedPackage = (String) request.getAttribute("selectedPackage");

    UserEntity user = null;
    String username = null;

    if(request.getSession().getAttribute("user")!=null){
        user = (UserEntity) request.getSession().getAttribute("user");
        username = user.getUsername();
    }
%>

<% if(username != null){

    %>
<p text-align="right"> User: ${user.username}</p>
<p text-align="right">Logout</p> // qui ci va il bottone con la ref //
<%
}
else{
%>
<p text-align="right">Login</p>p> // qui ci va il bottone con la ref //
<%
    }
%>

<div>

    <h1>BUY PAGE</h1>
    <br>
    <h2>Choose a Service Package</h2>

    <div>
        <form action="buyServicePage" method="post">
            <br>
            <br>
            
            <br>
            <br>
            <button type="submit" class="button" name="selectServicePackBTN">SELECT</button>
            <br>
            <br>
        </form>

        <form>
            <%
                if(periods != null && optionalServices != null){
            %>

                <h4>Selected Package: <%=selectedPackage%></h4>
                <label for="chosenPeriod">Choose the period:</label>
                <select name="chosenPeriod" id="chosenPeriod">
                    <%
                        for(PeriodEntity chosenPeriod: periods){
                    %>
                    <option value="<%=chosenPeriod.getPeriod_id()%>"<%=chosenPeriod.toString()%></option>
                    <%
                        }
                    %>
                </select>
            <br>
            <br>

            <div>
                <%
                    if(optionalServices.size() != 0){
                %>
                <table class="table">
                    <thead class="theadGrey">
                    <tr>
                        <td>Name Optional Service</td>
                        <td>Monthly Fee:</td>
                    </tr>
                    </thead>

                    <tbody class="tbodyWhite">
                    <%
                        for (OptionalServiceEntity optionalServ: optionalServices){
                    %>
                    <tr>
                        <td><%=optionalServ.getName()%></td>
                        <td><%=optionalServ.getMonthly_fee()%></td>
                    </tr>
                    <%
                        }
                        }
                    %>
                    </tbody>
                </table>
                <br>
                <br>
                <%
                    if(optionalServices.size() != 0){
                %>
                <fieldset>
                    <legend> Choose favourites optional services:</legend>
                    <%
                        for (OptionalServiceEntity optionalServ: optionalServices){
                    %>
                    <input type="checkbox" name="optionalServ"
                    value="<%=optionalServ.getId()%>"><%=optionalServ.getName()%>
                    <br>
                    <%
                        }
                        }
                    %>
                </fieldset>
                <br>
                <br>
                <button type="submit" class="button" name="buttonToConfirm">CONFIRM</button>
                <%
                    }
                %>
            </div>
        </form>
    </div>
</div>

</body>
</html>
