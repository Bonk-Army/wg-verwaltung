<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>WG-Verwaltung</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="assets/styles/login.css">
    <!-- Bootstrap CSS CDN -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css"
          integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
    <!-- jQuery CDN - Slim version (=without AJAX) -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <!-- Popper.JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"
            integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
    <!-- Bootstrap JS -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"
            integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>
</head>
<body>
<div class="header">
    <h2>WG-ORGANISATOR</h2>
</div>
<div class="main">
    <div class="overview">
        <h2>
            <i>OVERVIEW</i>
        </h2>
        <div id="overviewbox">
            <p>Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore
                et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea
                rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum
                dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore
                magna aliquyam erat, sed diam voluptua.
                At vero eos et accusam et justo duo dolores et ea rebum.
                Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.
            </p>
        </div>
    </div>
    <div class="verticalLine"></div>
    <div class="card">
        <div class="login-box">
            <div class="login-snip">
                <input id="tab-1" type="radio" name="tab" class="sign-in" checked>
                <label for="tab-1" class="tab">Login</label>

                <input id="tab-2" type="radio" name="tab" class="sign-up">
                <label for="tab-2" class="tab">Sign Up</label>

                <div class="login-space">
                    <form class="login" action="login" method="POST">
                        <div class="group">
                            <label for="user" class="label">Username</label>
                            <input id="user" type="text" class="input" placeholder="Enter your username"
                                   name="username">
                        </div>
                        <div class="group">
                            <label for="pass" class="label">Password</label>
                            <input id="pass" type="password" class="input" data-type="password"
                                   placeholder="Enter your password" name="password">
                        </div>
                        <div class="group">
                            <input id="check" type="checkbox" class="check" name="keepSignedIn" checked="checked">
                            <label for="check">
                                <span class="icon"></span>
                                Keep me Signed in
                            </label>
                        </div>
                        <input id="isRegister" type="checkbox" hidden="hidden" name="isRegister">
                        <div class="group">
                            <input type="submit" class="button" value="Sign In">
                        </div>
                        <div class="hr"></div>
                        <a href="#">Forgot Password?</a>
                    </form>
                    <form class="sign-up-form" action="login" method="POST">
                        <div class="group">
                            <label for="user" class="label">Username</label>
                            <input id="user" type="text" class="input" placeholder="Create your Username"
                                   name="username">
                        </div>
                        <div class="group">
                            <label for="pass" class="label">Password</label>
                            <input id="pass" type="password" class="input" data-type="password"
                                   placeholder="Create your password" name="password">
                        </div>
                        <div class="group">
                            <label for="pass" class="label">Repeat Password</label>
                            <input id="pass" type="password" class="input" data-type="password"
                                   placeholder="Repeat your password">
                        </div>
                        <div class="group">
                            <label for="email" class="label">Email Address</label>
                            <input id="email" type="text" class="input" placeholder="Enter your email address"
                                   name="email">
                        </div>
                        <input id="isRegister" type="checkbox" hidden="hidden" name="isRegister" checked="checked">
                        <div class="group"><input type="submit" class="button" value="Sign Up"></div>
                        <div class="hr"></div>
                        <div class="foot">
                            <label for="tab-1">Already Member?</label>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="footer">
        <div class="ftlinks">
        </div>
    </div>
</div>
</body>
</html>