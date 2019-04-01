/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insurance.webapp.Servlets;

import com.insurance.webapp.Dao.QueryDao;
import com.insurance.webapp.EntityBean.Member;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DELL
 */
@WebServlet(name = "MemberEditPassword", urlPatterns = {"/MemberEditPassword"})
public class MemberEditPassword extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("/userJsp/userProfile.jsp");
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Member member = new Member();

//        String currentpassword = request.getParameter("currentpassword");
        String newpassword = request.getParameter("newpassword");

        member.setPassword(newpassword);

        QueryDao dao = new QueryDao();
        int rows = dao.editMemberPassword(member,"1");

        String message = null;

        if (rows == 0) {
            message = "Couldn't change password. Something went wrong!";
            
            System.out.println("");
        } else {
            message = "Password changed Successfully!";
        }
        getServletContext().getRequestDispatcher("/userJsp/home.jsp").forward(request, response);

    
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
