/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insurance.webapp.Servlets;

import com.insurance.webapp.Dao.QueryDao;
import com.insurance.webapp.EntityBean.Member;
import com.insurance.webapp.Utils.DateUtil;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DELL
 */
@WebServlet(name = "MemberMakeClaim", urlPatterns = {"/MemberMakeClaim"})
public class MemberMakeClaim extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        QueryDao dao = new QueryDao();
        String username = (String) request.getSession().getAttribute("username");
        int memberID = dao.getMemberID(username);
        String vehicleNumber = dao.getVehicle(memberID);
        request.setAttribute("vehicleNO", vehicleNumber);
        request.getRequestDispatcher("/userJsp/claimEligible.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Member member = new Member();
        QueryDao dao = new QueryDao();
        String username = (String) request.getSession().getAttribute("username");

        String incidentDate = request.getParameter("incidentdate");
        int claimAmount = Integer.parseInt(request.getParameter("claimamount"));
        String claimDescription = request.getParameter("claimdescription");
        String quotationPlace = request.getParameter("quoationplace");
        String claimDate = LocalDate.now().toString();
        int memberID = dao.getMemberID(username);

        Date incident_Date = null;
        Date claim_date = null;
        try {
            incident_Date = DateUtil.stringToDate(incidentDate, DateUtil.DATE_FORMAT);
            claim_date = DateUtil.stringToDate(claimDate, DateUtil.DATE_FORMAT);

        } catch (ParseException ex) {
            Logger.getLogger(MemberMakeClaim.class.getName()).log(Level.SEVERE, null, ex);
        }

        member.setIncident_date(incident_Date);
        member.setClaim_amount(claimAmount);
        member.setClaim_description(claimDescription);
        member.setQuotation_place(quotationPlace);
        member.setClaim_date(claim_date);
        member.setMember_id(memberID);

        int rows = dao.requestClaim(member);

        String message = null;

        if (rows == 0) {
            message = "made request";
            System.out.println(message);
        } else {
            message = "Claim Request Failed";
            System.out.println(message);
        }
        request.getRequestDispatcher("/userJsp/claimStatus.jsp").forward(request, response);

    }

    @Override
    public String getServletInfo() {
        return "Please request claim for approval.";
    }// </editor-fold>

}
