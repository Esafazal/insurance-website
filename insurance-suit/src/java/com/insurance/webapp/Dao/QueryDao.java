/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insurance.webapp.Dao;

import com.insurance.webapp.EntityBean.Admin;
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

    public Member showMemberDetails(String userID) {

        Connection connection = DBConnection.getConnection();

        String query = "SELECT * FROM Member WHERE ";
        return null;
    }

    public List<Member> showMembers() {

        List<Member> member = new ArrayList<>();
        try {
            Connection connection = DBConnection.getConnection();

            String query = "SELECT * FROM `member` ";
//            String query = "SELECT * FROM Member LIKE first_name=? OR  last_name=?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setString(1, firsname);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Member memberData = new Member();

                memberData.setFirst_name(rs.getString("first_name"));
                memberData.setLast_name(rs.getString("last_name"));
                memberData.setAddress(rs.getString("address"));
                memberData.setDob(rs.getDate("dob"));
                memberData.setNic(rs.getString("nic"));
                memberData.setDate_of_registration(rs.getDate("date_of_registration"));
                memberData.setEmail(rs.getString("email"));
                memberData.setPhone_no(rs.getString("phone_no"));
                memberData.setUsername(rs.getString("username"));
                member.add(memberData);

            }
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(QueryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return member;
    }

    public List<Member> lookForMembers(String firstname) {
        List<Member> member = new ArrayList<>();
        try {

            Connection connection = DBConnection.getConnection();
            String query = "SELECT * FROM member WHERE first_name LIKE '?%'";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, firstname);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Member memberData = new Member();

                memberData.setFirst_name(rs.getString("first_name"));
                memberData.setLast_name(rs.getString("last_name"));
                memberData.setAddress(rs.getString("address"));
                memberData.setDob(rs.getDate("dob"));
                memberData.setNic(rs.getString("nic"));
                memberData.setDate_of_registration(rs.getDate("date_of_registration"));
                memberData.setEmail(rs.getString("email"));
                memberData.setPhone_no(rs.getString("phone_no"));
                memberData.setUsername(rs.getString("username"));
                member.add(memberData);

            }
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(QueryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return member;

    }


    public int updateAllMembershipFee(int car, int van, int bike, int threewheeler) {

        int rowsAffected = 0;

        try {

            Connection connection = DBConnection.getConnection();

            String query = "INSERT INTO membership_fee \n"
                    + "   (type, charge_amount)\n"
                    + "   VALUES \n"
                    + "       (\"bike\", ?),\n"
                    + "       (\"car\", ?),\n"
                    + "       (\"threewheeler\", ?),\n"
                    + "       (\"van\", ?)\n"
                    + "   ON DUPLICATE KEY UPDATE \n"
                    + "       charge_amount = VALUES(charge_amount);";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, bike);
            preparedStatement.setInt(2, car);
            preparedStatement.setInt(3, threewheeler);
            preparedStatement.setInt(4, van);

            if (preparedStatement.execute()) {
                rowsAffected++;
            };

        } catch (SQLException sQLException) {
            sQLException.printStackTrace();
        }
        return rowsAffected;
    }
}
