<%-- 
    Document   : searchMember
    Created on : Mar 22, 2019, 12:04:21 AM
    Author     : crazydude
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Driver's Association</title>
        <!-- BOOTSTRAP STYLES-->
        <link href="../css/assets/css/bootstrap.css" rel="stylesheet" />
        <!-- FONTAWESOME STYLES-->
        <link href="../css/assets/css/font-awesome.css" rel="stylesheet" />
        <!-- CUSTOM STYLES-->
        <link href="../css/assets/css/custom.css" rel="stylesheet" />
        <!-- GOOGLE FONTS-->
        <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
    </head>
    <body>
        <div id="wrapper">
            <nav class="navbar navbar-default navbar-cls-top " role="navigation" style="margin-bottom: 0">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="index.html">Admin</a> 
                </div>
                <div style="color: white;
                     padding: 15px 50px 5px 50px;
                     float: right;
                     font-size: 16px;"><script> document.write(new Date().toLocaleDateString());</script>&nbsp;<a href="#" class="btn btn-danger square-btn-adjust" onclick="window.location.href = '../index.jsp'">Logout</a> </div>
            </nav>   
            <!-- /. NAV TOP  -->
            <nav class="navbar-default navbar-side" role="navigation">
                <div class="sidebar-collapse">
                    <ul class="nav" id="main-menu">
                        <li class="text-center">
                            <img src="../css/assets/img/find_user.png" class="user-image img-responsive"/>
                        </li>


                        <li>
                            <a  href="../adminJsp/dashboard.jsp"> Dashboard</a>
                        </li>
                        <li>
                            <a  href="../adminJsp/paymentStatus.jsp"> Payment Status</a>
                        </li>
                        <li>
                            <a   href="../adminJsp/pendingApprovals.jsp"> Pending Approvals</a>
                        </li>
                        <li>
                            <a  href="../adminJsp/reviewClaims.jsp">Review Claims</a>
                        </li>
                        <li>
                            <a class="active-menu" href="../adminJsp/searchMember.jsp"> Search Member</a>
                        </li>




                    </ul>

                </div>

            </nav>  
            <!-- /. NAV SIDE  -->
            <div id="page-wrapper" >
                <div id="page-inner">
                    <div class="row">
                        <div class="col-md-12">
                            <h2>Search Member</h2>

                        </div>
                    </div>
                    <!-- /. ROW  -->
                    <hr />
                    <div class="form-group input-group">
                        <input type="text" class="form-control">
                            <span class="input-group-btn">
                                <button onclick="window.location.href='#'" class="btn btn-default" type="button"><i class="fa fa-search"></i>
                                </button>
                            </span>

                                </div>
                                <!-- /. PAGE INNER  -->
                                </div>
                                <!-- /. PAGE WRAPPER  -->
                                </div>
                                <!-- /. WRAPPER  -->
                                <!-- SCRIPTS -AT THE BOTOM TO REDUCE THE LOAD TIME-->
                                <!-- JQUERY SCRIPTS -->
                                <script src="../css/assets/js/jquery-1.10.2.js"></script>
                                <!-- BOOTSTRAP SCRIPTS -->
                                <script src="../css/assets/js/bootstrap.min.js"></script>
                                <!-- METISMENU SCRIPTS -->
                                <script src="../css/assets/js/jquery.metisMenu.js"></script>
                                <!-- CUSTOM SCRIPTS -->
                                <script src="../css/assets/js/custom.js"></script>


                                </body>
                                </html>