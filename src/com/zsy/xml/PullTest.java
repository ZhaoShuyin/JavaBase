package com.zsy.xml;

//import org.kxml2.io.KXmlParser;

import org.kxml2.io.KXmlParser;
import org.xmlpull.v1.XmlPullParser;
import sun.rmi.runtime.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @Title 使用固定jar包(android - kxml2.jar)
 * Pull解析
 * @Date 2019/11/1
 * @Autor Zsy
 */
public class PullTest {

    public static void main(String[] args) {
        try {
            String pull = Pull(new File("D:/abc/25mms.xml"), "MDC_ECG_LEAD_I");
//            System.out.println("\n解析结果: \n" + pull);
            String[] split = pull.split(" ");
            System.out.println("5秒: 25mm/s 数据个数 :"+split.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            String pull = Pull(new File("D:/abc/50mms.xml"), "MDC_ECG_LEAD_I");
            String[] split = pull.split(" ");
            System.out.println("5秒: 50mm/s 数据个数 :"+split.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String Pull(File file, String tag) throws Exception {
        InputStream inputStream = new FileInputStream(file);
        XmlPullParser parser = new KXmlParser();
        parser.setInput(inputStream, "UTF-8");
        boolean mark = false;
        int eventType = parser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            System.out.println("------------< " + eventType + " >------------");
            switch (eventType) {
                case XmlPullParser.START_DOCUMENT://文档开始
                    System.out.println("====================文档开始 name:" + parser.getName());
                    break;
                case XmlPullParser.START_TAG:
                    String name = parser.getName();
                    System.out.println(">>>节点开始 name:" + name + "/开始");
                    if (mark && name.equals("digits")) {
                        return parser.nextText();
                    }
                    if (name.equals("code") && tag.equals(parser.getAttributeValue(0))) {
                        System.out.println("+++++++++++++在此处理++++++++++++++++");
                        mark = true;
                    }
                    break;
                case XmlPullParser.END_TAG:
                    System.out.println(">>>节点结束 name:" + parser.getName() + " /结束");
                    break;
            }
            eventType = parser.next();
        }
        System.out.println("=====================文档结束 name:" + parser.getName());
        return null;
    }

}
