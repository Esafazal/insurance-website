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

/**
 *
 * @author SINGER
 */
@WebServlet(name = "SetConfigureFee", urlPatterns = {"/SetConfigureFee"})
public class SetConfigureFee extends HttpServlet {

    private int amount;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/adminJsp/configureFee.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String car = request.getParameter("car");
        String van = request.getParameter("van");
        String bike = request.getParameter("bike");
        String threewheeler = request.getParameter("threewheeler");

        String success = null, fail = null;
        QueryDao dao = new QueryDao();
        
        if (!car.equals("") && !van.equals("") && !bike.equals("") && !threewheeler.equals("")) {
            dao.updateAllMembershipFee(car, van, bike, threewheeler);
            success = "Updated Successfully";
        } else {
            fail = "Cannot update a single field only!";
        }

        request.setAttribute("done", success);
        request.setAttribute("error2", fail);
        request.getRequestDispatcher("/adminJsp/configureFee.jsp").forward(request, response);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
