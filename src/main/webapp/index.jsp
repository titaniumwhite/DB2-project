<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<form action="login" method="post">
    Email:<input type="text" name="username"/><br/><br/>
    Password:<input type="password" name="password"/><br/><br/>
    <input type="submit" value="login"/>"
</form>
<div class="registration">
    <div class="form">
        <h2>Registration</h2>
        <form action="registration" method="post">
            <input type="text" name="username" placeholder="Username" required/>
            <input type="text" name="email" placeholder="Email" required/>
            <input type="text" name="first_name" placeholder="First Name" required/>
            <input type="text" name="last_name" placeholder="Last Name" required/>
            <input type="password" name="password" placeholder="password" required/>
            <br>${registrationMessage}
            <button type="submit">sign up</button>
        </form>
    </div>
</div>
</h1>
<br/>
</body>
</html>