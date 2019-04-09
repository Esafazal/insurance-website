/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insurance.webapp.Utils;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author crazydude
 */
public class webserviceTest {

    public static void main(String[] args) throws IOException, UnirestException {
        
        
        HttpResponse<String> uniResponse = Unirest.get("http://192.168.1.7:8088/rest/member/1")
                .header("cache-control", "no-cache")
                .header("Postman-Token", "4841fb87-2341-452a-9f8f-fea2a01e137d")
                .asString();
        
        String res = uniResponse.getBody();
        
        if (res.equals("true")) {

            JOptionPane.showMessageDialog(null, "You are eligible motherfucker", "Successful", JOptionPane.INFORMATION_MESSAGE);
//                request.getRequestDispatcher("/userJsp/makeClaim.jsp").forward(request, response);
        } else {
            JOptionPane.showMessageDialog(null, "Your are not eligible motherfucker", "Error", JOptionPane.ERROR_MESSAGE);
//                request.getRequestDispatcher("/userJsp/claimEligible.jsp").forward(request, response);
        }
    }

}
