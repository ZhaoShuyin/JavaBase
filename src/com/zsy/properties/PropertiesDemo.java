package com.zsy.properties;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

/**
 * Title com.zsy.properties
 *
 * @author Zsy
 * @date 2019/8/10 14:33
 */
public class PropertiesDemo {

    public static void main(String[] args) {
        Properties prop = new Properties();

        //读取
        try {
            prop.load(new FileReader("info.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //获取对应的值
        String v1 = prop.getProperty("key1");
        String v2 = prop.getProperty("key2");
        System.out.println(v1 + " - " + v2);

        //写入,key值为唯一
        try {
            FileWriter fileWriter = new FileWriter("info.properties");
            prop.setProperty("key5", "value5");
            prop.store(fileWriter, "remark message");//只保存最新信息
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //遍历
        Set<Object> keySet = prop.keySet();
        for (Object o : keySet) {
            System.out.println("key==" + o);
        }
    }
}
