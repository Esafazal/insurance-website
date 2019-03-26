<%-- 
    Document   : registerVehicle
    Created on : Mar 24, 2019, 2:45:27 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Free Bootstrap Admin Template : Binary Admin</title>
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
                    <a class="navbar-brand" href="#">Member</a> 
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
                            <a href="../userJsp/home.jsp"> Home</a>
                        </li>
                        <li>
                            <a class="active-menu" href="../userJsp/userProfile.jsp"> Profile</a>
                        </li>
                        <li>
                            <a href="../userJsp/makeClaim.jsp"> Make Claim</a>
                        </li>
                        <li>
                            <a href="../userJsp/makePayment.jsp"> Make Payment</a>
                        </li>
                        <li>
                            <a href="../userJsp/claimStatus.jsp"> Claim Status</a>
                        </li>




                    </ul>

                </div>

            </nav>  
            <!-- /. NAV SIDE  -->
            <div id="page-wrapper" >
                <div id="page-inner">
                    <div class="row">
                        <div class="col-md-12">
                            <h2>Profile</h2>

                        </div>
                    </div>
                    <!-- /. ROW  -->
                    <hr />
                    <div class="row">
                        <div class="col-md-6 col-sm-6">
                            <div class="panel panel-default" style="width: 207%">

                                <div class="panel-body">
                                    <ul class="nav nav-tabs">
                                        <li class="active"><a href="#home" data-toggle="tab">Details</a>
                                        </li>
                                        <li class=""><a href="#profile" data-toggle="tab">Edit</a>
                                        </li>
                                        <li class=""><a href="#messages" data-toggle="tab">Password</a>
                                        </li>
                                        <li class=""><a href="#settings" data-toggle="tab">History</a>
                                        </li>
                                    </ul>

                                    <div class="tab-content">
                                        <div class="tab-pane fade active in" id="home">
                                            <h4><b>Personal Details</b></h4>
                                            <p><strong>Name:</strong>&nbsp; ${memberList.first_name}</p>
                                            <p><strong>Username:</strong>&nbsp; ${memberList.username}</p>
                                            <p><strong>Address:</strong>&nbsp; ${memberList.address}</p>
                                            <p><strong>Date of Birth:</strong> ${memberList.dob}</p>
                                            <p><strong>Date of Registration:</strong> ${memberList.date_of_registration}</p>
                                            <p><strong>NIC:</strong>&nbsp; ${memberList.nic}</p>
                                            <p><strong>Email:</strong>&nbsp; ${memberList.email}</p>
                                            <p><strong>Phone No:</strong>&nbsp; ${memberList.phone_no}</p>
                                        </div>
                                        <div class="tab-pane fade" id="profile">
                                            <h4>Edit Details</h4>

                                            <!-- Form Elements -->

                                            <div class="panel-body">
                                                <div class="row">
                                                    <div class="col-md-6">
                                                        <form role="form">

                                                            <div class="form-group">
                                                                <label>Username</label> 
                                                                <input class="form-control" />
                                                            </div>
                                                            <div class="form-group">
                                                                <label>Address</label> 
                                                                <input class="form-control" />
                                                            </div>
                                                            <div class="form-group">
                                                                <label>Email</label> 
                                                                <input class="form-control" />
                                                            </div>
                                                            <div class="form-group">
                                                                <label>Phone</label> 
                                                                <input class="form-control" />
                                                            </div>
                                                            <button type="submit" class="btn btn-primary">Save Changes</button>

                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="tab-pane fade" id="messages">
                                            <h4>Change Password</h4>
                                            <!-- Form Elements -->

                                            <div class="panel-body">
                                                <div class="row">
                                                    <div class="col-md-6">
                                                        <form role="form">

                                                            <div class="form-group">
                                                                <label>Current Password</label> 
                                                                <input class="form-control" />
                                                            </div>
                                                            <div class="form-group">
                                                                <label>New Password</label> 
                                                                <input class="form-control" />
                                                            </div>

                                                            <button type="submit" class="btn btn-primary">Save Changes</button>

                                                    </div>
                                                </div>
                                            </div>
                                        </div>


                                        <div class="tab-pane fade" id="settings">
                                            <h4>History</h4>
                                        </div>
                                    </div>

                                </div>

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

