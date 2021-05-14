package com.zsy.dicom;

import com.zsy.dicom.util.DicomInfo;
import com.zsy.dicom.util.DicomUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

/**
 * @Title com.zsy.dicom
 * @date 2021/5/11
 * @autor Zsy
 */

public class Demo {

    public static void main(String[] args) {
       /* File path = new File("D:/abc/dicom/窦性正常");
        File outPath = new File("D:/abc/dicom/窦性正常/out");
        File[] files = path.listFiles();
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            if (file.isDirectory()){
                continue;
            }
            String dicomname = file.getName();
            System.out.println("====== "+dicomname);
            String name = dicomname.substring(0,dicomname.lastIndexOf("."));
            System.out.println("========================= "+name);
            DicomInfo dicomInfo = DicomUtil.readDicomFile(file);
            try {
                FileWriter writer = new FileWriter(new File(outPath, name + ".txt"));
                writer.write("导联数据长度:"+dicomInfo.leadsNumber+"\n");
                writer.write("每个导联增益:"+ Arrays.toString(dicomInfo.leadsGain)+"\n");
                writer.write("采样率:"+ dicomInfo.sampling+"\n");
                writer.close();
                FileOutputStream outputStream = new FileOutputStream(new File(outPath, name + ".dat"));
                outputStream.write(dicomInfo.bytes);
                outputStream.close();
                System.out.println("=============================================================== 转换");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/
        byte[] bytes = new byte[12 * 10 * 2 * 500];
        for (int i = 0; i < 5000; i++) {
            for (int j = 0; j < 12; j++) {
                bytes[i * 24 + 2 * j] = (byte) (j & 255);
                bytes[i * 24 + 2 * j + 1] = (byte) (j >> 8);
            }
        }
//        DicomInfo dicomInfo = new DicomInfo(bytes, 12, 1);
//        DicomUtil.dicomFile(dicomInfo, new File("D:/abc/dicom/ECGL.dcm"));
        DicomInfo dicomInfo = DicomUtil.readDicomFile(new File("D:/abc/dicom/ECGL.dcm"));
        try {
            FileOutputStream outputStream = new FileOutputStream(new File("D:/abc/dicom/ECGL.dat"));
            outputStream.write(dicomInfo.bytes);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
