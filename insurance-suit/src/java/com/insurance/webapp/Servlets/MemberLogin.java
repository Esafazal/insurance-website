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
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author crazydude
 */
@WebServlet(name = "MemberLogin", urlPatterns = {"/MemberLogin"})
public class MemberLogin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/userJsp/memberLogin.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //new password hashing to validate user
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(MemberLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        byte[] digest = md.digest(password.getBytes(StandardCharsets.UTF_8));
        String mdpassword = DatatypeConverter.printHexBinary(digest);
        //
        QueryDao queryDao = new QueryDao();
        int memberID = queryDao.getMemberID(username);
        Member member = queryDao.getMemberDetails(memberID);
        boolean match = queryDao.MemberSignIn(username, mdpassword);
        //check if user is suspended`   
        if (!member.getStatus().equals("suspended")) {
            boolean payStatus = queryDao.getPaymentStatusMember(memberID);
            //check if payment hasn't been made for more than a week
            if (!payStatus) {
                //check if user is older than a year, inorder to renew membership
                LocalDate now = LocalDate.now();
                Date date = (Date) member.getDate_of_registration();
                LocalDate regDate = date.toLocalDate();
                if (!regDate.isBefore(now.minusMonths(12))) {
                    //check if username and password matches
                    if (match) {
                        HttpSession session = request.getSession();
                        session.setAttribute("username", username);
                        session.setMaxInactiveInterval(60 * 20);
                        //get membershipfee NADEE
                        int amount = queryDao.getPayableAmount(memberID);
                        request.setAttribute("amount", amount);
                        //ends here
                        request.getRequestDispatcher("/userJsp/home.jsp").forward(request, response);
                        
                    } else {

                        String errorMessage = "Invalid Credentials, please login again!";
                        request.setAttribute("error", errorMessage);
                        request.getRequestDispatcher("/userJsp/memberLogin.jsp").forward(request, response);
                    }

                } else {
                    if (match) {
                        HttpSession session = request.getSession();
                        session.setAttribute("username", username);
                        session.setMaxInactiveInterval(60 * 20);
                        String errorMessage = "Your Account has expired, please renew account!";
                        request.setAttribute("error", errorMessage);
                        MakePayment fee = new MakePayment();
                        int memberFee = queryDao.getPayableAmount(memberID);
                        String vehicleType = queryDao.getVehicleType(memberID);
                        int annualFee = queryDao.calculateAnnualFee(vehicleType);

                        int count = queryDao.memberCountAll(vehicleType);
                        int averageAnnualFee = 0;
                        if (regDate.isBefore(now.minusMonths(12))) {

                            if (count != 0) {
                                averageAnnualFee = annualFee / count;
                            } else {
                                averageAnnualFee = annualFee;
                            }
                        }

                        request.setAttribute("memberFee", memberFee);
                        request.setAttribute("annualFee", averageAnnualFee);
                        request.setAttribute("outstanding", memberFee + averageAnnualFee);
                        request.getRequestDispatcher("/userJsp/annualMakePayment.jsp").forward(request, response);
                    }
                }

            } else {
                queryDao.ifRejectMember(memberID);
                String errorMessage = "Your account has been suspended due to lack of payment.";
                request.setAttribute("error", errorMessage);
                request.getRequestDispatcher("/userJsp/memberLogin.jsp").forward(request, response);
            }

        } else {

            String errorMessage = "Your Account access has been denied. Please contact administrator!";
            request.setAttribute("error", errorMessage);
            request.getRequestDispatcher("/userJsp/memberLogin.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
