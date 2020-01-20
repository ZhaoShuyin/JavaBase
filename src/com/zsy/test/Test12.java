package com.zsy.test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

/**
 * @Title com.zsy.test
 * @date 2019/12/27
 * @autor Zsy
 */

class Test12 {
    public static void main(String[] args) {

        StringBuilder builder = new StringBuilder();

        InputStream inputStream = null;
        try {

            FileOutputStream fileOutputStream = new FileOutputStream("D:/abc/456.txt");
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");


            inputStream = new FileInputStream(new File("D:/abc/123.txt"));
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            int len;
            while ((len = inputStreamReader.read()) != -1) {
                outputStreamWriter.write(len);
            }
            inputStreamReader.close();

            outputStreamWriter.close();
            fileOutputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        String s = builder.toString();
        byte[] bytes = new byte[0];
        try {
            bytes = s.getBytes("UTF-8");
            System.out.println("123:   " + new String(bytes,"UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
