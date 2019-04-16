/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insurance.webapp.Servlets;

import com.insurance.webapp.Dao.QueryDao;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author crazydude
 */
public class ClaimEligible extends HttpServlet {
    private String IP;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        QueryDao dao = new QueryDao();
        String username = (String) request.getSession().getAttribute("username");
        int memberID = dao.getMemberID(username);
        ServletContext context = getServletContext();
        IP = context.getInitParameter("IP");

        try {
            HttpResponse<String> uniResponse = Unirest.get("http://"+IP+":8088/rest/member/" + memberID)
                    .header("cache-control", "no-cache")
                    .header("Postman-Token", "4841fb87-2341-452a-9f8f-fea2a01e137d")
                    .asString();
            
            String res = uniResponse.getBody();

            if (res.equals("true")) {
                String vehicleNumber = dao.getVehicle(memberID);
                request.setAttribute("vehicleNO", vehicleNumber);
                request.getRequestDispatcher("/userJsp/makeClaim.jsp").forward(request, response);
            } else {
                String errorMessage="You are not eligible to make a claim as of now, Sorry!";
                request.setAttribute("error", errorMessage);
                request.getRequestDispatcher("/userJsp/claimEligible.jsp").forward(request, response);
            }
        } catch (UnirestException ex) {
            Logger.getLogger(ClaimEligible.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
