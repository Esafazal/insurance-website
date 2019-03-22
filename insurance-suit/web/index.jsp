<%-- 
    Document   : index
    Created on : Mar 21, 2019, 6:10:54 PM
    Author     : crazydude
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
   <head>
      <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <meta name="description" content="Java EE Group Assignment">
      <meta name="author" content="Crazydude">
      <title>Driver's Association Colombo</title>
      <!-- Css -->
      <link href="css/bootstrap.css" rel="stylesheet">
      <link href="css/style.css" rel="stylesheet">
   </head>
   <body>
      <nav class="navbar navbar-default navbar-fixed-top">
         <div class="col-md-12">
            <div class="nav">
               <button class="btn-nav">
               <span class="icon-bar inverted top"></span>
               <span class="icon-bar inverted middle"></span>
               <span class="icon-bar inverted bottom"></span>
               </button>
            </div>
            <a class="navbar-brand" href="index.html">
            <img class="logo" src="css/img/logo.png" alt="logo">
            </a>
            <div class="nav-content hideNav hidden">
               <ul class="nav-list vcenter">
                  <li class="nav-item"><a class="item-anchor" href="adminJsp/login.jsp">Admin Sign In</a></li>
                  <li class="nav-item"><a class="item-anchor" href="userJsp/login.jsp">Member Sign In</a></li>
                  <li class="nav-item"><a class="item-anchor" href="userJsp/registration.jsp">Register</a></li>
               </ul>
            </div>
         </div>
      </nav>
      <!-- Header -->
      <div class="span12">
         <div class="col-md-6 no-gutter text-center fill">
            <h2 class="vcenter">Welcome to Driver's Association Colombo </h2>
         </div>
         <div class="col-md-6 no-gutter text-center">
            <div id="header" data-speed="2" data-type="background">
               <div id="headslide" class="carousel slide" data-ride="carousel">
                  <div class="carousel-inner" role="listbox">
                     <div class="item active">
                        <img src="css/img/4.jpg" alt="Slide">
                     </div>
                     <div class="item">
                        <img src="css/img/3.jpg" alt="Slide">
                     </div>
                     <div class="item">
                        <img src="css/img/1.jpg" alt="Slide">
                     </div>
                     <div class="item">
                        <img src="css/img/2.jpg" alt="Slide">
                     </div>
                  </div>
               </div>
            </div>
         </div>
      </div>
      <div style="clear:both;"></div>
      <!-- script -->
      <script src="css/js/jquery.js"></script>
      <script src="css/js/bootstrap.min.js"></script>
      <script src="css/js/menu-color.js"></script>
      <script src="css/js/modernizr.js"></script>
      <script src="css/js/script.js"></script>
   </body>
</html>
