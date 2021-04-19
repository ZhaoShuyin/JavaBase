package com.zsy.java.zsy.date;

/**
 * @Title com.wx.dm.util.date
 * @date 2020/6/19
 * @autor Zsy
 */
public class Month {

    public String monthName;     //当天日期名称
    public long start;         //最早毫秒值
    public long end;           //最后毫秒值

    public Object object;

    @Override
    public String toString() {
        return "{" + monthName + ", " + start + "," + end + "}";
    }

}
