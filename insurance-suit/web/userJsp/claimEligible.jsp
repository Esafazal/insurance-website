<%-- 
    Document   : Claim Eligiblity
    Created on : Mar 22, 2019, 12:02:46 AM
    Author     : crazydude
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Claim Eligibility</title>
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
                     font-size: 16px;"><script> document.write(new Date().toLocaleDateString());</script>&nbsp;<a href="<%= response.encodeURL("Logout") %>" class="btn btn-danger square-btn-adjust">Logout</a> </div>
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
                            <h2>Make Claim</h2>
                            <c:if test="${requestScope.error!=null}">
                            <div class="alert alert-info alert-dismissable">
                                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                                <a href="#" class="alert-link">${error}</a>.
                            </div>
                            </c:if>
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
                                    <form role="form" action="ClaimEligible" method="GET">
                                        <p>Eligibility Criteria</p>

                                        <ul>
                                            <li>Membership must be minimum 6 months old.</li>
                                            <li>Claim quota is not exceeded.</li>
                                            <li>No outstanding payments.</li>
                                        </ul> 
                                        
                                         <c:if test="${requestScope.error == null}">
                                        <button type="submit"  class="btn btn-primary">Check Eligibility</button>
                                        </c:if>
                                        
                                        <c:if test="${requestScope.error != null}">
                                        <fieldset disabled="disabled">
                                            <button type="submit" class="btn btn-primary">Check Eligibility</button>
                                        </fieldset></c:if>
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
