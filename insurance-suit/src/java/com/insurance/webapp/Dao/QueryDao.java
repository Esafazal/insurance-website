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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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

    public Member getMemberDetails(int memberID) {

        Member member = new Member();
        try {
            Connection connection = DBConnection.getConnection();
            String query = "SELECT * FROM `Member` WHERE member_id = ? ";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, memberID);

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

    public int editMemberPassword(String newPassword, int memberId) {

        int rowsAffected = 0;

        try {
            Connection connection = DBConnection.getConnection();

            String query = "UPDATE member SET password= ? WHERE member_id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, newPassword);
            preparedStatement.setInt(2, memberId);

            if (preparedStatement.execute()) {
                rowsAffected++;
            };

        } catch (SQLException sQLException) {
            sQLException.printStackTrace();
        }

        return rowsAffected;
    }

    public boolean checkPassword(int memberId, String password) {

        boolean match = false;

        try {
            Connection connection = DBConnection.getConnection();

            String query = "SELECT * FROM Member WHERE member_id=? AND password=?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, memberId);
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

    public int getPendingApprovals() {
        int count = 0;
        try {
            Connection connection = DBConnection.getConnection();
            String query = "SELECT COUNT(*) AS rowcount FROM Member WHERE status='pending';";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt("rowcount");
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public int getPendingClaims() {
        int count = 0;
        try {
            Connection connection = DBConnection.getConnection();
            String query = "SELECT COUNT(*) AS rowcount FROM Claim WHERE status='pending';";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt("rowcount");
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public int getPendingPayments() {
        int count = 0;
        try {
            Connection connection = DBConnection.getConnection();
            String query = "SELECT COUNT(*) AS rowcount FROM payment WHERE status='pending';";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt("rowcount");
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public List<Member> getNewRegistrations() {

        List<Member> registers = new ArrayList<>();
        try {
            Connection connection = DBConnection.getConnection();
            String query = "SELECT * FROM Member WHERE status='pending'";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Member member = new Member();
                member.setMember_id(resultSet.getInt("member_id"));
                member.setFirst_name(resultSet.getString("first_name"));
                member.setLast_name(resultSet.getString("last_name"));
                member.setAddress(resultSet.getString("address"));
                member.setNic(resultSet.getString("nic"));
                member.setDate_of_registration(resultSet.getDate("date_of_registration"));
                member.setEmail(resultSet.getString("email"));
                member.setPhone_no(resultSet.getString("phone_no"));
                registers.add(member);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return registers;
    }

    public int ifAcceptMember(String memberID) {
        int rowsAffected = 0;
        try {
            Connection connection = DBConnection.getConnection();
            String query = "UPDATE Member SET status='accepted' WHERE member_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, memberID);
            if (preparedStatement.execute()) {
                rowsAffected++;
            }

        } catch (SQLException ex) {
            Logger.getLogger(QueryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowsAffected;
    }

    public int ifRejectMember(String memberID) {
        int rowsAffected = 0;
        try {
            Connection connection = DBConnection.getConnection();
            String query = "UPDATE Claim SET status='rejected' WHERE member_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, memberID);
            if (preparedStatement.execute()) {
                rowsAffected++;
            }

        } catch (SQLException ex) {
            Logger.getLogger(QueryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowsAffected;
    }

    public int addMemberPayment(String memberID, int amount) {
        int rowsAffected = 0;
        try {
            Connection connection = DBConnection.getConnection();
            String query = "INSERT INTO payment(member_id, amount) VALUES(?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, memberID);
            preparedStatement.setInt(2, amount);
            if (preparedStatement.execute()) {
                rowsAffected++;
            }

        } catch (SQLException ex) {
            Logger.getLogger(QueryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowsAffected;
    }

    public int getMembershipFee(String vehicleType) {
        int amount = 0;
        try {
            Connection connection = DBConnection.getConnection();
            String query = "SELECT charge_amount FROM membership_fee WHERE type=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, vehicleType);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                amount = resultSet.getInt("charge_amount");
            }

        } catch (SQLException ex) {
            Logger.getLogger(QueryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amount;
    }

    public int getUserId(String username) {
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

    public String getVehicleType(int memberID) {
        String type = null;
        try {
            Connection connection = DBConnection.getConnection();
            String query = "SELECT vehicle_type FROM Vehicle WHERE member_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, memberID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                type = resultSet.getString("vehicle_type");
            }

        } catch (SQLException ex) {
            Logger.getLogger(QueryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return type;
    }

    public int getPayableAmount(int memberID) {
        int amount = 0;
        try {
            Connection connection = DBConnection.getConnection();
            String query = "SELECT amount FROM payment WHERE member_id=? AND status='not_paid'";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, memberID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                amount = resultSet.getInt("amount");
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amount;
    }

    public List<Member> getNotPayedMembers() {
        List<Member> payments = new ArrayList<>();
        try {
            Connection connection = DBConnection.getConnection();
            String query = "SELECT m.first_name, m.last_name, m.email, m.date_of_registration, m.phone_no, p.amount, p.member_id \n"
                    + "FROM Member m, payment p \n"
                    + "WHERE p.member_id = m.member_id AND m.status = 'accepted'";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Member member = new Member();
                member.setMember_id(resultSet.getInt("member_id"));
                member.setFirst_name(resultSet.getString("first_name"));
                member.setLast_name(resultSet.getString("last_name"));
                member.setDate_of_registration(resultSet.getDate("date_of_registration"));
                member.setEmail(resultSet.getString("email"));
                member.setPhone_no(resultSet.getString("phone_no"));
                member.setAmount(resultSet.getInt("amount"));
                payments.add(member);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return payments;
    }

    public int ifSuspendMember(String memberID) {
        int rowsAffected = 0;
        try {
            Connection connection = DBConnection.getConnection();
            String query = "UPDATE Member SET status='suspended' WHERE member_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, memberID);
            if (preparedStatement.execute()) {
                rowsAffected++;
            }

        } catch (SQLException ex) {
            Logger.getLogger(QueryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowsAffected;
    }

    public int paymentGateway(int memberID) {
        int rowsAffected = 0;
        try {
            Connection connection = DBConnection.getConnection();
            String query = "UPDATE payment SET status='paid', payment_date=? WHERE member_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDate(1, Date.valueOf(LocalDate.now()));
            preparedStatement.setInt(2, memberID);
            if (preparedStatement.execute()) {
                rowsAffected++;
            }

        } catch (SQLException ex) {
            Logger.getLogger(QueryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowsAffected;
    }

    public List<Member> getNewClaims() {
        List<Member> claims = new ArrayList<>();
        try {
            Connection connection = DBConnection.getConnection();
            String query = "SELECT m.first_name, m.last_name,\n"
                    + " c.claim_date, c.claim_amount, c.description, c.incident_date, c.quotation_place, c.member_id \n"
                    + "FROM Member m, Claim c WHERE m.member_id = c.member_id AND c.status='pending'";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Member member = new Member();
                member.setMember_id(resultSet.getInt("member_id"));
                member.setFirst_name(resultSet.getString("first_name"));
                member.setLast_name(resultSet.getString("last_name"));
                member.setClaim_date(resultSet.getDate("claim_date"));
                member.setClaim_amount(resultSet.getInt("claim_amount"));
                member.setClaim_description(resultSet.getString("description"));
                member.setIncident_date(resultSet.getDate("incident_date"));
                member.setQuotation_place(resultSet.getString("quotation_place"));
                claims.add(member);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return claims;
    }

    public int ApproveClaim(String memberID) {
        int rowsAffected = 0;
        try {
            Connection connection = DBConnection.getConnection();
            String query = "UPDATE Claim SET status='approved', claim_count=claim_count+1 WHERE member_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, memberID);
            if (preparedStatement.execute()) {
                rowsAffected++;
            }

        } catch (SQLException ex) {
            Logger.getLogger(QueryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowsAffected;
    }

    public int RejectClaim(int memberID) {
        int rowsAffected = 0;
        try {
            Connection connection = DBConnection.getConnection();
            String query = "UPDATE Claim SET status='rejected' WHERE member_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, memberID);
            if (preparedStatement.execute()) {
                rowsAffected++;
            }

        } catch (SQLException ex) {
            Logger.getLogger(QueryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowsAffected;
    }

    public List<Member> getClaimStatus(String memberID) {
        List<Member> status = new ArrayList<>();
        try {
            Connection connection = DBConnection.getConnection();
            String query = "SELECT claim_date, claim_amount, description, incident_date, quotation_place, member_id, status\n"
                    + "FROM Claim WHERE member_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, memberID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Member member = new Member();
                member.setMember_id(resultSet.getInt("member_id"));
                member.setStatus(resultSet.getString("status"));
                member.setClaim_date(resultSet.getDate("claim_date"));
                member.setClaim_amount(resultSet.getInt("claim_amount"));
                member.setClaim_description(resultSet.getString("description"));
                member.setIncident_date(resultSet.getDate("incident_date"));
                member.setQuotation_place(resultSet.getString("quotation_place"));
                status.add(member);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }

    public int cancelRequestedClaim(String memberID) {
        int rowsAffected = 0;
        try {
            Connection connection = DBConnection.getConnection();
            String query = "DELETE FROM `Claim` WHERE member_id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, memberID);
            if (preparedStatement.execute()) {
                rowsAffected++;
            }

        } catch (SQLException ex) {
            Logger.getLogger(QueryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowsAffected;
    }
}
