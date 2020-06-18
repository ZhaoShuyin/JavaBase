package com.zsy.java.regex;

import java.util.regex.Pattern;

/**
 * @Title com.zsy.java.regex
 * @date 2020/1/17
 * @autor Zsy
 */

public class RegexDemo {

    public static void main(String[] args) {
       /* String content = "I am noob " +
                "from runoob.com.";

        String pattern = ".*runoob.*";

        boolean isMatch = Pattern.matches(pattern, content);
        System.out.println("字符串中是否包含了 'runoob' 子字符串? " + isMatch);*/

//        System.out.println(Pattern.matches("abc@1","/^[A-Za-z0-9-]*$/g"));

            String s = "123456";
        System.out.println(s.substring(0,s.length()-1));

//        custom();
    }

    private static void custom(){
        String s = null;
        String regex = "^[A-Za-z0-9]+$";
//        boolean flag = s.matches(regex);//判断功能
//        System.out.println("flag:"+flag);
        System.out.println(Pattern.matches(regex,s));
    }

    /**
     * 手机号校验
     */
    private static void phone(){
        String s = "18610245555";
        String regex = "1[38]\\d{9}";//定义手机好规则
        boolean flag = s.matches(regex);//判断功能
        System.out.println("flag:"+flag);
    }

}
