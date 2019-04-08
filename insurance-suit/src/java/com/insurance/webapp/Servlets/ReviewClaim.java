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
 * @author crazydude
 */
@WebServlet(name = "ReviewClaim", urlPatterns = {"/ReviewClaim"})
public class ReviewClaim extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        QueryDao dao = new QueryDao();
        List<Member> claim = dao.getNewClaims();
        request.setAttribute("claims", claim);
        request.getRequestDispatcher("/adminJsp/reviewClaim.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String memberID = request.getParameter("accept");
        System.out.println("MEMBER IDDDDDDDDDDDDDDDD"+memberID);
        QueryDao dao = new QueryDao();
        dao.ApproveClaim(memberID);

        List<Member> claims = dao.getNewClaims();
        request.setAttribute("claims", claims);
        request.getRequestDispatcher("/adminJsp/reviewClaim.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
