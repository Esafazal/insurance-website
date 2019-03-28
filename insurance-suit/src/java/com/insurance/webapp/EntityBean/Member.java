/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insurance.webapp.EntityBean;

import java.util.Date;



/**
 *
 * @author crazydude
 */
public class Member {
    
    private int member_id;
    private String first_name;
    private String last_name;
    private String address;
    private Date dob;
    private String nic;
    private Date date_of_registration;
    private String email;
    private String phone_no;
    private String username;
    private String password;
    private int claim_id;
    private Date claim_date;
    private Date incident_date;
    private int claim_amount;
    private String claim_description;
    private String quotation_place;
    private String claim_vehicle_number;
    private String membership_id;

//    public Member(String first_name, String last_name, String address, Date dob, String nic, Date date_of_registration, String email, String phone_no, String username, String password) {
//        this.first_name = first_name;
//        this.last_name = last_name;
//        this.address = address;
//        this.dob = dob;
//        this.nic = nic;
//        this.date_of_registration = date_of_registration;
//        this.email = email;
//        this.phone_no = phone_no;
//        this.username = username;
//        this.password = password;
//    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public Date getDate_of_registration() {
        return date_of_registration;
    }

    public void setDate_of_registration(Date date_of_registration) {
        this.date_of_registration = date_of_registration;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getClaim_id() {
        return claim_id;
    }

    public void setClaim_id(int claim_id) {
        this.claim_id = claim_id;
    }

    public Date getClaim_date() {
        return claim_date;
    }

    public void setClaim_date(Date claim_date) {
        this.claim_date = claim_date;
    }

    public Date getIncident_date() {
        return incident_date;
    }

    public void setIncident_date(Date incident_date) {
        this.incident_date = incident_date;
    }

    public int getClaim_amount() {
        return claim_amount;
    }

    public void setClaim_amount(int claim_amount) {
        this.claim_amount = claim_amount;
    }

    public String getClaim_description() {
        return claim_description;
    }

    public void setClaim_description(String claim_description) {
        this.claim_description = claim_description;
    }

    public String getQuotation_place() {
        return quotation_place;
    }

    public void setQuotation_place(String quotation_place) {
        this.quotation_place = quotation_place;
    }

    public String getClaim_vehicle_number() {
        return claim_vehicle_number;
    }

    public void setClaim_vehicle_number(String claim_vehicle_number) {
        this.claim_vehicle_number = claim_vehicle_number;
    }

    public String getMembership_id() {
        return membership_id;
    }

    public void setMembership_id(String membership_id) {
        this.membership_id = membership_id;
    }

    
    
}
