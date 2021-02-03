package com.zsy.java.zsy;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;

/**
 * @Title 日期工具类
 */
public class DateUtil {

    /**
     * 查询"yyyyMMdd"格式的当前日期
     */
    public static String getQuery() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(12, -1);
        Date time = calendar.getTime();
        return queryFormat.format(time);
//        return queryFormat.format(new Date());
    }

    private static long HOUR_MILLIS = 1000 * 60 * 60;
    private static long DAY_MILLIS = 1000 * 60 * 60 * 24;
    private static long MONTH_MILLIS = 1000 * 60 * 60 * 24 * 30;

    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static DateFormat calenderFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static DateFormat queryFormat = new SimpleDateFormat("yyyyMMddHHmm");

    private static ZoneOffset offset = ZoneOffset.ofHours(8);
    private static DateTimeFormatter formatter_date = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static DateTimeFormatter formatter_calendar = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static DateTimeFormatter formatter_time = DateTimeFormatter.ofPattern("HH:mm:ss");

    private static String regex_date = "^[0-9]{4}-[0-9]{2}-[0-9]{2}[\\u3000|\\u0020|\\u00A0][0-9]{2}:[0-9]{2}:[0-9]{2}$";
    private static String regex_calendar = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$";
    private static String regex_time = "^[0-9]{2}:[0-9]{2}:[0-9]{2}$";

    public static boolean verify(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        return s.matches(regex_date) || s.matches(regex_calendar);
    }

    public static String turnDate(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        if (s.matches(regex_date)) {
            return s;
        }
        if (s.matches(regex_calendar)) {
            return s + " 00:00:00";
        }
        return null;
    }


    public static String turnCalendar(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        if (s.matches(regex_date)) {
            return s.substring(0, 10);
        }
        if (s.matches(regex_calendar)) {
            return s;
        }
        return null;
    }

    //获取某天最早毫秒值
    public static long getDayFristMs(String s) {
        String turn = turnCalendar(s);
        if (turn == null) {
            return -1;
        }
        return LocalDateTime.of(LocalDate.parse(turn, formatter_calendar), LocalTime.MIN).toInstant(offset).toEpochMilli();
    }

    //获取某天最后毫秒值
    public static long getDayLastMs(String s) {
        String turn = turnCalendar(s);
        if (turn == null) {
            return -1;
        }
        return LocalDateTime.of(LocalDate.parse(turn, formatter_calendar), LocalTime.MAX).toInstant(offset).toEpochMilli();
    }

    public static long turnMs(String date) {
        if (date == null || date.length() == 0) {
            return -1;
        }
        if (date.matches(regex_date)) {
            return turnMsB(date);
        }
        if (date.matches(regex_calendar)) {
            return turnMsB(date + " 00:00:00");
        }
        return 0;
    }

    /**
     * 文字转为毫秒
     */
    private static long turnMsA(String date) {
        try {
            long time = dateFormat.parse(date).getTime();
            return time;
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private static long turnMsB(String date) {
        return LocalDateTime.parse(date, formatter_date).toInstant(offset).toEpochMilli();
    }

    public static String turnText(long ms) {
        if (ms <= 0) {
            return "1970-01-01 00:00:00";
        }
        return turnTextB(ms);
    }

    /**
     * 毫秒转为文字
     */
    public static String turnTextA(long ms) {
        String format = dateFormat.format(new Date(ms));
        return format;
    }

    private static String turnTextB(long ms) {
        return LocalDateTime
                .ofInstant(Instant.ofEpochMilli(ms), offset)
                .format(formatter_date);
    }

    /**
     * 获取某天最早毫秒值
     *
     * @return
     */
    public static long getTodayFristMs() {
        return getTodayFristMsA();
    }

    private static long getTodayFristMsA() {
        try {
            String format = calenderFormat.format(new Date());
            System.out.println(format);
            return calenderFormat.parse(format).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private static long getTodayFristMsB() {
        return LocalDateTime.of(LocalDate.now(), LocalTime.MIN).toInstant(offset).toEpochMilli();
    }

    /**
     * 获取某天最后毫秒值
     */
    public static long getTodayLastMs() {
        return getTodayLastMsB();
    }

    private static long getTodayLastMsA() {
        try {
            String format = calenderFormat.format(new Date());
            System.out.println(format);
            return calenderFormat.parse(format).getTime() + DAY_MILLIS - 1;
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private static long getTodayLastMsB() {
        return LocalDateTime.of(LocalDate.now(), LocalTime.MAX).toInstant(offset).toEpochMilli();
    }

    public static String getDate() {
        return dateFormat.format(new Date());
    }

    public static String getDateA() {
        return dateFormat.format(new Date());
    }

    public static String getDateB() {
        return LocalDateTime.now().format(formatter_date);
    }

    public static String getCalendar() {
        return calenderFormat.format(new Date());
    }


    /**
     * 计算两日期差值天数
     */
    public static int getDifDays(String start, String end) {
        if (!verify(start) || !verify(end)) {
            return 0;
        }
        long dif = LocalDate.parse(turnCalendar(end), formatter_calendar).toEpochDay()
                - LocalDate.parse(turnCalendar(start), formatter_calendar).toEpochDay();
        return (int) dif;
    }

    public static Day[] getDays(String start, int number) {
        Day[] days = new Day[number];
        days[0] = new Day();
        days[0].dayName = start;
        days[0].start = getDayFristMs(start);
        days[0].end = days[0].start + DAY_MILLIS;
        for (int i = 1; i < number; i++) {
            days[i] = new Day();
            days[i].start = days[i - 1].end;
            days[i].end = days[i].start + DAY_MILLIS;
            days[i].dayName = turnCalendar(turnText(days[i].start));
        }
        return days;
    }

    public static long current() {
//        return LocalDateTime.now().toEpochSecond(offset);
        return LocalDateTime.now().toInstant(offset).toEpochMilli();
    }

    public static void main(String[] args) {
        /*long l = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
//            System.out.println(turnMs("2020-01-01 00:00:00"));
//            System.out.println(turnMs("2020-01-01"));
//            System.out.println(turnTextA(1577808000000l));//11ms
//            System.out.println(turnTextB(1577808000000l));//24ms
//            System.out.println(getTodayFristMsA());       //97ms
//            System.out.println(getTodayFristMsB());       //55ms
//            System.out.println(getTodayLastMsA());        //117ms
//            System.out.println(getTodayLastMsB());        //42ms
//            getDateA();                                   //15ms
//            getDateB();                                   //31ms
        }
        System.out.println(System.currentTimeMillis() - l);*/

//        long l = LocalDate.parse(turnCalendar("2020-01-03"), formatter_calendar).toEpochDay();
//        long l1 = LocalDate.parse(turnCalendar("2020-01-01"), formatter_calendar).toEpochDay();
//        System.out.println(l);
//        System.out.println(l1);

//        System.out.println(turnMsB("2020-01-01 01:00:00"));
//        System.out.println(turnMsB("2020-01-02 01:00:00"));
//        System.out.println(turnMsB("2020-01-03 01:00:00"));
//        System.out.println(turnMsB("2020-01-04 01:00:00"));
//        System.out.println(turnMsB("2020-01-05 01:00:00"));

       /* for (int i = 0; i < 6; i++) {
            LocalDate today = LocalDate.now().minusMonths(i);
            //本月的第一天
            LocalDate firstday = LocalDate.of(today.getYear(), today.getMonth(), 1);
            //本月的最后一天
            LocalDate lastDay = today.with(TemporalAdjusters.lastDayOfMonth());

            System.out.println(firstday.getMonth().getValue() + "月");
            System.out.println("最后一天：" + lastDay.getDayOfMonth());
            System.out.println("-------------------");
        }*/
//        System.out.println(Arrays.toString(getMonths(2)));

//        Calendar calendar = Calendar.getInstance();
////        Calendar calendar1 = queryFormat.getCalendar();
//        calendar.add(12, -1);
//        Date time = calendar.getTime();
//        String format = queryFormat.format(time);
//        System.out.println(format);
////        return df.format(calendar.getTime());


//        System.out.println(turnText(1597754844051l));

       /* long todayFristMs = getTodayFristMs();
        System.out.println("todayFristMs " + todayFristMs);

        System.out.println(turnText(1599313371000l)); //131538695341  1599215287000  202009041828

        System.out.println(System.currentTimeMillis());

        System.out.println(DateUtil.turnText(1599313371000l));*/

        long millis = monthFistMilles();
        System.out.println(millis);
        System.out.println(turnText(millis));
    }



    public static long monthFistMilles() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        return calendar.getTimeInMillis();
    }

    /**
     * @param numebr
     * @return
     */
    public static Month[] getMonths(int numebr) {
        Month[] months = new Month[numebr];
        for (int i = 0; i < numebr; i++) {
            LocalDate today = LocalDate.now().minusMonths(i);
            LocalDate firstday = LocalDate.of(today.getYear(), today.getMonth(), 1);
            LocalDate lastDay = today.with(TemporalAdjusters.lastDayOfMonth());
            months[i] = new Month();
            months[i].monthName = today.toString().substring(0, 7);
            months[i].start = getDayFristMs(firstday.toString());
            months[i].end = getDayLastMs(lastDay.toString());
        }
        return months;
    }




}
