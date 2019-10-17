package com.zsy.java.net;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

/**
 * @Title com.zsy.java
 * @Date 2019/10/15
 * @Autor Zsy
 */
public class NetTest {


    public static String BOUNDARY = UUID.randomUUID().toString(); // 边界标识 随机生成


    public static void main(String[] args) {
        new Thread(new UpLoad()).start();
    }

    public static class UpLoad implements Runnable {
        @Override
        public void run() {
            try {
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                String path = "http://192.168.0.52:8083/Api/UploadFileInfo";
                URL url = new URL(path);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Charset", "UTF-8"); // 设置编码
                conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + BOUNDARY);
                conn.setDoOutput(true);//设置打开输出流
                conn.setDoInput(true); //允许输入流
                //拿到输出流
                DataOutputStream dataOutputStream = new DataOutputStream(conn.getOutputStream());
                StringBuffer sb = new StringBuffer();
                setMessagevalue(sb, "hospital", "安贞医院");
                setMessagevalue(sb, "patientName", "张三");
                setMessagevalue(sb, "Brithday", "1970-01-01");
                setMessagevalue(sb, "CreateDate", "2019-10-10");
                setMessagevalue(sb, "gender", "M");
                setMessagevalue(sb, "Age", "11");


                sb.append("--");
                sb.append(BOUNDARY);
                sb.append("\r\n");
                sb.append("Content-Disposition: form-data; name=\"uploadfile\"; filename=\""
                        + "ECGL.xml" + "\"" + "\r\n");
                dataOutputStream.write(sb.toString().getBytes());

                stream.write(sb.toString().getBytes());

                File uploadFile = new File("D:/abc/BECG.xml");

                FileInputStream fStream = new FileInputStream(uploadFile);
                if (uploadFile.isFile()) {
                    int bufferSize = 1024;
                    byte[] buffer = new byte[bufferSize];
                    int length = -1;
                    while ((length = fStream.read(buffer)) != -1) {
                        dataOutputStream.write(buffer, 0, length);
                    }
                    stream.write("文件\n".getBytes());
                    fStream.close();
                    dataOutputStream.write("\r\n".getBytes());
                    stream.write("\r\n".getBytes());
                    byte[] end_data = ("--" + BOUNDARY + "--" + "\r\n").getBytes();
                    dataOutputStream.write(end_data);
                    stream.write(end_data);
                    dataOutputStream.flush();

                    System.out.println(">>>>>>>\n" + new String(stream.toByteArray()));

                }
                if (conn.getResponseCode() == 200) {
                    InputStream is = conn.getInputStream();
                    String text = getTextFromStream(is);
                    System.out.println(">>>>>>>>>>>响应结果:text : " + text);
                }else{
                    System.out.println(""+conn.getResponseCode());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static void setMessagevalue(StringBuffer sb, String key, String value) {
        sb.append("--").append(BOUNDARY).append("\r\n");//分界符
        sb.append("Content-Disposition: form-data; name=\"" + key + "\"" + "\r\n");
//        sb.append("Content-Type: text/plain; charset=" + "UTF-8" + "\r\n");
//        sb.append("Content-Transfer-Encoding: 8bit" + "\r\n");
        sb.append("\r\n");
        sb.append(value);
        sb.append("\r\n");//换行！
    }


    public static String getTextFromStream(InputStream is) {
        byte[] b = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            while ((len = is.read(b)) != -1) {
                bos.write(b, 0, len);
            }
            String text = new String(bos.toByteArray());
            return text;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}


