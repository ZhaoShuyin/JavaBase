package com.zsy.test;


import org.kxml2.io.KXmlParser;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xmlpull.v1.XmlPullParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * @Title com.pengyang.ecg.utils.xml
 * @date 2019/11/4
 * @autor Zsy
 */

public class XmlUtil {


    public static void main(String[] args) {
        try {
            EcgBean parse = parse(new File("D:/abc/bean.xml"), 3);
            System.out.println(parse);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static List<String> TAGS = null;


    private static void init() {
        TAGS = new ArrayList<>();
        TAGS.add(EcgBean.TAG_I);
        TAGS.add(EcgBean.TAG_II);
        TAGS.add(EcgBean.TAG_III);
        TAGS.add(EcgBean.TAG_AVR);
        TAGS.add(EcgBean.TAG_AVL);
        TAGS.add(EcgBean.TAG_AVF);
        TAGS.add(EcgBean.TAG_V1);
        TAGS.add(EcgBean.TAG_V2);
        TAGS.add(EcgBean.TAG_V3);
        TAGS.add(EcgBean.TAG_V4);
        TAGS.add(EcgBean.TAG_V5);
        TAGS.add(EcgBean.TAG_V6);
    }

    /**
     * @param file
     * @param unitSize 显示时每个单元格的像素个数(每 mm 的数据个数)
     * @return
     * @throws Exception
     */
    public static EcgBean parse(File file, int unitSize) throws Exception {
        if (TAGS == null || TAGS.size() == 0) {
            init();
        }
        EcgBean ecgBean = new EcgBean();
        InputStream inputStream = new FileInputStream(file);
        XmlPullParser parser = new KXmlParser();
        parser.setInput(inputStream, "UTF-8");
        String mark = null;
        int eventType = parser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_TAG) {
                String name = parser.getName();
                //开始时间
                if (ecgBean.low == 0 && name.equals("low")) {
                    String low = parser.nextText();
                    ecgBean.low = Float.valueOf(low);
                }
                //结束时间
                if (ecgBean.high == 0 && name.equals("high")) {
                    String high = parser.nextText();
                    ecgBean.high = Float.valueOf(high);
                    ecgBean.duration = (int) (ecgBean.high - ecgBean.low);
                }

                if (name.equals("digits") && TAGS.contains(mark)) {
                    String s = parser.nextText();
                    setValue(mark, s, unitSize, ecgBean);
                    mark = null;
                }
                if (name.equals("code")) {
                    String value = parser.getAttributeValue(0);
                    mark = value;
                }
            }
            eventType = parser.next();
        }
        return ecgBean;
    }

    /**
     * 赋值
     */
    private static void setValue(String mark, String content, int unitSize, EcgBean ecgBean) {
        String[] split = content.split(" ");
        //每秒数据个数
        float unitCount = split.length / ecgBean.duration;
        //1秒25个单元格,每秒显示的数据个数
        float secondsSize = unitCount * 25f;
        //缩放比例
        float scale = unitCount / secondsSize;

        int[] ints = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            ints[i] = Integer.parseInt(split[i]);
        }
        switch (mark) {
            case EcgBean.TAG_I:
                ecgBean.I = conver(ints, scale);
                break;
            case EcgBean.TAG_II:
                ecgBean.II = conver(ints, scale);
                break;
            case EcgBean.TAG_III:
                ecgBean.III = conver(ints, scale);
                break;
            case EcgBean.TAG_AVR:
                ecgBean.AVR = conver(ints, scale);
                break;
            case EcgBean.TAG_AVL:
                ecgBean.AVL = conver(ints, scale);
                break;
            case EcgBean.TAG_AVF:
                ecgBean.AVF = conver(ints, scale);
                break;
            case EcgBean.TAG_V1:
                ecgBean.V1 = conver(ints, scale);
                break;
            case EcgBean.TAG_V2:
                ecgBean.V2 = conver(ints, scale);
                break;
            case EcgBean.TAG_V3:
                ecgBean.V3 = conver(ints, scale);
                break;
            case EcgBean.TAG_V4:
                ecgBean.V4 = conver(ints, scale);
                break;
            case EcgBean.TAG_V5:
                ecgBean.V5 = conver(ints, scale);
                break;
            case EcgBean.TAG_V6:
                ecgBean.V6 = conver(ints, scale);
                break;
        }
    }

    private static int[] conver(int[] origin, float scale) {
        if (scale == 1 || scale <= 0) {
            return origin;
        }
        int number = (int) (origin.length / scale);
        int[] ints = new int[number];
        for (int i = 0; i < number; i++) {
            ints[i] = origin[(int) (i * scale)];
        }
        return ints;
        /*if (scale < 1) { //对数据进行扩
            return enlarge(origin, scale);
        } else {        //对数据进行缩
            return shrink(origin, scale);
        }*/
    }

    /**
     * 收缩
     */
    private static int[] shrink(int[] origin, float scale) {
        int number = (int) (origin.length / scale);
        int[] ints = new int[number];
        for (int i = 0; i < ints.length; i++) {
        }
        return ints;
    }

    /**
     * 扩大
     */
    private static int[] enlarge(int[] origin, float scale) {

        return null;
    }


    private static int getTotal(int[] ints, int start, int number) {
        int total = 0;
        for (int i = 0; i < number; i++) {
            total += ints[i + start * number];
        }
        return total / number;
    }

}
