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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author crazydude
 */
public class PaymentGateway extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/userJsp/paymentGateway.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = (String) request.getSession().getAttribute("username");
        QueryDao dao = new QueryDao();
        int memberID = dao.getMemberID(username);
        dao.paymentGateway(memberID);

        int payAmount = dao.getPayableAmount(memberID);
        request.setAttribute("payAmount", payAmount);
        request.getRequestDispatcher("/userJsp/home.jsp").forward(request, response);
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
