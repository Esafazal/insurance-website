/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insurance.webapp.Servlets;

import com.insurance.webapp.Dao.QueryDao;
import com.insurance.webapp.EntityBean.Member;
import com.insurance.webapp.Utils.AutoGenerate;
import com.insurance.webapp.Utils.DateUtil;
import com.insurance.webapp.Utils.EmailUtility;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "MemberEditDetails", urlPatterns = {"/MemberEditDetails"})
public class MemberEditDetails extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
//        request.getRequestDispatcher("/userJsp/userProfile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Member member = new Member();

        String username = request.getParameter("username");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String phone_no = request.getParameter("phoneno");


//        Member member = new Member(firstName, lastName, address, dob, nic, date_of_registration, email, phone_no, username, password);
        member.setUsername(username);
        member.setAddress(address);
        member.setEmail(email);
        member.setPhone_no(phone_no);

        QueryDao dao = new QueryDao();
        int rows = dao.editUserDetails(member);

        String message = null;

        if (rows == 0) {
            message = "Couldn't Register. Something went wrong!";
            
            System.out.println("");
        } else {
            message = "Registered Successfully";
        }
        getServletContext().getRequestDispatcher("/userJsp/success.jsp").forward(request, response);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
