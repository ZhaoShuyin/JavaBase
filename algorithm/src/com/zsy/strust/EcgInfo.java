package com.zsy.strust;

import java.io.Serializable;

import struct.StructClass;
import struct.StructField;

/**
 * @Title com.zsy.strust
 * @date 2021/6/2
 * @autor Zsy
 */
@StructClass
public class EcgInfo implements Serializable {

    @StructField(order = 0)
    public byte[] version = new byte[6];//版本标记
    @StructField(order = 1)
    public byte p;             //版本标记尾端0
    @StructField(order = 2)
    public short sampling;     //⼼电采样率 【8-9】 例：500，1000
    @StructField(order = 3)
    public short leads;        //⼼电导联数 【10】 例：12，18
    @StructField(order = 4)
    public byte gain;          //1mv数据值 【11-12】 例：125，180
    @StructField(order = 5)
    public int length;         //⼼电数据⻓度 【13-16】 例⼦：30000   单位：毫秒
    @StructField(order = 6)
    public byte[] datas;       //心电数据

    public static void main(String[] args) {

    }
}
