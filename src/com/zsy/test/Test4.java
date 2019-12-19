package com.zsy.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Title com.zsy.test
 * @date 2019/12/9
 * @autor Zsy
 */

class Test4 {

    public static void main(String[] args) {
//        System.out.println(paramItem("123456"));
        System.out.println(getDate());
        System.out.println(getCalendar());

        System.out.println(getBrithday(10));
        System.out.println(getAge(1999, 1, 1));
    }

    public static String paramItem(String examUid) {
        return "{\"Token\":null,\"Command\":\"E00041\",\"Param\":{\"Type\":\"PatientInfo\",\"EXAM_UID\":\"" + examUid + "\"},\"RequestModel\":null}";
    }

    public static String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date());
    }

    public static String getCalendar() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(new Date());
    }


    public static String getBrithday(int age) {
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.YEAR, -age);
        return ca.get(Calendar.YEAR) + "-" + (ca.get(Calendar.MONTH) + 1) + "-" + ca.get(Calendar.DAY_OF_MONTH);
    }


    public static String getAge(int year, int month, int day) {
        Calendar ca = Calendar.getInstance();
        Calendar ca1 = Calendar.getInstance();
        ca1.set(year, month, day);
        int i = ca.compareTo(ca1);
        return String.valueOf(i);
    }


    public static int getsdays(Calendar a,Calendar b) {
        if(b.after(a)) {
            Calendar temp;
            temp=a;
            a=b;
            b=temp;
        }
        int days=a.get(Calendar.DAY_OF_YEAR)-b.get(Calendar.DAY_OF_YEAR);
        if(a.get(Calendar.YEAR)!=b.get(Calendar.YEAR)) {
            do {
                days+=a.getActualMaximum(Calendar.DAY_OF_YEAR);
                a.add(Calendar.YEAR, 1);
            }
            while(a.get(Calendar.YEAR)!=b.get(Calendar.YEAR));
        }
        return days;
    }
}
