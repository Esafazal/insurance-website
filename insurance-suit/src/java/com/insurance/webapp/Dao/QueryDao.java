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
                    + "`password`)"
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
            }

        } catch (SQLException sQLException) {
            sQLException.printStackTrace();
        }

        return rowsAffected;
    }

    public int registeVehicle(Member member) {
        int rowsAffected = 0;
        try {
            Connection connection = DBConnection.getConnection();
            String query = "INSERT INTO Vehicle"
                    + "(vehicle_number,"
                    + "vehicle_type,"
                    + "model, "
                    + "description,"
                    + "member_id)"
                    + "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, member.getVehicle_number());
            preparedStatement.setString(2, member.getVehicle_type());
            preparedStatement.setString(3, member.getVehicle_model());
            preparedStatement.setString(4, member.getVehicle_condition());
            preparedStatement.setInt(5, member.getMember_id());

            if (preparedStatement.execute()) {
                rowsAffected++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowsAffected;
    }

    public int getMemberID(String username) {
        int id = 0;
        try {
            Connection connection = DBConnection.getConnection();
            String query = "SELECT member_id FROM Member WHERE username=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("member_id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;

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

    public Member getMemberDetails(String memberId) {

        Member member = new Member();
        try {
            Connection connection = DBConnection.getConnection();
            String query = "SELECT * FROM `Member` WHERE username = ? ";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, memberId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                member.setFirst_name(resultSet.getString("first_name"));
                member.setLast_name(resultSet.getString("last_name"));
                member.setAddress(resultSet.getString("address"));
                member.setDob(resultSet.getDate("dob"));
                member.setNic(resultSet.getString("nic"));
                member.setDate_of_registration(resultSet.getDate("date_of_registration"));
                member.setEmail(resultSet.getString("email"));
                member.setPhone_no(resultSet.getString("phone_no"));
                member.setUsername(resultSet.getString("username"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(QueryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return member;
    }

    public int editMemberDetails(Member member, String username) {

        int rowsAffected = 0;

        try {
            Connection connection = DBConnection.getConnection();

            String query = "UPDATE Member SET address = ?, email = ?, phone_no = ?, username = ? WHERE username = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, member.getAddress());
            preparedStatement.setString(2, member.getEmail());
            preparedStatement.setString(3, member.getPhone_no());
            preparedStatement.setString(4, member.getUsername());
            preparedStatement.setString(5, username);

            if (preparedStatement.execute()) {
                rowsAffected++;
            };

        } catch (SQLException sQLException) {
            sQLException.printStackTrace();
        }

        return rowsAffected;
    }

    public int editMemberPassword(Member member, String memberId) {

        int rowsAffected = 0;

        try {
            Connection connection = DBConnection.getConnection();

            String query = "UPDATE `Member` SET `password`= ? WHERE member_id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, member.getPassword());
            preparedStatement.setString(2, memberId);

            if (preparedStatement.execute()) {
                rowsAffected++;
            };

        } catch (SQLException sQLException) {
            sQLException.printStackTrace();
        }

        return rowsAffected;
    }

    public int requestClaim(Member member) {

        int rowsAffected = 0;

        try {
            Connection connection = DBConnection.getConnection();

            String query = "INSERT INTO `Claim`("
                    + "`claim_date`,"
                    + " `claim_amount`,"
                    + " `description`, "
                    + " `incident_date`,"
                    + " `quotation_place`,"
                    + " `member_id`)"
                    + " VALUES (?,?,?,?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setDate(1, (new java.sql.Date(member.getClaim_date().getTime())));
            preparedStatement.setInt(2, member.getClaim_amount());
            preparedStatement.setString(3, member.getClaim_description());
            preparedStatement.setDate(4, (new java.sql.Date(member.getIncident_date().getTime())));
            preparedStatement.setString(5, member.getQuotation_place());
            preparedStatement.setInt(6, member.getMember_id());

            if (preparedStatement.execute()) {
                rowsAffected++;
            };

        } catch (SQLException sQLException) {
            sQLException.printStackTrace();
        }

        return rowsAffected;
    }

    public String getVehicle(int memberID) {
        String vehicleNumber = null;
        try {
            Connection connection = DBConnection.getConnection();
            String query = "SELECT vehicle_number FROM Vehicle WHERE member_id =?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, memberID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                vehicleNumber = resultSet.getString("vehicle_number");
            }
        } catch (SQLException sQLException) {
            sQLException.printStackTrace();
        }
        return vehicleNumber;
    }

}
