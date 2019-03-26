/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insurance.webapp.Dao;

import com.insurance.webapp.EntityBean.Member;
import java.io.IOException;
import java.io.PrintWriter;
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
        
        QueryDao queryDao = new QueryDao();
        List memberList = queryDao.getMemberDetails("1");
        
        request.setAttribute("memberList",memberList);
        contextPath = request.getContextPath();
        getServletContext().getRequestDispatcher(contextPath + "/userJsp/userProfile.jsp").forward(request, response);
//        response.sendRedirect("/userJsp/userProfile.jsp");
                

//        String firstName = request.getParameter("firstname", firstname);
//        String lastName = request.getParameter("lastname");
//        String address = request.getParameter("address");
//        String birthday = request.getParameter("dob");
//        String nic = request.getParameter("nic");
//        String date_of_registration = request.getParameter("dor");
//        String email = request.getParameter("email");
//        String phone_no = request.getParameter("phoneno");
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
