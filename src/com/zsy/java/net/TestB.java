package com.zsy.java.net;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Title com.zsy.java
 * @Date 2019/10/15
 * @Autor Zsy
 */
public class TestB {

    public static void main(String[] args) throws Exception {
        HttpURLConnection connection = (HttpURLConnection)new URL("http://www.baidu.com").openConnection();
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        OutputStream out = connection.getOutputStream();
        out.write("post传递的数据".getBytes());
        out.close();

        InputStream in = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line = null;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        in.close();

        if (connection != null) connection.disconnect();
        if (out != null) out.close();
        if (in != null) in.close();
    }
}
