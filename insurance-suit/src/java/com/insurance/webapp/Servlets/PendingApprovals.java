/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insurance.webapp.Servlets;

import com.insurance.webapp.Dao.QueryDao;
import com.insurance.webapp.EntityBean.Member;
import com.insurance.webapp.Utils.AutoGenerate;
import com.insurance.webapp.Utils.EmailUtility;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author sachi Keragala
 */
public class PendingApprovals extends HttpServlet {

    private String host;
    private String port;
    private String user;
    private String pass;

    public void init() {
        // reads SMTP server setting from web.xml file
        ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        user = context.getInitParameter("user");
        pass = context.getInitParameter("pass");
    }

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
        //
        QueryDao dao = new QueryDao();
        //
        String type = dao.getVehicleType(Integer.parseInt(memberID));
        //
        int amount = dao.getMembershipFee(type);
        //
        dao.addMemberPayment(memberID, amount);
        //
        dao.ifAcceptMember(memberID);
        //
        Member member = dao.getMemberDetails(Integer.parseInt(memberID));
        //
        String password = AutoGenerate.generateKey(10, AutoGenerate.ALPHA_CAPS + AutoGenerate.ALPHA + AutoGenerate.NUMERIC);
        //hashing the password
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
        byte[] digest = md.digest(password.getBytes(StandardCharsets.UTF_8));
        String mdpassword = DatatypeConverter.printHexBinary(digest);
        
        dao.updateMemberCredentials(Integer.parseInt(memberID), mdpassword);
        
        String subject = "Drivers Association Srilanka Registration";
        String content = "Hi, " + member.getFirst_name() + ".\n\nWe warmly welcome you to our association to have you protected."
                + " Please note that the following credentials can be used to login to your account."
                + " Further more, please change the username and password since it was system generated."
                + " Thank You \n\n" + "Username: " + member.getUsername() + "\n" + "Password: " + password + "\n\n"
                + "Kind Regards, \n\n" + "Team Group B.";

        try {
            EmailUtility.sendEmail(host, port, user, pass, member.getEmail(), subject, content);
        } catch (MessagingException ex) {
            Logger.getLogger(PendingApprovals.class.getName()).log(Level.SEVERE, null, ex);
        }

        List<Member> memberList = dao.getNewRegistrations();
        request.setAttribute("member", memberList);
        request.getRequestDispatcher("/adminJsp/pendingApprovals.jsp").forward(request, response);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
