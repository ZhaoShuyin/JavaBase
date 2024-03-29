package com.zsy.dicom.test;


import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;


/**
 * @Title 上传工具类
 * @date 2019/10/15
 * @autor Zsy
 */
public class UploadDicom {

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
    public static void upload(String uid, File file,UploadListener listener) {
        String boundary = UUID.randomUUID().toString();
        FileInputStream fileInputStream = null;
        DataOutputStream dataOutputStream = null;
        InputStream inputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        //读取文件中的导联模式标签 12导/18导
        String lead_type = String.valueOf(DicomUtil.readModeName(file));
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL("http://39.98.68.80:3008/api/UploadForAndroidML/UploadFileInfo").openConnection();
            connection.setConnectTimeout(60000);
            connection.setReadTimeout(60000);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Charset", "UTF-8");
            connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
            connection.setDoOutput(true);//以后就可以使用conn.getOutputStream().write()
            connection.setDoInput(true); //以后就可以使用conn.getInputStream().read();
            fileInputStream = new FileInputStream(file);
            dataOutputStream = new DataOutputStream(connection.getOutputStream());

            String top = "--" + boundary + "\n";
            dataOutputStream.write(top.getBytes());
            dataOutputStream.write(getMultipart("exam_uid", uid, boundary));
            dataOutputStream.write(getMultipart("lead_type", lead_type, boundary));

            String filePart = "Content-Disposition: form-data; name=\"uploadfile\"; filename=\"ECGL.dcm\"\n\n";
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
                listener.callback(true,result);
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
