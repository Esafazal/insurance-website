<%-- 
    Document   : home
    Created on : Mar 21, 2019, 6:11:48 PM
    Author:Nadee 
--%>    
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
		Connection conn = null;
		Statement stmt = null;
		ResultSet res = null;
                ResultSet res2 = null;
		
		String uname = session.getAttribute("username").toString();
                QueryDao queryDao = new QueryDao();
                String userID = queryDao.getUserId(uname);
               
                System.out.println(userID);
	
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/insurance_website", "root", "");
		}
		
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	
%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />

        <title>Home page</title>

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
                            <a class="active-menu" href="../userJsp/home.jsp"> Home</a>
                        </li>
                        <li>
                            <a href="../userJsp/userProfile.jsp"> Profile</a>
                        </li>
                        <li>
                            <a   href="../userJsp/makeClaim.jsp"> Make Claim</a>
                        </li>
                        <li>
                            <a  href="../userJsp/makePayment.jsp"> Make Payment</a>
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
                        <div class="col-md-12" style="
                             text-align: center
                             ">
                            <h2>Home</h2>
                            <hr />
                        </div>
                        <style>
                            .aboutimg{
                                width: 80%;
                                height: 20%;
                                margin: 0px 0px 0px 130px;  /*top right bottom left*/
                            }
                            ;
                        </style>


                        <img src="https://img.blesk.cz/img/1/normal620/1788957-img-auto-ridic-bouracka-nehoda-v0.jpg?v=0" class="aboutimg">
                            <!-- /. ROW  -->

                            <style>h1{
                                    text-align: center
                                }
                                P{  
                                    text-align: center
                                }
                            </style>
                            <h1>Welcome to Insurance Suit</h1>
                            <p>Insurance Suit Association Ltd (ISAL) registered under 
                                the name of Insurance suit
                                as a public limited liability company, incorporated 
                                in Sri Lanka under companies act No 07 of 1997 and 
                                re- registered under the companies act No 11 of 2008. 
                                SICL is authorized to carry on Drivers Association, 
                                under the Regulation of Association Industry Act No 43 of 2000. ISAL is an Drivers Association  
                                affiliated to Sanasa movement and therefore it vision is 
                                “To become a strong assurance company operating with a large customer base as 
                                a global tendency pavior of Micro assurance” whilst the mission in particular 
                                to Sanasa Insurance Company is “To give our clients an excellent service on all 
                                occasions and at all stages to mitigate their risk in order to improve their living 
                                conditions and secure economic development”. Hence it mainly focuses on providing insurance 
                                services to members of Sanasa societies and other CBOs of whom majority are in the rural sector.</p>


                            <%
				try { 
					stmt=conn.createStatement();
					String sql2 ="SELECT * FROM membership where member_id = '"+userID+"'";
									
					res = stmt.executeQuery(sql2);
					while(res.next()){
                                                     
                                        double amt = 5000;
                                        amt = amt - res.getDouble("amount");
										
                                  
                            %>
                               <h2 style="color:green;"><b>Outstanding Balance : <%=amt%></b></h2>--                            
                            <%
					}
				}
									
				catch (Exception e) {
										
                                    e.printStackTrace();
										
				}
							
							
							
			%>
							

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
