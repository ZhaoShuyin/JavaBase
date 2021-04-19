package test.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

/**
 * @Title com.zsy.test
 * @date 2020/4/2
 * @autor Zsy
 */

class Test18 {
    private static SimpleDateFormat formatT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    private static SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat formatCalender = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
        System.out.println(getBrithday(365, "天"));
        System.out.println(Arrays.toString(getNumber(2020, 2, 1)));
    }

    public static String getBrithday(int number, String unit) {
        Calendar calendar = Calendar.getInstance();
        switch (unit) {
            case "岁":
                calendar.add(Calendar.YEAR, -number);
                break;
            case "月":
                calendar.add(Calendar.MONTH, -number);
                break;
            case "天":
                calendar.add(Calendar.DAY_OF_YEAR, -number);
                break;
            default:
                return "";
        }
        Date time = calendar.getTime();
        return formatCalender.format(time);
    }

    public static int[] getNumber(int year, int month, int dayOfMonth) {
        Date parse = null;
        try {
            parse = formatCalender.parse(year + "-" + (month < 10 ? "0" + month : month) + "-" + (dayOfMonth < 10 ? "0" + dayOfMonth : dayOfMonth));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (parse == null) {
            return new int[]{0, 0, 0};
        }
        Date date = new Date();
        Calendar calendarA = Calendar.getInstance();
        Calendar calendarB = Calendar.getInstance();
        calendarA.setTime(parse);
        calendarB.setTime(date);
        int yearNum = calendarB.get(Calendar.YEAR) - calendarA.get(Calendar.YEAR);
        int monthNum = calendarB.get(Calendar.MONTH) - calendarA.get(Calendar.MONTH);
        int dayNum = calendarB.get(Calendar.DAY_OF_YEAR) - calendarA.get(Calendar.DAY_OF_YEAR);
        return new int[]{yearNum, monthNum, dayNum};
    }

}
