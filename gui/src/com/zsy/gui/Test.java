package com.zsy.gui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Title com.zsy.gui
 * @date 2021/7/6
 * @autor Zsy
 */

class Test {

    public static String string = "[{\"HeartBeat\":0,\"Position\":0},{\"HeartBeat\":1,\"Position\":1},{\"HeartBeat\":2,\"Position\":2},{\"HeartBeat\":3,\"Position\":3}," +
            "{\"HeartBeat\":4,\"Position\":4},{\"HeartBeat\":5,\"Position\":5},{\"HeartBeat\":6,\"Position\":6},{\"HeartBeat\":7,\"Position\":7}," +
            "{\"HeartBeat\":8,\"Position\":8},{\"HeartBeat\":9,\"Position\":9},{\"HeartBeat\":10,\"Position\":10},{\"HeartBeat\":11,\"Position\":11}]";


    public static void main(String[] args) {
        String rgex = "\"HeartBeat\":([0-9]{1,5}),\"Position\":([0-9]{1,5})";

        Pattern pattern = Pattern.compile(rgex); // 匹配的模式
        Matcher m = pattern.matcher(string);

        Pattern patternN = Pattern.compile("[0-9]{1,5}");

        while (m.find()) {
            String group = m.group();
//            System.out.println(group);
            String beat = group.substring(group.indexOf(":") + 1, group.indexOf("Position") - 2);
            String position = group.substring(group.lastIndexOf(":") + 1);
            System.out.println("(" + beat + ") , (" + position + ")");
        }

    }

}
