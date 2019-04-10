/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.check.eligibility.service.dao;

import com.check.eligibility.service.resource.CheckEligibility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author crazydude
 */
public class queryDao {

    public Boolean isEligible(String memberID) {

        boolean isEligible = false;
        try {
            Connection connection = DBConnection.getConnection();
            String query = "SELECT m.date_of_registration, c.claim_count\n"
                    + "FROM Member m, Claim c\n"
                    + "WHERE m.member_id = ?\n"
                    + "AND m.date_of_registration <= (now() - interval 6 month) AND c.member_id = m.member_id AND c.claim_count < 2";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, memberID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                isEligible = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(CheckEligibility.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isEligible;
    }

}
