package com.zsy.data.pyd;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @Title com.pengyang.library.data
 * @date 2021/6/22
 * @autor Zsy
 */

public class PYDUtil {

    /**
     * 从文件读取PYD信息
     *
     * @param file
     * @return
     */
    public static PydData readDate(File file) {
        if (file == null || !file.exists()) {
            return null;
        }
        if (file.length() < 512) {
            return null;
        }
        byte[] bytes = new byte[(int) file.length()];
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            inputStream.read(bytes);
            inputStream.close();
            return readDate(bytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 读取PYD信息
     *
     * @param bytes
     * @return
     */
    public static PydData readDate(byte[] bytes) {
        PydData pydData = new PydData();
        int leadsNumber = pydData.leads = bytes[10];
        pydData.gain = (short) (bytes[11] & 255 | bytes[12] << 8);
        pydData.sampling = (short) (bytes[8] & 255 | bytes[9] << 8);
        //数据长度
        int length = (bytes.length - 512) / 2 / leadsNumber;
        final int[][] datas = new int[leadsNumber][length];
        int index;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < leadsNumber; j++) {
                index = i * leadsNumber + j;
                datas[j][i] = (bytes[index * 2 + 512] & 255 | bytes[index * 2 + +513] << 8);
            }
        }
        System.out.println(datas.length);
        return pydData;
    }


    public static void main(String[] args) {
        readDate(new File("D:/abc/pyd/ECG.pyd"));
    }

}
