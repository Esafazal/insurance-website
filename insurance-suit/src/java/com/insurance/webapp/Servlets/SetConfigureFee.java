/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insurance.webapp.Servlets;

import com.insurance.webapp.Dao.QueryDao;
import com.insurance.webapp.EntityBean.Admin;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
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

//        response.sendRedirect("/adminJsp/configureFee.jsp");
        request.getRequestDispatcher("/adminJsp/configureFee.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String car = request.getParameter("car");
        String van = request.getParameter("van");
        String bike = request.getParameter("bike");
        String threewheeler = request.getParameter("threewheeler");

        QueryDao dao = new QueryDao();
//       if(car != null){
//           dao.updateMembershipFeeCar(Integer.parseInt(car));
//       }
//       else if(van != null){
//           dao.updateMembershipFeeVan(Integer.parseInt(van));
//       }
//       else if(bike != null){
//             dao.updateMembershipFeeBike(Integer.parseInt(bike));
//       }
//       else if(threewheeler != null){
//           dao.updateMembershipFeeThreewheeler(Integer.parseInt(threewheeler));
//       }
//       else{
//           request.setAttribute("error2","Didn't perform any updates!");
//           request.getRequestDispatcher("/adminJsp/configureFee.jsp").forward(request, response);
//       }
        dao.updateAllMembershipFee(Integer.parseInt(car), Integer.parseInt(van), 
                Integer.parseInt(bike), Integer.parseInt(threewheeler));
        String success = "Updated Successfully";

        request.setAttribute("done", success);
        request.setAttribute("error2","Didn't perform any updates!");
        request.getRequestDispatcher("/adminJsp/configureFee.jsp").forward(request, response);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
