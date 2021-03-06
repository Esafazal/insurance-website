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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DELL
 */
@WebServlet(name = "MemberDetails", urlPatterns = {"/MemberDetails"})
public class MemberDetails extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = (String) request.getSession().getAttribute("username");
        QueryDao queryDao = new QueryDao();
        int memberID = queryDao.getMemberID(username);
        Member memberList = queryDao.getMemberDetails(memberID);

        request.setAttribute("memberList", memberList);
        request.getRequestDispatcher("/userJsp/userProfile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //if the adin presses reject vlaim buttton, the request is forwarded to tthis servlet
        String memberID = request.getParameter("reject");
        QueryDao dao = new QueryDao();
        dao.RejectClaim(Integer.parseInt(memberID));

        List<Member> claims = dao.getNewClaims();
        request.setAttribute("claims", claims);
        request.getRequestDispatcher("/adminJsp/pendingApprovals.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
