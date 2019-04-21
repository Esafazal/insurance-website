/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insurance.webapp.Servlets;

import com.insurance.webapp.Dao.QueryDao;
import com.insurance.webapp.EntityBean.Member;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
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
        String vehicleType = Dao.getVehicleType(memberID);
        int annualFee = Dao.calculateAnnualFee(vehicleType);

        int count = Dao.memberCountAll(vehicleType);
        int averageAnnualFee = 0;

        Member member = Dao.getMemberDetails(memberID);
        LocalDate now = LocalDate.now();
        Date date = (Date) member.getDate_of_registration();
        LocalDate regDate = date.toLocalDate();
        if (regDate.isBefore(now.minusMonths(12))){
            
            if (count != 0) {
                averageAnnualFee = annualFee / count;
            } else {
                averageAnnualFee = annualFee;
            }
        } 
       
        request.setAttribute("memberFee", memberFee);
        request.setAttribute("annualFee", averageAnnualFee);
        request.setAttribute("outstanding", memberFee + averageAnnualFee);

        request.getRequestDispatcher("/userJsp/makePayment.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    public String getServletInfo() {
        return "Short description";
    }

}
