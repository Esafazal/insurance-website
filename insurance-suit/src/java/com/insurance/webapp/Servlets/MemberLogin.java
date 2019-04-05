/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insurance.webapp.Servlets;

import com.insurance.webapp.Dao.QueryDao;
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

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(MemberLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        byte[] digest = md.digest(password.getBytes(StandardCharsets.UTF_8));
        String mdpassword = DatatypeConverter.printHexBinary(digest);

        QueryDao queryDao = new QueryDao();
        boolean match = queryDao.MemberSignIn(username, mdpassword);

        if (match) {

            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setMaxInactiveInterval(120);
<<<<<<< HEAD

            request.getRequestDispatcher("/userJsp/home.jsp").forward(request, response);
        } else {
=======
            
              response.sendRedirect("UserHome");
        }
        else{
>>>>>>> origin/esa/pathum_branchMerge
            String errorMessage = "Invalid Credentials, please login again!";
            request.setAttribute("error", errorMessage);
            request.getRequestDispatcher("/userJsp/error.jsp").forward(request, response);
        }
}

@Override
        public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
