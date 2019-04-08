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
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author crazydude
 */
@WebServlet(name = "Register", urlPatterns = {"/Register"})
public class Register extends HttpServlet {

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.sendRedirect("/userJsp/register.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Member member = new Member();
        //member details
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String address = request.getParameter("address");
        String birthday = request.getParameter("dob");
        String nic = request.getParameter("nic");
        String date_of_reg = LocalDate.now().toString();
        System.out.println(date_of_reg);
        String email = request.getParameter("email");
        String phone_no = request.getParameter("phoneno");
        String username = AutoGenerate.generateKey(10, AutoGenerate.ALPHA_CAPS);
        String password = AutoGenerate.generateKey(10, AutoGenerate.ALPHA_CAPS + AutoGenerate.ALPHA + AutoGenerate.NUMERIC);

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
        byte[] digest = md.digest(password.getBytes(StandardCharsets.UTF_8));
        String mdpassword = DatatypeConverter.printHexBinary(digest);

        //vehicle details
        String vehicle_number = request.getParameter("vehicle_number");
        String vehicle_type = request.getParameter("vehicle_type");
        String vehicle_model = request.getParameter("vehicle_model");
        String vehicle_condition = request.getParameter("vehicle_condition");

        Date dob = null;
        Date date_of_registration = null;
        try {
            dob = DateUtil.stringToDate(birthday, DateUtil.DATE_FORMAT);
            date_of_registration = DateUtil.stringToDate(date_of_reg, DateUtil.DATE_FORMAT);

        } catch (ParseException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
        QueryDao dao = new QueryDao();
        boolean match = dao.isDuplicateVehicle(vehicle_number);

        if (!match) {
            //member

            String subject = "Drivers Association Srilanka Registration";
            String content = "Hi, " + lastName + ".\n\nWe warmly welcome you to our association to have you protected."
                    + " Please note that the following credentials can be used to login to your account."
                    + " Further more, please change the username and password since it was system generated."
                    + " Thank You \n\n" + "Username: " + username + "\n" + "Password: " + password + "\n\n"
                    + "Kind Regards, \n\n" + "Team Group B.";

            try {
                EmailUtility.sendEmail(host, port, user, pass, email, subject, content);

                member.setFirst_name(firstName);
                member.setLast_name(lastName);
                member.setAddress(address);
                member.setDob(dob);
                member.setNic(nic);
                member.setDate_of_registration(date_of_registration);
                member.setEmail(email);
                member.setPhone_no(phone_no);
                member.setUsername(username);
                member.setPassword(mdpassword);

                int rows = dao.registerMember(member);
                //vehice
                int memberID = dao.getMemberID(username);

                member.setVehicle_type(vehicle_type);
                member.setVehicle_number(vehicle_number);
                member.setVehicle_model(vehicle_model);
                member.setVehicle_condition(vehicle_condition);
                member.setMember_id(memberID);

                dao.registeVehicle(member);

                String message = null;

                if (rows == 0) {
                    message = "Couldn't Register. Something went wrong!";
                } else {
                    message = "Registered Successfully";
                }

                request.setAttribute("email", email);
                getServletContext().getRequestDispatcher("/userJsp/success.jsp").forward(request, response);

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } else {
            PrintWriter out = response.getWriter();
            out.println("The vehicle '" + vehicle_number + "' already exists!");
        }
    }

    @Override
    public String getServletInfo() {
        return "Please register your self to this page if you are are someone who is willing to join the association.";
    }

}
