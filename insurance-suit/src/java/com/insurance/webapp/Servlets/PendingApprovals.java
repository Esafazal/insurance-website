/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insurance.webapp.Servlets;

import com.insurance.webapp.Dao.QueryDao;
import com.insurance.webapp.EntityBean.Member;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sachi Keragala
 */
public class PendingApprovals extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        QueryDao dao = new QueryDao();
        List<Member> member = dao.getNewRegistrations();
        request.setAttribute("member", member);
        request.getRequestDispatcher("/adminJsp/pendingApprovals.jsp").forward(request, response);
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String memberID = request.getParameter("accept");
        QueryDao dao = new QueryDao();
        String type = dao.getVehicleType(Integer.parseInt(memberID));
        int amount = dao.getMembershipFee(type);
        dao.addMemberPayment(memberID, amount);
        dao.ifAcceptMember(memberID);
        
        List<Member> member = dao.getNewRegistrations();
        request.setAttribute("member", member);
        request.getRequestDispatcher("/adminJsp/pendingApprovals.jsp").forward(request, response);
       
    }
  
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
