package com.zsy.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Title com.pengyang.ecg.utils.date
 * @date 2019/12/10
 * @autor Zsy
 */

public class DateUtil {

    public static void main(String[] args) {

    }

    private static SimpleDateFormat formatT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    private static SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat formatCalender = new SimpleDateFormat("yyyy-MM-dd");
    ;

    private static Calendar calendar = Calendar.getInstance();


    public static String getDate() {
        return formatDate.format(new Date());
    }

    public static String getCalendar() {
        return formatCalender.format(new Date());
    }

    public static String turnDate(String date) {
        try {
            Date parse = formatT.parse(date);   //申请会诊日期
            return formatDate.format(parse);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getAge(String brithday) {
        try {
            Date parse = formatT.parse(brithday);
            long l = new Date().getTime() - parse.getTime();
            return String.valueOf(l / 365 / 24 / 3600 / 1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "0";
    }


    public static String getBrithday(String brithday) {
        try {
            Date parse = formatT.parse(brithday);
            return formatCalender.format(parse);       //转换为yyyy-MM-dd格式
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static String getBrithday(int year, int month, int day) {
        month += 1;
        return year + "-" + (month < 10 ? "0" + month : month) + "-" + (day < 10 ? "0" + day : day);
    }

}
