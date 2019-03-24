/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insurance.webapp.Utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 *
 * @author crazydude
 */
public class DateUtil {

    public static String DATE_FORMAT = "yyyy-MM-dd";
    public static String TIME_FORMAT = "HH:mm";
    public static String TIME_FORMAT_NATURAL = "Ha";
    public static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";
    public static String DATE_TIME_NATURAL_FORMAT = "yyyy-MM-dd ha";
    public static String DATE_TIME_FB = "yyyy-MM-dd'T'HH:mm:ss'+'ssss";

    public static Date stringToDate(String date, String format) throws ParseException {
        DateFormat dformat = new SimpleDateFormat(format);
        Date d = dformat.parse(date);
        return d;
    }

    public static Date convertToDate(String date, String format) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        Date date1 = (Date) formatter.parse(date);
        return date1;
    }  
    
    public static String dateToString(Date date, String format) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String format1 = formatter.format(date);
        return format1;
    }

    public static Date getStartTimeOfTheDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0); // set hour to midnight
        cal.set(Calendar.MINUTE, 0); // set minute in hour
        cal.set(Calendar.SECOND, 0); // set second in minute

        return new Date(cal.getTimeInMillis());
    }

    public static Date getEndTimeOfTheDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23); // set hour to midnight
        cal.set(Calendar.MINUTE, 59); // set minute in hour
        cal.set(Calendar.SECOND, 59); // set second in minute

        return new Date(cal.getTimeInMillis());
    }

}
