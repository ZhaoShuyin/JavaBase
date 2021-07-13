package com.zsy.strust;

import com.zsy.utils.HexUtil;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import struct.JavaStruct;
import struct.StructClass;
import struct.StructException;
import struct.StructField;

/**
 * @Title com.pengyang.library.bean
 * @date 2021/6/2
 * @autor Zsy
 */

@StructClass
public class EcgParam implements Serializable {

    @StructField(order = 0)
    public byte[] version = new byte[6];//版本标记
    @StructField(order = 1)
    public byte p = 0;             //版本标记尾端0
    @StructField(order = 2)
    public short heartrate;    //心率    【8-9】    例：50-200bpm
    @StructField(order = 3)
    public short P_W;          //P波宽度 【10-11】  例：92ms
    @StructField(order = 4)
    public short QRS_W;        //QRS宽度 【12-13】  例：100ms
    @StructField(order = 5)
    public short T_W;          //T宽度   【14-15】
    @StructField(order = 6)
    public short RR_I;         //RR间期  【16-17】
    @StructField(order = 7)
    public short PR_I;         //PR间期  【18-19】  例：140ms
    @StructField(order = 8)
    public short QT_I;         //QT间期  【20-21】  例：380ms
    @StructField(order = 9)
    public short QTC_I;        //QTC间期 【22-23】
    @StructField(order = 10)
    public short P_TAxis;      //T轴     【24-25】
    @StructField(order = 11)
    public short QRS_TAxis;    //QRS轴   【26-27】
    @StructField(order = 12)
    public short T_TAxis;      //T轴     【28-29】
    @StructField(order = 13)
    public short RV5;          //RV5     【30-31】
    @StructField(order = 14)
    public short SV1;          //SV1     【32-33】
    @StructField(order = 15)
    public short cardiacNumber;//⼼博总数 【34-35】

    @StructField(order = 16)
    public byte[] conclusion = new byte[512];//⾃动结论 GBK编码
    @StructField(order = 17)
    public byte[] cardiacs;                  //⼼博位置/属性数据段

    public EcgParam(int length) {
        cardiacs = new byte[length];
    }

    public int[][] getCardiacDatas() {
        int length = cardiacs.length / 5;
        int[][] ints = new int[length][2];
        for (int i = 0; i < length; i++) {
            ints[i][0] = (cardiacs[5 * i] << 24 & 0xff000000) | (cardiacs[5 * i + 1] << 16 & 0xff0000) | (cardiacs[5 * i + 2] << 8 & 0xff00) | (cardiacs[5 * i + 3] & 0xff);
            ints[i][1] = cardiacs[5 * i + 4];
        }
        return ints;
    }

    public String getConclusion() {
        if (conclusion == null) {
            return null;
        }
        List<Byte> list = new ArrayList<>();

        int index = 0;
        while (index < 512 && (conclusion[index++]) != 0) {
        }
        byte[] copyOf = Arrays.copyOf(conclusion, index);

//        list.toArray(new String[10]);

        try {
            return new String(copyOf,"GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean setCardiacDatas(int[][] ints) {
        if (ints == null || ints[0].length != 2) {
            return false;
        }
        cardiacs = new byte[ints.length * 5];
        for (int i = 0; i < ints.length; i++) {
            cardiacs[5 * i] = (byte) (ints[i][0] >> 24);
            cardiacs[5 * i + 1] = (byte) (ints[i][0] >> 16);
            cardiacs[5 * i + 2] = (byte) (ints[i][0] >> 8);
            cardiacs[5 * i + 3] = (byte) (ints[i][0]);
            cardiacs[5 * i + 4] = (byte) ints[i][1];
        }
        return true;
    }

    public boolean setConclusion(String s) {
        try {
            byte[] gbks = s.getBytes("GBK");
            System.out.println("gbks.length:  " + gbks.length);
            if (gbks.length > 512) {
                return false;
            }
            for (int i = 0; i < gbks.length; i++) {
                conclusion[i] = gbks[i];
            }
            return true;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) throws StructException {
        EcgParam ecgInfo = new EcgParam(3);
        ecgInfo.setConclusion("这是自动诊断结论");
        int[][] ints = new int[5][2];
        for (int i = 0; i < ints.length; i++) {
            ints[i][0] = i * 500;
            ints[i][1] = i;
        }
        ecgInfo.setCardiacDatas(ints);
        byte[] pack = JavaStruct.pack(ecgInfo);
        System.out.println(HexUtil.encodeHexStr(pack));
        System.out.println("=========================");
        int[][] cardiacDatas = ecgInfo.getCardiacDatas();
        for (int i = 0; i < cardiacDatas.length; i++) {
            System.out.println("0:" + cardiacDatas[i][0] + ",1:" + cardiacDatas[i][1]);
        }
        System.out.println("=========================");
        System.out.println(ecgInfo.getConclusion());
    }


}
