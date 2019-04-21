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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DELL
 */
@WebServlet(name = "MemberEditDetails", urlPatterns = {"/MemberEditDetails"})
public class MemberEditDetails extends HttpServlet {

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
        String sesName = (String) request.getSession().getAttribute("username");

        String username = request.getParameter("username");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String phone_no = request.getParameter("phoneno");

        QueryDao dao = new QueryDao();
        boolean user = dao.checkUsername(username);
        int memberID = dao.getMemberID(sesName);
        Member memberList = dao.getMemberDetails(memberID);
        if (user) {
            
            request.setAttribute("memberList", memberList);

            String errorMessage = "Username already exists!";
            request.setAttribute("usererror", errorMessage);
            request.getRequestDispatcher("/userJsp/userProfile.jsp").forward(request, response);

        } else {

            Member member = new Member();
            member.setUsername(username);
            member.setAddress(address);
            member.setEmail(email);
            member.setPhone_no(phone_no);
            int rows = dao.editMemberDetails(member, memberID);
            request.setAttribute("memberList", memberList);
//            String done = "Successfully updated user profile!";
//            request.setAttribute("done", done);
            PrintWriter out = response.getWriter();
            out.println("Successfully updated user profile, Please login again!");
//            request.getRequestDispatcher("/index.jsp").forward(request, response);
            response.setHeader("Refresh", "5;url=../index.jsp");
//            response.sendRedirect("");
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
