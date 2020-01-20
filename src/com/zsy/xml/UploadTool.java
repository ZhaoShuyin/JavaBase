package com.zsy.xml;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;


/**
 * @Title 上传工具类
 * @date 2019/10/14
 * @autor Zsy
 */
public class UploadTool {

    public static void main(String[] args) {
        upload("http://192.168.0.52:8083/API/UploadFileInfo/GetHospitaName", new UploadListener() {
            @Override
            public void callback(boolean respond, String msg) {
                System.out.println("respond:" + respond + " msg:" + msg);
            }
        });
    }

    public interface UploadListener {
        void callback(boolean respond, String msg);
    }

    //https://blog.csdn.net/yi_master/article/details/80893194
    public static void upload(String url, UploadListener listener) {
        String boundary = UUID.randomUUID().toString();
        FileInputStream fileInputStream = null;
        DataOutputStream dataOutputStream = null;
        InputStream inputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setConnectTimeout(300000);
            connection.setReadTimeout(300000);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Charset", "UTF-8");
            connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
            connection.setDoOutput(true);//以后就可以使用conn.getOutputStream().write()
            connection.setDoInput(true); //以后就可以使用conn.getInputStream().read();


//            fileInputStream = new FileInputStream(new File("D:/abc/BECG.xml"));
            File file = new File("D:/abc/1572421228526/ECGL.xml");
            System.out.println("'file:"+file.length());
            fileInputStream = new FileInputStream(file);


            dataOutputStream = new DataOutputStream(connection.getOutputStream());

            String top = "--" + boundary + "\n";
            dataOutputStream.write(top.getBytes());
            dataOutputStream.write(getMultipart("hospital", "安贞医院", boundary));
            dataOutputStream.write(getMultipart("patientName", "张三", boundary));
            dataOutputStream.write(getMultipart("gender", "M", boundary));
            dataOutputStream.write(getMultipart("CreateDate", "1999-9-9", boundary));
            dataOutputStream.write(getMultipart("Age", "123", boundary));
            dataOutputStream.write(getMultipart("Brithday", "1999-9-9", boundary));

            dataOutputStream.write(getMultipart("department", "心电图室", boundary));
            dataOutputStream.write(getMultipart("type", "门诊", boundary));
            dataOutputStream.write(getMultipart("outpaitent", "", boundary));
            dataOutputStream.write(getMultipart("inhospital", "", boundary));
            dataOutputStream.write(getMultipart("ward", "", boundary));
            dataOutputStream.write(getMultipart("bedin", "", boundary));

            String filePart = "Content-Disposition: form-data; name=\"uploadfile\"; filename=\"ECGL.xml\"\n";
            dataOutputStream.write(filePart.getBytes());

            byte[] bytes = new byte[1024];
            int i = 0;
            while ((i = fileInputStream.read(bytes)) != -1) {
                dataOutputStream.write(bytes, 0, i);
            }
            dataOutputStream.write("\r\n".getBytes()); //<\r>\n
            dataOutputStream.write(("--" + boundary + "--").getBytes());
            dataOutputStream.flush();

            int responseCode = connection.getResponseCode();
            System.out.println("responseCode:"+responseCode);
            if (responseCode == 200) {
                byteArrayOutputStream = new ByteArrayOutputStream();
                inputStream = connection.getInputStream();
                while ((i = inputStream.read(bytes)) != -1) {
                    byteArrayOutputStream.write(bytes, 0, i);
                }
                String result = new String(byteArrayOutputStream.toByteArray());
                System.out.println("result:" + result);
                listener.callback(true, "succes");
//                try {
//                    JSONObject jsonObject = new JSONObject(result);
//                    int status = jsonObject.getInt("Status");
//                    if (status==1){
//                        listener.callback(true, "succes");
//                    }else{
//                        listener.callback(true, "fail");
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    listener.callback(false, "error");
//                }
            } else {
                listener.callback(false, String.valueOf(responseCode));
            }
        } catch (IOException e) {
            e.printStackTrace();
            listener.callback(false, "400");
        } finally {
            try {
                if (fileInputStream != null)
                    fileInputStream.close();
                if (dataOutputStream != null)
                    dataOutputStream.close();
                if (inputStream != null)
                    inputStream.close();
                if (byteArrayOutputStream != null)
                    byteArrayOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static byte[] getMultipart(String key, String value, String boundary) {
        String s = "Content-Disposition: form-data; name=\"" + key + "\"\n" +
                "\n" +
                value + "\n--" +
                boundary + "\n";
        return s.getBytes();
    }
}
