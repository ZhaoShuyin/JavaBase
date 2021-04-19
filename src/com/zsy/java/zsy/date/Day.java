package com.zsy.java.zsy.date;

/**
 *
 */
public class Day {

    public String dayName;     //当天日期名称
    public long start;         //最早毫秒值
    public long end;           //最后毫秒值

    public Object object;

    @Override
    public String toString() {
        return "{" + dayName + ", " + start + "," + end + "}";
    }
}
