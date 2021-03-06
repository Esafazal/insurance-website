<%-- 
    Document   : Make Claim
    Created on : Mar 24, 2019, 10:30:23 PM
    Author     : Pathum
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Make Claim</title>
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
                            <a  href="<%= response.encodeURL("UserHome")%>"> Home</a>
                        </li>
                        <li>
                            <a  href="<%= response.encodeURL("MemberDetails")%>"> Profile</a>
                        </li>
                        <li>
                            <a  class="active-menu" href=""> Make Claim</a>
                        </li>
                        <li>
                            <a  href="<%= response.encodeURL("MakePayment")%>"> Make Payment</a>
                        </li>
                        <li>
                            <a href="<%= response.encodeURL("ClaimStatus")%>"> Claim Status</a>
                        </li>   



                    </ul>

                </div>

            </nav>  
            <!-- /. NAV SIDE  -->
            <div id="page-wrapper" >
                <div id="page-inner">
                    <div class="row">
                        <div class="col-md-12">
                            <h2>Make Claim</h2>

                        </div>
                    </div>
                    <!-- /. ROW  -->
                    <hr />
                    <div class="row">
                <div class="col-md-12">
                    <!-- Form Elements -->
                    <div class="panel panel-default">
                        
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-6">
                                    <form role="form" action="MemberMakeClaim" method="POST">
                                        <div class="form-group">
                                            <label>Select Vehicle</label> 
                                            <select class="form-control" name="vehiclenumber" required>
                                                <option>Select Vehicle</option>
                                                <option>${vehicleNO}</option>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label>Date of Incident</label> 
                                            <input type=date class="form-control" placeholder="Enter Date of Incident" name="incidentdate" required/>
                                        </div>
                                        <div class="form-group">
                                            <label>Claim Description</label> 
                                            <textarea type="text" class="form-control" rows="5" placeholder="Enter Claim Description" name="claimdescription" required></textarea>
                                        </div>
                                        <div class="form-group">
                                            <label>Place of Quotation</label> 
                                            <input type="text" class="form-control" placeholder="Enter Place of Quotation" name="quoationplace" required/>
                                        </div>
                                        <div class="form-group">
                                            <label>Claim Amount</label> 
                                            <input type="text" class="form-control" placeholder="Enter Claim Amount" name="claimamount" required/>
                                        </div>
                                        
                                        <button type="submit"  class="btn btn-primary">Request Claim</button>
                                        </form>
                                </div>
                                </div>
                            </div>
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
