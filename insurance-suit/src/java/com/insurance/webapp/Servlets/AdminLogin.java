/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insurance.webapp.Servlets;

import com.insurance.webapp.Dao.QueryDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author crazydude
 */
@WebServlet(name = "AdminLogin", urlPatterns = {"/AdminLogin"})
public class AdminLogin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

           request.getRequestDispatcher("/adminJsp/adminLogin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String pass = request.getParameter("password");
        
        QueryDao queryDao = new QueryDao();
        boolean valid = queryDao.adminSignIn(username, pass);

        if (valid) {

            HttpSession session = request.getSession();
            session.setAttribute("username", username);

            request.getRequestDispatcher("/adminJsp/dashboard.jsp").forward(request, response);

        } else {
            String errorMessage = "Invalid Credentials, please login again!";
            request.setAttribute("error", errorMessage);
            request.getRequestDispatcher("/adminJsp/adminLogin.jsp").forward(request, response);

        }
    }

}
