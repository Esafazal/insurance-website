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
@WebServlet(name = "MemberEditDetails", urlPatterns = {"/MemberEditDetails"})
public class MemberEditDetails extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.sendRedirect("/userJsp/userProfile.jsp");
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
        int rows = dao.editMemberDetails(member,"1");

        String message = null;

        if (rows == 0) {
            message = "Couldn't update Details. Something went wrong!";
            
            System.out.println("");
        } else {
            message = "Updated Successfully!";
        }
        getServletContext().getRequestDispatcher("/userJsp/home.jsp").forward(request, response);

    }
    
    

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
