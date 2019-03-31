/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insurance.webapp.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

/**
 *
 * @author nadee
 */
@WebServlet(name = "addPayment", urlPatterns = {"/addPayment"})
public class addPayment extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
                String id = request.getParameter("memID");
		String amount = request.getParameter("totOutstanding");
		
	
		//System.out.println(email);
		//System.out.println(crseID + " " + crseName + " " + crseDesc + " " + crseCred + " " + crseWeeks + " " + crseAssign + " " + crseLec + " " + prof);
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/insurance_website", "root", "");
			Statement myStmt = myConn.createStatement();
                        
                        String query1 = "select count(*) as cnt from membership where member_id = '"+id+"'";//membership_id
                        ResultSet res = myStmt.executeQuery(query1);
                        
                        int rowCount = 0;
                        
                        while(res.next()){
                            rowCount = res.getInt(1);
                        }
                        
                        System.out.println(rowCount);
                        
                        if(rowCount == 0){
                            
                            String query2 = "insert into membership(member_id, amount) values ('" + id + "', '"+ amount + "') ";
                            
                            int myRs = myStmt.executeUpdate(query2);
                           
                            JOptionPane.showMessageDialog(null, "Payment added Successfully", "Registration",
					JOptionPane.INFORMATION_MESSAGE);
                            response.sendRedirect("makePayment.jsp");
                        }
                        
                        else{
                            
                            String query3 = "update membership set amount = amount + '"+amount+"' where member_id = '"+id+"' " ;                       
                            int myRs = myStmt.executeUpdate(query3);
                            System.out.print(myRs);
                            JOptionPane.showMessageDialog(null, "Payment added Successfully", "Registration",
					JOptionPane.INFORMATION_MESSAGE);
                            response.sendRedirect("makePayment.jsp");
                            
                        }
                        

		
		} catch (Exception e) {
                        e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Sorry, Something went wrong!", "Registration",
					JOptionPane.ERROR_MESSAGE);
			
			response.sendRedirect("makePayment.jsp");
		}
        
    }

}
