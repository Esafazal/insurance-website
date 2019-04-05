/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insurance.webapp.Servlets;

import com.insurance.webapp.Dao.QueryDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nadee
 */
public class MakePayment extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String username = (String) request.getSession().getAttribute("username");
        QueryDao Dao = new QueryDao();
        int memberID = Dao.getUserId(username);
        int memberFee = Dao.getPayableAmount(memberID);
        int annualFee = 25000;
        request.setAttribute("memberFee", memberFee);
        request.setAttribute("annualFee", annualFee);
        request.setAttribute("outstanding", memberFee+annualFee);
        
        request.getRequestDispatcher("/userJsp/makePayment.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
