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

    
    
}
