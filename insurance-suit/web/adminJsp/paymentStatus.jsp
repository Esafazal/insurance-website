<%-- 
    Document   : paymentStatus
    Created on : Mar 22, 2019, 12:04:54 AM
    Author     : crazydude
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
                            <a  href="<%= response.encodeURL("SuspendMember")%>"> Dashboard</a>
                        </li>
                        <li>
                            <a class="active-menu" href=""> Payment Status</a>
                        </li>
                        <li>
                            <a   href="<%= response.encodeURL("PendingApprovals")%>"> Pending Approvals</a>
                        </li>
                        <li>
                            <a  href="<%= response.encodeURL("ReviewClaim")%>">Review Claims</a>
                        </li>
                         <li>
                            <a href="<%= response.encodeURL("ShowMembers")%>"> Search Member</a>
                        </li>
                        <li>
                            <a href="../adminJsp/configureFee.jsp"> Configure Fee</a>
                        </li>




                    </ul>

                </div>

            </nav>  
            <!-- /. NAV SIDE  -->
            <div id="page-wrapper" >
                <div id="page-inner">
                    <div class="row">
                        <div class="col-md-12">
                            <h2>Payment Status</h2>
                            
                            <c:if test="${requestScope.payment!=null}">
                            <div class="panel-body">
                                <div class="table-responsive">
                                    <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                        <thead
                                            <tr>
                                                <th>Name</th>
                                                <th>Date of Registration</th>
                                                <th>Amount</th>
                                                <th>Email</th>
                                                <th>Phone No</th>
                                                <th>Action</th>
                                            </tr>
                                            <form action="PaymentStatus" method="POST">
                                        <tbody>
                                            <c:forEach items="${requestScope.payment}" var="payment" varStatus="loop">
                                            <tr class="odd gradeX">
                                                <td>${payment.first_name} ${payment.last_name}</td>
                                                <td>${payment.date_of_registration}</td>
                                                <td>${payment.amount}</td>
                                                <td class="center">${payment.email}</td>
                                                <td class="center">${payment.phone_no}</td>
                                                <td> <button type="submit" name="notify" value="${payment.member_id}" class="btn btn-success btn-xs">Notify</button> <br>
                                                     <button type="submit" name="suspend" formaction="SuspendMember" value="${payment.member_id}" class="btn btn-danger btn-xs">Suspend</button> </td>
                                                
                                            </tr>
                                            </c:forEach>
                                        </tbody>
                                            </form>
                                    </table>
                                </div>

                            </div>
                         </c:if>
                        </div>
                    </div>
                    <!-- /. ROW  -->
                    <hr />

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
