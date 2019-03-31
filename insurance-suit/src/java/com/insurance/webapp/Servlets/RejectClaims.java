/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insurance.webapp.Servlets;

import com.insurance.webapp.Dao.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.sql.Connection;
import javax.swing.JOptionPane;

/**
 *
 * @author nadee
 */
public class RejectClaims extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
      String claimId = request.getParameter("claimId");
        System.out.println(claimId);

        try {
            Connection conn = DBConnection.getConnection();

            Statement stmt = conn.createStatement();
            

            String query = "update claim set review = 'rejected' where claim_id = '"+claimId+"' ";
            
            int result1 = stmt.executeUpdate(query);
            
            System.out.println(result1);
            
            if(result1 == 1){
                JOptionPane.showMessageDialog(null, "Claim status Rejected Successfully..","Success", JOptionPane.INFORMATION_MESSAGE);
                response.sendRedirect("reviewClaims.jsp");
                
                
            }
            else{
                JOptionPane.showMessageDialog(null, "Something went wrong..","Error", JOptionPane.ERROR_MESSAGE);
                response.sendRedirect("reviewClaims.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
