package com.zsy.test;

import org.apache.commons.lang.StringEscapeUtils;

/**
 * @Title com.zsy.test
 * @date 2020/1/13
 * @autor Zsy
 */

class Test17 {

    public static void main(String[] args) {
        String string = "\"{\"Status\":\"1\"}\"";
        String string2 = "{\"Status\":\"1\"}";
/*
        String result = StringEscapeUtils.unescapeJava(string);
        if (result.startsWith("\"")&result.endsWith("\"")) {
            result = string.substring(1, result.length());
        }
        System.out.println("result: " + result);*/
        System.out.println( "string: "+string );
        String result = StringEscapeUtils.unescapeJava(string);
        System.out.println("result1: "+result);
        if (result.startsWith("\"")&result.endsWith("\"")) {
            result = string.substring(1, result.length()-1);
        }
        System.out.println( "result2: "+result );
    }

}
