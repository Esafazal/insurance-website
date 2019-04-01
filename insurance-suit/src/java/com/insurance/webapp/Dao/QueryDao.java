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

            String query = "INSERT INTO Vehicle"
                    + "(vehicle_number,"
                    + "vehicle_type,"
                    + "model, "
                    + "description) "
                    + "VALUES (?, ?, ?, ?);";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, member.getVehicle_number());
            preparedStatement.setString(2, member.getVehicle_type());
            preparedStatement.setString(3, member.getVehicle_model());
            preparedStatement.setString(4, member.getVehicle_condition());

            preparedStatement.execute();

            String query2 = "INSERT INTO `Member`\n"
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
                    + "`password`, "
                    + "`vehicle_number`)"
                    + "VALUES\n"
                    + "(?,?,?,?,?,?,?,?,?,?,?)";

            preparedStatement = connection.prepareStatement(query2);

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
            preparedStatement.setString(11, member.getVehicle_number());

            if (preparedStatement.execute()) {
                rowsAffected++;
            };

        } catch (SQLException sQLException) {
            sQLException.printStackTrace();
        }

        return rowsAffected;
    }

    public boolean isDuplicateVehicle(String vehicle_number) {
        boolean validate = false;
        try {
            Connection connection = DBConnection.getConnection();
            String query = "SELECT * FROM Vehicle WHERE vehicle_number=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, vehicle_number);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                validate = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(QueryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return validate;
    }

    public boolean adminSignIn(String username, String password) {

        boolean match = false;

        try {
            Connection connection = DBConnection.getConnection();

            String query = "SELECT * FROM `Admin` WHERE username=? AND password=?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                match = true;
            }

        } catch (SQLException ex) {

            Logger.getLogger(QueryDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return match;
    }

    public Boolean MemberSignIn(String username, String password) {
        boolean match = false;

        try {
            Connection connection = DBConnection.getConnection();

            String query = "SELECT * FROM Member WHERE username=? AND password=?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                match = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(QueryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return match;
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
