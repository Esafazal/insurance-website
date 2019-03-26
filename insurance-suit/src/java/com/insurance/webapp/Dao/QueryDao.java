/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insurance.webapp.Dao;

import com.insurance.webapp.EntityBean.Admin;
import com.insurance.webapp.EntityBean.Member;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            preparedStatement.setDate(4, (new java.sql.Date(member.getDob().getTime())));
            preparedStatement.setString(5, member.getNic());
            preparedStatement.setDate(6, (new java.sql.Date(member.getDate_of_registration().getTime())));
            preparedStatement.setString(7, member.getEmail());
            preparedStatement.setString(8, member.getPhone_no());
            preparedStatement.setString(9, member.getUsername());
            preparedStatement.setString(10, member.getPassword());

            if (preparedStatement.execute()) {
                rowsAffected++;
            };

        } catch (SQLException sQLException) {
            sQLException.printStackTrace();
        }

        return rowsAffected;
    }

    public Admin adminSignIn(String username, String password) {

        String DbUsername, DbPassword;
        boolean match = false;

        try {
            Connection connection = DBConnection.getConnection();

            String query = "SELECT * FROM `Admin` WHERE username = ? AND password = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()) {

                DbUsername = resultSet.getString(username);
                DbPassword = resultSet.getString(password);

                if (DbUsername.equals(username) && DbPassword.equals(password)) {

                    match = true;
                } else {
                    System.out.println("MATCH " + match);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(QueryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Member> getMemberDetails(String memberId) {

        try {
            Connection connection = DBConnection.getConnection();

            String query = "SELECT * FROM `Member` WHERE member_id = ? ";

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
