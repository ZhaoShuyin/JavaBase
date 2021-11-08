package com.zsy.ecg;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Title com.zsy.ecg
 * @date 2021/8/7
 * @autor Zsy
 */

class Test3 {

    public static void main(String[] args) {
        String conclusion = "1\n2\n\n3\n";
//        Pattern p = Pattern.compile("\\s*|\t|\r|\n");
        Pattern p = Pattern.compile("^[2]{1}$");
        Matcher m = p.matcher(conclusion);
        String result = m.replaceAll("");
        System.out.println(result);
    }

}
