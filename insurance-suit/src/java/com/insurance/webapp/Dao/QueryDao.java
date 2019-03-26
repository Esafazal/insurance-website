/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insurance.webapp.Dao;

import com.insurance.webapp.EntityBean.Member;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author crazydude
 */
public class QueryDao {

    public int registerMember(Member member) {

        int rowsAffected = 0;

        try {
            Connection connection = DBConnection.getConnection();
            
            String query = "INSERT INTO `Member`\n"
                    + "("
                    + "`first_name`,\n"
                    + "`last_name`,\n"
                    + "`address`,\n"
                    + "`dob`,\n"
                    + "`nic`,\n"
                    + "`date_of_registration`,\n"
                    + "`email`,\n"
                    + "`phone_no`,\n"
                    + "`username`,\n"
                    + "`password`)\n"
                    + "VALUES\n"
                    + "(?,?,?,?,?,?,?,?,?,?)";
            
            
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, member.getFirst_name());
            preparedStatement.setString(2, member.getLast_name());
            preparedStatement.setString(3, member.getAddress());
            preparedStatement.setDate(4, (new java.sql.Date( member.getDob().getTime())));
            preparedStatement.setString(5, member.getNic());
            preparedStatement.setDate(6, (new java.sql.Date( member.getDate_of_registration().getTime())));
            preparedStatement.setString(7, member.getEmail());
            preparedStatement.setString(8, member.getPhone_no());
            preparedStatement.setString(9, member.getUsername());
            preparedStatement.setString(10, member.getPassword());

            if(preparedStatement.execute()){
                rowsAffected++; };

        } catch (SQLException sQLException) {
            sQLException.printStackTrace();
        }

        return rowsAffected;
    }

}
