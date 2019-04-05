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
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nadee
 */
public class ClaimStatus extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = (String) request.getSession().getAttribute("username");
        QueryDao dao = new QueryDao();
        int member = dao.getUserId(username);
        String memberID = Integer.toString(member);
        List<Member> status = dao.getClaimStatus(memberID);
        request.setAttribute("status", status);
        request.setAttribute("username", username);

        request.getRequestDispatcher("/userJsp/claimStatus.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = (String) request.getSession().getAttribute("username");
        String memberID = request.getParameter("cancel");
        QueryDao dao = new QueryDao();
        dao.cancelRequestedClaim(memberID);

        List<Member> status = dao.getClaimStatus(memberID);
        request.setAttribute("status", status);
        request.setAttribute("username", username);

        request.getRequestDispatcher("/userJsp/claimStatus.jsp").forward(request, response);

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
