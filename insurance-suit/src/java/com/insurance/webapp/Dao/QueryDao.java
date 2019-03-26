/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insurance.webapp.Dao;

import com.insurance.webapp.EntityBean.Member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author crazydude
 */
public class QueryDao {

    public ArrayList<Member> getMemberDetails(String memberId) {

        String firstName, lastName, username, address, date_of_birth, date_of_registration, nic, email, phone;

        try {
            Connection connection = DBConnection.getConnection();

            String query = "SELECT * FROM `member` WHERE member_id = ? ";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
           
            preparedStatement.setString(1, memberId);
//            preparedStatement.executeQuery(query);

            ArrayList<Member> memberList = new ArrayList();

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Member member = new Member();

                member.setFirst_name(resultSet.getString("first_name"));
                member.setLast_name(resultSet.getString("last_name"));
                member.setAddress(resultSet.getString("address"));
                member.setDob(resultSet.getDate("dob"));
                member.setNic(resultSet.getString("nic"));
                member.setDate_of_registration(resultSet.getDate("date_of_registration"));
                member.setEmail(resultSet.getString("email"));
                member.setPhone_no(resultSet.getString("phone_no"));
                member.setUsername(resultSet.getString("username"));

                memberList.add(member);
             
                return memberList;

            }

        } catch (SQLException ex) {
            Logger.getLogger(QueryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
