<%-- 
    Document   : login
    Created on : Mar 21, 2019, 6:17:19 PM
    Author     : crazydude
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Log In</title>
        <link href="../css/style.css" rel="stylesheet">
    </head>
    <body>
        <form action="AdminLogin" method="POST">
            <div class="imgcontainer">
                <img src="../css/img/login.png" alt="Avatar" class="avatar">
            </div>
            <c:if test="${requestScope.error!=null}">
                <div class="alert alert-info alert-dismissable">
                    <!--<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>-->
                    <a href="#" class="alert-link">${error}</a>.
                </div>
            </c:if>

            <div class="container">
                <label for="uname"><b>Username</b></label>
                <input type="text" placeholder="Enter Username" name="username" required>

                <label for="psw"><b>Password</b></label>
                <input type="password" placeholder="Enter Password" name="password" required>
                <button type="submit">Login</button>
                <!--<button type="submit" onclick="window.location.href='dashboard.jsp'">Login</button>-->
                <label>
                    <input type="checkbox" checked="checked" name="remember"> Remember me
                </label>
            </div>
            <div class="container" style="background-color:#f1f1f1">
                <button type="button" class="cancelbtn" onclick="window.location.href='../index.jsp'">Cancel</button>
                <span class="psw">Forgot <a href="#">password?</a></span>
            </div>
        </form>
    </body>
</html>

<!--            <div class="container" style="background-color:#f1f1f1">
                <span class="psw">Forgot <a href="#">password?</a></span>
            </div>-->
        </form>
    </body>
</html>
