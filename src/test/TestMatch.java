package test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Title test
 * @date 2021/6/29
 * @autor Zsy
 */

class TestMatch {

    public static String s = "个人测试2||998000100389265/5901202104019195&&&";
    public static String s1 = "111111111/012345678901234&&&";

    // Pattern pattern = Pattern.compile("([\\u4e00-\\u9fa5]{1,3})([a-z]{1,3})([0-9]{6})([\\u4e00-\\u9fa5]{1,6})");

    public static void main(String[] args) {
        String regex = "/";
        System.out.println("/".matches(regex));

        String rgex = "/([0-9]{15})&&&";

        System.out.println(s1.matches(rgex));

        Pattern pattern = Pattern.compile(rgex); // 匹配的模式
        Matcher m = pattern.matcher(s1);

        System.out.println("几组; "+m.groupCount());
        while (m.find()){
            String group = m.group();
            System.out.println(group);
        }
    }

    public void test(){
        String regex = "/";
        System.out.println("/".matches(regex));

        String rgex = "abc([0-9]{15})efg";

        System.out.println(s1.matches(rgex));

        Pattern pattern = Pattern.compile(rgex); // 匹配的模式
        Matcher m = pattern.matcher(s1);

        System.out.println("几组; "+m.groupCount());
        while (m.find()){
            String group = m.group();
            System.out.println(group);
        }
    }

}
