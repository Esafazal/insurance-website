/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insurance.webapp.Servlets;

import com.insurance.webapp.Dao.QueryDao;
import com.insurance.webapp.EntityBean.Member;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;
import javax.xml.bind.DatatypeConverter;

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

        QueryDao dao = new QueryDao();
        String currentpassword = request.getParameter("cpassword");
        String newpassword = request.getParameter("npassword");
        String username = (String) request.getSession().getAttribute("username");
        int memberID = dao.getMemberID(username);

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(MemberEditPassword.class.getName()).log(Level.SEVERE, null, ex);
        }
        byte[] digest = md.digest(currentpassword.getBytes(StandardCharsets.UTF_8));
        String currentmdpassword = DatatypeConverter.printHexBinary(digest);

        boolean check = dao.checkPassword(memberID, currentmdpassword);

        if (check) {
            try {
                md = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(MemberEditPassword.class.getName()).log(Level.SEVERE, null, ex);
            }
            digest = md.digest(newpassword.getBytes(StandardCharsets.UTF_8));
            String newmdpassword = DatatypeConverter.printHexBinary(digest);

            int rows = dao.editMemberPassword(newmdpassword, memberID);
            
            Member memberList = dao.getMemberDetails(memberID);
            request.setAttribute("memberList", memberList);
            
            String success = "Password was chnanged successfully!";
            request.setAttribute("done", success);
            request.getRequestDispatcher("/userJsp/userProfile.jsp").forward(request, response);

        } else {
            Member memberList = dao.getMemberDetails(memberID);
            request.setAttribute("memberList", memberList);
            
            String errorMessage = "Current password is incorrect!";
            request.setAttribute("passworderror", errorMessage);
            request.getRequestDispatcher("/userJsp/userProfile.jsp").forward(request, response);

        }

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
