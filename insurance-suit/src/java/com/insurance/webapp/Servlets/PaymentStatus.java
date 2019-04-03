/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insurance.webapp.Servlets;

import com.insurance.webapp.Dao.QueryDao;
import com.insurance.webapp.EntityBean.Member;
import com.insurance.webapp.Utils.EmailUtility;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sachi keragala
 */
public class PaymentStatus extends HttpServlet {

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
        List<Member> payment = dao.getNotPayedMembers();
        request.setAttribute("payment", payment);
        request.getRequestDispatcher("/adminJsp/paymentStatus.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //receiving the memberID from jsp
        String memberID = request.getParameter("notify");
        //creating an object of the dao class
        QueryDao dao = new QueryDao();
        //getting payable amount for the member using the member id
        int amount = dao.getMemberPayableAmount(memberID);
        //getting member details and assigning them to inidividual variables
        Member member = dao.getMemberDetails(Integer.parseInt(memberID));
        
        String toAddress = member.getEmail();
        String lastName = member.getLast_name();
        //creating subject and message to send emaiil
        String subject = "Drivers Association Srilanka(Payment Reminder)";
        String message = "Hi, " + lastName + ". \n\n This is to inform you to settle your outstanding balances of sum: "
                + amount + ", Inorder to avoid your acount being suspended temporarily. \n\n Thanks and Regards, \n Team B.";
        try {
            //calling the email utility method
            EmailUtility.sendEmail(host, port, user, pass, toAddress, subject, message);
        } catch (MessagingException ex) {
            Logger.getLogger(PaymentStatus.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            List<Member> payment = dao.getNotPayedMembers();
            request.setAttribute("payment", payment);
            request.getRequestDispatcher("/adminJsp/paymentStatus.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
