package com.zsy.java.net;

import java.io.*;
import java.util.UUID;

/**
 * @Title com.zsy.java
 * @Date 2019/10/15
 * @Autor Zsy
 */
public class Test {
    private static String BOUNDARY = UUID.randomUUID().toString();

    public static void main(String[] args) {
        File wfile = new File("D:/abc/wfile.txt");
        if (!wfile.exists()) {
            try {
                wfile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        File rfile = new File("D:/abc/BECG.xml");
        System.out.println("长度:" + rfile.length());

        StringBuffer sb = new StringBuffer();
        setMessagevalue(sb, "patientName", "张三");
        setMessagevalue(sb, "Brithday", "1970-1-1");
        setMessagevalue(sb, "CreateDate", "2019-10-15");
        setMessagevalue(sb, "gender", "M");
        setMessagevalue(sb, "Age", "25");
        setMessagevalue(sb, "hospital", "医院名称");

        sb.append("--");
        sb.append(BOUNDARY);
        sb.append("\r\n");
        sb.append("Content-Disposition: form-data; name=\"uploadfile\"; filename=\""
                + "ECGL.xml" + "\"" + "\r\n");
        sb.append("Content-Type: application/octet-stream; charset=" + "UTF-8" + "\r\n");
        sb.append("\r\n");

        DataOutputStream dataOutputStream = null;
        try {
            dataOutputStream = new DataOutputStream(new FileOutputStream(wfile));
            dataOutputStream.write(sb.toString().getBytes());
            FileInputStream fStream = new FileInputStream(rfile);
            /* 设置每次写入1024bytes */
            if (rfile.isFile()) {
                int bufferSize = 1024;
                byte[] buffer = new byte[bufferSize];
                int length = -1;
                while ((length = fStream.read(buffer)) != -1) {
                    dataOutputStream.write(buffer, 0, length);
                }
                fStream.close();
                dataOutputStream.write("\r\n".getBytes());
                byte[] end_data = ("--" + BOUNDARY + "--" + "\r\n").getBytes();
                dataOutputStream.write(end_data);
                dataOutputStream.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void setMessagevalue(StringBuffer sb, String key, String value) {
        sb.append("--").append(BOUNDARY).append("\r\n");//分界符
        sb.append("Content-Disposition: form-data; name=\"" + key + "\"" + "\r\n");
        sb.append("Content-Type: text/plain; charset=" + "UTF-8" + "\r\n");
        sb.append("Content-Transfer-Encoding: 8bit" + "\r\n");
        sb.append("\r\n");
        sb.append(value);
        sb.append("\r\n");//换行！
    }

}
