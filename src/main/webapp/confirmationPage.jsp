<%@ page import="it.polimi.dbproject.entities.ServicePackEntity" %>
<%@ page import="it.polimi.dbproject.entities.UserEntity" %><%--
  Created by IntelliJ IDEA.
  User: simbo
  Date: 10/03/2022
  Time: 16:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CONFIRMATION PAGE</title>
</head>

<body>

<%
    ServicePackEntity servicePack = (ServicePackEntity) request.getAttribute("servicePack");

    UserEntity user = null;
    String username = null;

    if(request.getSession().getAttribute("user") != null){
        user = (UserEntity) request.getSession().getAttribute("user");
        username = user.getUsername();
    }

    if(username != null){


%>
        <p text-align="right"> User: ${user.username}</p>
        // qui ci va il tasto per il logout
        <%
        }
        else {
        %>

            <p text-align="right"><a href="${pageContext.request.contextPath}/login">LOGIN</a> </p>
        <%
            }
        %>

        <div>
            <h1> ORDER DETAILS </h1>
            <br>
            <h3> Summary Specification: </h3>

            <div>
                <table>
                    <tr>
                        <td> Selected Package: </td>
                        <td> Service Type: </td>
                        <%
                            if(servicePack.getSelectedOptionalServices() != null && servicePack.getSelectedOptionalServices().size()!=0){
                        %>
                        <td>Optional Services</td>
                        <%
                            }
                        %>
                        <td>Starting Date: </td>
                        <td>Ending Date: </td>
                        <td>Total Cost: </td>
                    </tr>
                    <tr>
                        <td><%=servicePack.getAvailablePackages().getName()%></td>
                        <td><%=servicePack.getAvailablePackages().getServices()%></td>
                        <%
                            if(servicePack.getSelectedOptionalServices()!=null && servicePack.getSelectedOptionalServices().size()!=0){
                        %>
                        <td><%=servicePack.getSelectedOptionalServices()%></td>
                        <%
                            }
                        %>
                        <td><%=servicePack.getStartDate()%></td>
                        <td><%=servicePack.getEndDate()%></td>
                        <td><%=servicePack.getTotalCost()%></td>
                    </tr>
                </table>
            </div>
            <br>
            <br>
        </div>
<div>
    <%
        if(username!=null){
    %>
    <div>
       <form action="confirmationPage" method="post">
           <button name="result" value="placeable" type="submit">BUY SERVICE</button>
           <button name="result" value="notPlaceable" type="submit">BUY SERVICE</button>
       </form>
    </div>
    <br>
    <br>
    <form action="confirmationPage" method="post">
        <button name="result" value="random" type="submit">BUY SERVICE (Da vedere a che serve)</button>
    </form>

    <%
        }
        else {
    %>
    <form action="login" method="get">
        <button type="submit">LOGIN/REGISTER</button>
    </form>
    <%
        }
    %>
</div>
</body>
</html>
