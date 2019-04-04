<%-- 
    Document   : claimStatus
    Created on : Mar 22, 2019, 12:03:03 AM
    Author     : crazydude
--%>
<%@page import="com.insurance.webapp.Dao.DBConnection"%>
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

    String uname = session.getAttribute("username").toString();
    QueryDao queryDao = new QueryDao();
    String userID = queryDao.getUserId(uname);

    System.out.println(userID);

%>

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
                            <a  href="../userJsp/home.jsp"> Home</a>
                        </li>
                        <li>
                            <a href="<%= response.encodeURL("MemberDetails")%>"> Profile</a>
                        </li>
                        <li>
                            <a   href="../userJsp/makeClaim.jsp"> Make Claim</a>
                        </li>
                        <li>
                            <a href="../userJsp/makePayment.jsp"> Make Payment</a>
                        </li>
                        <li>
                            <a class="active-menu" href="../userJsp/claimStatus.jsp"> Claim Status</a>
                        </li>



                    </ul>

                </div>

            </nav>  
            <!-- /. NAV SIDE  -->
            <div id="page-wrapper" >
                <div id="page-inner">
                    <div class="row">
                        <div class="col-md-12">
                            <h2>Claim Status</h2>

                        </div>
                    </div>
                    <!-- /. ROW  -->
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
                                conn = DBConnection.getConnection();

                                String query = "select * from claim where membership_id = '" + userID + "' ";
                                stmt = conn.createStatement();
                                res = stmt.executeQuery(query);

                                while (res.next()) {

                        %>

                        <tr>
                            <td><%=res.getString("claim_id")%></td>
                            <td><%=res.getString("vehicle_number")%></td>
                            <td><%=res.getString("incident_date")%></td>
                            <td><%=res.getString("claim_date")%></td>
                            <td><%=res.getString("description")%></td>
                            <td><%=res.getString("quotation_place")%></td>
                            <td><%=res.getString("claim_amount")%></td>
                            <td><%=res.getString("review")%></td>

                        </tr>


                        <%                    }

                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }

                        %>

                    </table>

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
