package com.zsy.java.other;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @Title com.zsy.java.other
 * @Date 2019/10/8
 * @Autor Zsy
 */
public class Test {


    public static void main(String[] args) {
        /*short[] shorts = new short[]{1,2,3,4,5};
        System.out.println(Arrays.toString(shorts));*/

       /*SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       long time = System.currentTimeMillis();
        String str = format.format(time);
        System.out.println(str);*/


//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");//设置日期格式
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
    }

}
