<%-- 
    Document   : home
    Created on : Mar 21, 2019, 6:11:48 PM
   
--%>
<%@page import="com.insurance.webapp.Dao.QueryDao"%>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.Connection" %>     
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Connection conn = null;
    Statement stmt = null;
    ResultSet res = null;

    try {
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/insurance_website", "root", "");
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }

%>
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
                            <a  href="<%= response.encodeURL("PaymentStatus")%>"> Payment Status</a>
                        </li>
                        <li>
                            <a  href="<%= response.encodeURL("PendingApprovals")%>"> Pending Approvals</a>
                        </li>
                        <li>
                            <a class="active-menu" href="../adminJsp/reviewClaims.jsp">Review Claims</a>
                        </li>
                        <li>
                            <a href="../adminJsp/searchMember.jsp"> Search Member</a>
                        </li>




                    </ul>

                </div>

            </nav>  
            <!-- /. NAV SIDE  -->
            <div id="page-wrapper" >
                <div id="page-inner">
                    <div class="row">
                        <div class="col-md-12">
                            <h2>Review Claims </h2>
                            <c:if test="${requestScope.claims!=null}">
                            <div class="panel-body">
                                <div class="table-responsive">
                                    <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                        <thead
                                            <tr>
                                                <th>Name</th>
                                                <th>Claim Date</th>
                                                <th>Description</th>
                                                <th>Incident Date</th>
                                                <th>Claim Amount</th>
                                                <th>Quotation Place</th>
                                                <th>Action</th>
                                            </tr>
                                            <form action="ReviewClaim" method="POST">
                                        <tbody>
                                            <c:forEach items="${requestScope.claims}" var="claim" varStatus="loop">
                                            <tr class="odd gradeX">
                                                <td>${claim.first_name} ${claim.last_name}</td>
                                                <td>${claim.claim_date}</td>
                                                <td>${claim.description}</td>
                                                <td class="center">${claim.incident_date}</td>
                                                <td class="center">${claim.claim_amount}</td>
                                                <td class="center">${claim.quotation_place}</td>
                                                <td> <button type="submit" name="accept" value="${claim.member_id}" class="btn btn-success btn-xs">Approve</button> <br>
                                                     <button type="submit" name="reject" formaction="RejectMember" value="${claim.member_id}" class="btn btn-danger btn-xs">Decline</button> </td>
                                                
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

                    <hr />


                    <style>
                        table {
                            font-family: arial, sans-serif;
                            border-collapse: collapse;
                            width: 100%;
                        }

                        td, th {
                            border: 1px solid #dddddd;
                            text-align: left;
                            padding: 8px;
                        }

                        tr:nth-child(even) {
                            background-color: #dddddd;
                        }
                    </style>



                        <table>
                            <tr>
                                <th>Claim Id</th>
                                <th>Vehicle No</th>
                                <th>Date of Incident</th>
                                <th>Claim Date</th>
                                <th>Description</th>
                                <th>Quotation Place</th>
                                <th>Quotation Amount</th>
                                <th>Review</th>

                            </tr>

                            <%                try {

                                    String query = "select * from claim";
                                    stmt = conn.createStatement();
                                    res = stmt.executeQuery(query);

                                    while (res.next()) {

                            %>
                            <form action="reviewClaims?claimId=<%=res.getString("claim_id")%>" method="POST">
                                <tr>
                                    <td><%=res.getString("claim_id")%></td>
                                    <td><%=res.getString("vehicle_number")%></td>
                                    <td><%=res.getString("incident_date")%></td>
                                    <td><%=res.getString("claim_date")%></td>
                                    <td><%=res.getString("description")%></td>
                                    <td><%=res.getString("quotation_place")%></td>
                                    <td><%=res.getString("claim_amount")%></td>
                                    <td><%=res.getString("review")%></td>
                                    <td><center>                                    
                                            <style>


                                                .button {
                                                    background-color: #4CAF50;
                                                    border: none;
                                                    color: white;
                                                    padding: 15px 32px;
                                                    text-align: center;
                                                    text-decoration: none;
                                                    display: inline-block;
                                                    font-size: 16px;
                                                    margin: 4px 2px;
                                                    cursor: pointer;
                                                    border-radius:10px;
                                                }
                                            </style>

                                            <input type="submit" class="button" value="Accept">
                                                </form>
                                                <form  action="RejectClaims?claimId=<%=res.getString("claim_id")%>" method="POST">
                                                    <input style="background-color: red" type="submit" class="button" value="Reject">
                                                </form>
                                        </center>
                                    </td>
                                </tr>


                                <%                    }

                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }

                                %>

                        </table>





                        <!--                    <table border = "1">
                                                <tr>
                                                    <th>Vehicle No</th>
                                                    <th>Date of Incident</th>
                                                    <th>Claim Date</th>
                                                    <th>Description</th>
                                                    <th>Quotation Place</th>
                                                    <th>Quotation Amt</th>
                                                    <th></th>
                                                </tr>
                        
                                                <tr>
                                                    
                                                        <th>Membership ID</th>
                                                        <th>Vehicle No</th>
                                                        <th>Date of Incident</th>
                                                        <th>Claim Date</th>
                                                        <th>Description</th>
                                                        <th>Quotation Place</th>
                                                        <th>Quotation Amount</th>
                                                    </
                                                        <th></th>
                                                </tr>
                                                
                                                <tr>
                                                    <b>
                                                        <th>Membership ID</th>
                                                        <th>Vehicle No</th>
                                                        <th>Date of Incident</th>
                                                        <th>Claim Date</th>
                                                        <th>Description</th>
                                                        <th>Quotation Place</th>
                                                        <th>Quotation Amount</th>
                                                    </b>
                                                        <th></th>
                                                </tr>
                        
                                            </table>-->

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
