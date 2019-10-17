package com.zsy.java.net;

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
        CasesBean.Case aCase = new CasesBean.Case(
                "安贞医院",
                "张三",
                11,
                true,
                "1970-01-01",
                false,
                "2019-10-15",
                "D:/abc/BECG.xml");

        upload("http://192.168.0.52:8083/Api/UploadFileInfo", aCase, new UploadListener() {
            @Override
            public void callback(boolean respond, String msg) {
                System.out.println("respond: " + respond + "\nmsg: " + msg);
            }
        });
    }


    public interface UploadListener {
        void callback(boolean respond, String msg);
    }

    //https://blog.csdn.net/yi_master/article/details/80893194
    public static void upload(String url, CasesBean.Case aCase, UploadListener listener) {
        String boundary = UUID.randomUUID().toString();
        FileInputStream fileInputStream = null;
        DataOutputStream dataOutputStream = null;
        InputStream inputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {

            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Charset", "UTF-8");
            connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
            connection.setDoOutput(true);//以后就可以使用conn.getOutputStream().write()
            connection.setDoInput(true); //以后就可以使用conn.getInputStream().read();
            File file = new File(aCase.filePath);
            System.out.println("file "+file.length());
            fileInputStream = new FileInputStream(file);
            dataOutputStream = new DataOutputStream(connection.getOutputStream());

            String top = "--"+boundary + "\n";
            dataOutputStream.write(top.getBytes());
            dataOutputStream.write(getMultipart("hospital", aCase.hospital, boundary));
            dataOutputStream.write(getMultipart("patientName", aCase.name, boundary));
            dataOutputStream.write(getMultipart("gender", aCase.genders ? "M" : "F", boundary));
            dataOutputStream.write(getMultipart("CreateDate", aCase.date, boundary));
            dataOutputStream.write(getMultipart("Age", String.valueOf(aCase.age), boundary));
            dataOutputStream.write(getMultipart("Brithday", aCase.brithday, boundary));

            stream.write(top.getBytes());
            stream.write(getMultipart("hospital", aCase.hospital, boundary));
            stream.write(getMultipart("patientName", aCase.name, boundary));
            stream.write(getMultipart("gender", aCase.genders ? "M" : "F", boundary));
            stream.write(getMultipart("CreateDate", aCase.date, boundary));
            stream.write(getMultipart("Age", String.valueOf(aCase.age), boundary));
            stream.write(getMultipart("Brithday", aCase.brithday, boundary));

            String filePart = "Content-Disposition: form-data; name=\"uploadfile\"; filename=\"ECGL.xml\"\n";
            dataOutputStream.write(filePart.getBytes());

            stream.write(filePart.getBytes());

            byte[] bytes = new byte[1024];
            int i = 0;
            while ((i = fileInputStream.read(bytes)) != -1) {
                dataOutputStream.write(bytes, 0, i);
            }
            dataOutputStream.write("\n".getBytes());
            dataOutputStream.write(("--"+boundary+"--").getBytes());

            stream.write("文件\n".getBytes());
            stream.write(("--"+boundary+"--").getBytes());

            dataOutputStream.flush();

            System.out.println(">>>>>>>\n" + new String(stream.toByteArray()));

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                byteArrayOutputStream = new ByteArrayOutputStream();
                inputStream = connection.getInputStream();
                while ((i = inputStream.read(bytes)) != -1) {
                    byteArrayOutputStream.write(bytes, 0, i);
                }
                listener.callback(true, new String(byteArrayOutputStream.toByteArray()));
            } else {
                listener.callback(false, String.valueOf(responseCode));
            }
        } catch (IOException e) {
            e.printStackTrace();
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
