<%-- 
    Document   : success
    Created on : Mar 21, 2019, 6:32:37 PM
    Author     : crazydude
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
   <head>
      <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <meta name="description" content="">
      <meta name="author" content="">
      <title>Successful Registration</title>
      <!-- Css -->
      <link href="../css/bootstrap.css" rel="stylesheet">
      <link href="../css/style.css" rel="stylesheet">
   </head>
   <body>
      <nav class="navbar navbar-default color-fill navbar-fixed-top">
         <div class="col-md-12">
            <div class="nav">
               <button class="btn-nav">
               <span class="icon-bar top"></span>
               <span class="icon-bar middle"></span>
               <span class="icon-bar bottom"></span>
               </button>
            </div>
            <a class="navbar-brand" href="index.html">
<!--            <img class="logo" src="img/logo.png" alt="logo">-->
            </a>
            <div class="nav-content hideNav hidden">
               <ul class="nav-list vcenter">
                   <li class="nav-item"><a class="item-anchor" href="../adminJsp/adminLogin.jsp">Admin Sign In</a></li>
                   <li class="nav-item"><a class="item-anchor" href="../userJsp/memberLogin.jsp">Member Sign In</a></li>
                   <li class="nav-item"><a class="item-anchor" href="../userJsp/registration.jsp">Register</a></li>
               </ul>
            </div>
         </div>
      </nav>
      <section>
         <div class="container">
            <div class="row">
               <div class="col-md-12">
                  <h1>
                     Registration Successful.<br>
                     We've Emailed your Username and Password.
                  </h1>
                   <h4>${email}</h4>
               </div>
            </div>
            </div>
         </div>
         </div>
      </section>
      <!-- script -->
      <script src="../css/js/jquery.js"></script>
      <script src="../css/js/bootstrap.min.js"></script>
      <script src="../css/js/modernizr.js"></script>
      <script src="../css/js/script.js"></script>
   </body>
</html>
