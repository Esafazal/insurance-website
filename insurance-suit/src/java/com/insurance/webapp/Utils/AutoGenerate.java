/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insurance.webapp.Utils;

import java.security.SecureRandom;

/**
 *
 * @author crazydude
 */
public class AutoGenerate {
    
    private static SecureRandom random = new SecureRandom();

  
    public static final String ALPHA_CAPS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String ALPHA = "abcdefghijklmnopqrstuvwxyz";
    public static final String NUMERIC = "0123456789";
    public static final String SPECIAL_CHARS = "!@#$%^&*_=+-/";


    public static String generateKey(int len, String dic) {
        String result = "";
        for (int i = 0; i < len; i++) {
            int index = random.nextInt(dic.length());
            result += dic.charAt(index);
        }
        return result;
    }
}

//        int len = 10;
//        String password = generatePassword(len, ALPHA_CAPS + ALPHA);
//        len = 20;
//        password = generatePassword(len, ALPHA_CAPS + ALPHA + SPECIAL_CHARS);
//        len = 15;
//        password = generatePassword(len, ALPHA_CAPS + ALPHA + SPECIAL_CHARS + NUMERIC);
