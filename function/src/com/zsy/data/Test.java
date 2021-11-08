package com.zsy.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Title com.zsy.data
 * @date 2021/7/21
 * @autor Zsy
 */

public class Test {

    private static SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static Map<String, String> header = new HashMap<>();

    public static void main(String[] args) throws ParseException {
        String code = "py-ecg";
        String secret = "7nxQQmTtdt0=";
        Date date1 = new Date();
        String date = formatDate.format(date1);
        header.put("code", code);
        header.put("testTime", date);
        String s = code + secret + formatDate.parse(date).getTime();

        header.put("sign", DigestUtils.md5DigestAsHex(s.getBytes()));

        System.out.println(System.currentTimeMillis());
        System.out.println("拼接字符串    "+s);
        Iterator<Map.Entry<String, String>> iterator = header.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, String> next = iterator.next();
            System.out.println(next.getKey()+"  :  "+next.getValue());
        }
    }

}
