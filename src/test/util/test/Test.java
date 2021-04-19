package test.util.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @Title test
 * @date 2021/4/1
 * @autor Zsy
 */

public class Test {

    public static void main(String[] args) {
        try {
            File file = new File("D:/Test/apk/origin.data");
            File target = new File("D:/Test/apk/ECGL.dcm");
            FileInputStream inputStream = new FileInputStream(file);
            int length = (int) file.length();
            byte[] bytes = new byte[length];
            int read = inputStream.read(bytes);
            int index = 0;
            //
          /*  while (index < length) {
                for (int i = 12; i < index; i++) {
                    bytes[24 + index] = 0x00;
                    bytes[25 + index] = 0x00;

                    bytes[26 + index] = 0x00;
                    bytes[27 + index] = 0x00;

                    bytes[28 + index] = 0x00;
                    bytes[29 + index] = 0x00;

                    bytes[30 + index] = 0x00;
                    bytes[31 + index] = 0x00;

                    bytes[32 + index] = 0x00;
                    bytes[33 + index] = 0x00;

                    bytes[34 + index] = 0x00;
                    bytes[35 + index] = 0x00;
                }
                index += 36;
            }*/
            DicomInfo dicomInfo = new DicomInfo(bytes, 12, 5);
            boolean b = DicomUtil.dicomFile(dicomInfo, target);
            System.out.println(" 转换Dicom ==> "+b);
            System.out.println(" 原来文件 :" + file.length() + "  " + file.getAbsolutePath());
            System.out.println(" 转换后文件  : " + target.length() + "  " + target.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }
    }

}
