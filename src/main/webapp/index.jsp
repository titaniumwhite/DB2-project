<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <style><%@include file="style.css"%></style>
</head>
<body>
<section class="vh-100" style="background-color: #508bfc;">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                <div class="card shadow-2-strong" style="border-radius: 1rem;">
                    <div class="card-body p-33 text-center">

                        <h3 class="mb-3">Sign in</h3>

                        ${registrationMessage}

                        <br>
                        <br>

                        <div class="form-outline mb-4">
                            <input type="text" name="username" id="typeUsername" class="form-control form-control-lg" />
                            <label class="form-label" for="typeUsername">Username</label>

                            <input type="password" id="typePassword" name="password" class="form-control form-control-lg"  />
                            <label class="form-label" for="typePassword">Password</label>

                        </div>

                        <div class="form-outline mb-4">
                        </div>


                        <button class="btn btn-primary btn-lg btn-block" type="submit" value="login">Login</button>
                        <br>
                        <hr/>

                        If you don't have an account yet
                        <br>
                        <br>

                        <button class="btn btn-outline-primary btn-lg btn-block" type="submit" value="registration"  onclick="overlayOn()">Sign up</button>
                        <br>
                        <br>

                        <a href="homepage" class="btn btn-outline-primary btn-lg btn-block" role="button" >Access as a guest</a>

                        <div id="overlay">
                            <div class="col d-flex justify-content-center" style="padding-top: 55px;">
                                <div class="card shadow-2-strong" style="border-radius: 1rem;">
                                    <div class="card-body p-4 text-center">
                                <h5>Sign up</h5>
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

                                    <input class="btn btn-outline-danger" type="button" onclick="overlayOff()" value="Cancel"></button>
                                    <input class="btn btn-outline-primary" type="submit" value="Submit">

                                </form>
                            </div>
                        </div>
                            </div></div></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>

<script>
    function overlayOn() {
        document.getElementById("overlay").style.display = "block";
    }

    function overlayOff() {
        document.getElementById("overlay").style.display = "none";
    }
</script>
</script>

</html>