package com.zsy.data.pyd;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;


/**
 * @Title com.zsy.strust
 * @date 2021/6/2
 * @autor Zsy
 */
public class PydData implements Serializable {

    //order = 0)
    public byte[] version = new byte[7];//版本标记
    //order = 1)
    public byte p;             //版本标记尾端0
    //order = 2)
    public short sampling;     //⼼电采样率 【8-9】 例：500，1000
    //order = 3)
    public byte leads;         //⼼电导联数 【10】 例：12，18
    //order = 4)
    public short gain;          //1mv数据值 【11-12】 例：125，180
    //order = 5)
    public int length;         //⼼电数据⻓度 【13-16】 例⼦：30000   单位：毫秒
    //order = 7)
    public byte[] datas;       //心电数据


    public PydData() {
    }

    public PydData(String versionStr, short sampling, byte leads, short gain, int length, byte[] bytes) {
        byte[] bs = versionStr.getBytes();
        int min = Math.min(bs.length, 7);
        for (int i = 0; i < min; i++) {
            version[i] = bs[i];
        }
        this.sampling = sampling;
        this.leads = leads;
        this.gain = gain;
        this.length = length;
        this.datas = bytes;
    }

    public PydData(byte[] bytes) {
        datas = bytes;
        for (int i = 0; i < 6; i++) {
            version[i] = bytes[i];
        }
        //采样率
        sampling = (short) (bytes[9] << 8 | bytes[8] & 255);
        //导联个数
        leads = bytes[10];
        if (leads == 0) {
            leads = 12;
        }
        //增益
        gain = (short) (int) (bytes[12] << 8 | bytes[11] & 255);
        //数据时长毫秒值
        length = (bytes[16] << 24 & 0xff000000) | (bytes[15] << 16 & 0xff0000) | (bytes[14] << 8 & 0xff00) | (bytes[13] & 0xff);
    }

    public int[][] ints; //计算出的显示数据


    /**
     * 数据转换为可绘制的二维数组
     */
    public void turnInts() {
        if (datas.length <= 512) {
            ints = new int[leads][0];
            return;
        }
        int length = (datas.length - 512) / 2 / leads;
        int leadsNumber = leads;
        ints = new int[leads][length];
        int index = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < leadsNumber; j++) {
                index = i * leadsNumber + j;
                ints[j][i] = (datas[index * 2 + 512] & 255 | datas[index * 2 + +513] << 8);
            }
        }
    }

    /**
     * 写PYD格式的文件
     *
     * @param file
     * @return
     */
    public boolean write(File file) {
        if (!file.exists()) {
            return false;
        }
        try {
            FileOutputStream output = new FileOutputStream(file);
            byte[] bytes = new byte[512];
            bytes[0] = version[0];
            bytes[1] = version[1];
            bytes[2] = version[2];
            bytes[3] = version[3];
            bytes[4] = version[4];
            bytes[5] = version[5];
            bytes[6] = version[6];

            int s = sampling;               //数据采样率
            bytes[8] = (byte) (s & 255);
            bytes[9] = (byte) (s >> 8);

            bytes[10] = leads;

            int g = gain;                   //数据增益
            bytes[11] = (byte) (g & 255);
            bytes[12] = (byte) (g >> 8);

            int l = length;                 //数据毫秒数
            bytes[16] = (byte) (l >> 24);
            bytes[15] = (byte) (l >> 16);
            bytes[14] = (byte) (l >> 8);
            bytes[13] = (byte) (l & 255);

            output.write(bytes);              //ECG信息
            output.write(datas);              //ECG数据

            //50 59 45 43 47 30 31 00 f4 01 0c e4 57 10 27 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00
            //50 59 45 43 47 30 31 00 f4 01 0c e4 57 10 27 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String toString() {
        return "{version:" + new String(version) + ",sampling:" + sampling + ",leads:" + leads + ",gain:" + gain + ",length:" + length + ",datas:" + datas.length + "}";
    }


    public static void main(String[] args) throws Exception {
        showPYD("D://abc/pyd/ECG.pyd");
        showPYD("D://abc/pyd/3711221052500002.pyd");
    }

    private static void showPYD(String path) throws IOException {
        File file = new File(path);
        FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[(int) file.length()];
        inputStream.read(bytes);
        inputStream.close();
        PydData pydData = new PydData(bytes);
        System.out.println(pydData);
    }
}
