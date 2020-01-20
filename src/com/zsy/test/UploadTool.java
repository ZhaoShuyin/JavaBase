package com.zsy.test;

//import android.content.Context;
//
//import com.pengyang.ecg.R;
//import com.pengyang.ecg.bean.CaseBean2;
//
//import org.json.JSONException;
//import org.json.JSONObject;

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
        upload("http://39.98.68.80:3101/api/UploadFileForAndroid", "123456789", "结果分析",
                new File("C:/Users/pengyang/Desktop/数据/1572917636197-1分钟/ECGL.xml"),
                new UploadListener() {
                    @Override
                    public void callback(boolean respond, String msg) {

                    }
                });
    }


    private static String succes = "上传成功";
    private static String fail = "上传失败";
    private static String error = "上传响应错误";
    private static String invalid = "网络异常";

    public interface UploadListener {
        void callback(boolean respond, String msg);
    }

    /**
     * 上传数据文件
     */
    public static void upload(String url, String uid, String analysis, File file, UploadListener listener) {
        String boundary = UUID.randomUUID().toString();
        FileInputStream fileInputStream = null;
        DataOutputStream dataOutputStream = null;
        InputStream inputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setConnectTimeout(30000);
            connection.setReadTimeout(30000);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Charset", "UTF-8");
            connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
            connection.setDoOutput(true);//以后就可以使用conn.getOutputStream().write()
            connection.setDoInput(true); //以后就可以使用conn.getInputStream().read();
            fileInputStream = new FileInputStream(file);
            dataOutputStream = new DataOutputStream(connection.getOutputStream());

            String top = "--" + boundary + "\n";
            dataOutputStream.write(top.getBytes());
            dataOutputStream.write(getMultipart("conclusion", analysis, boundary));
            dataOutputStream.write(getMultipart("exam_uid", uid, boundary));

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
            if (responseCode == 200) {
                byteArrayOutputStream = new ByteArrayOutputStream();
                inputStream = connection.getInputStream();
                while ((i = inputStream.read(bytes)) != -1) {
                    byteArrayOutputStream.write(bytes, 0, i);
                }
                String result = new String(byteArrayOutputStream.toByteArray());
                System.out.println("result==========\n  "+result);
               /* try {
                    JSONObject jsonObject = new JSONObject(result);
                    int status = jsonObject.getInt("Status");
                    if (status == 1) {
                        listener.callback(true, succes);
                    } else {
                        listener.callback(true, fail);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    listener.callback(false, error);
                }*/
            } else {
                listener.callback(false, String.valueOf(responseCode));
            }
        } catch (IOException e) {
            e.printStackTrace();
            listener.callback(false, invalid);
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

    /**
     * 拼接表单参数
     */
    private static byte[] getMultipart(String key, String value, String boundary) {
        String s = "Content-Disposition: form-data; name=\"" + key + "\"\n" +
                "\n" +
                value + "\n--" +
                boundary + "\n";
        return s.getBytes();
    }
}
