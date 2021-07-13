package com.zsy.netty.websocket;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

/**
 * @Title com.py.transit.model
 * @date 2021/6/3
 * @autor Zsy
 */
public class MessageStrust implements Serializable {

    public short header = -1;     //0:包头 0~1

    public int length;            //长度   2~5

    public byte type = 0x03;      //1:类型 1:数据 2:信息 3:消息

    public byte[] device = new byte[12]; //设备号  7~18

    public byte[] message;        //消息    20~

    public byte o1 = 0x00;

    /**
     * @param device  //设备号
     * @param message //消息
     */
    public MessageStrust(String device, String message) {
        try {
            this.device = device.getBytes("UTF-8");
            this.message = message.getBytes("UTF-8");
            length = 19 + this.message.length + 1;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param length 数据长度
     * @param bytes  数据字节数组
     */
    public MessageStrust(int length, byte[] bytes) {
        this.length = length + 7;
        device[0] = bytes[0];
        device[1] = bytes[1];
        device[2] = bytes[2];
        device[3] = bytes[3];
        device[4] = bytes[4];
        device[5] = bytes[5];
        device[6] = bytes[6];
        device[7] = bytes[7];
        device[8] = bytes[8];
        device[9] = bytes[9];
        device[10] = bytes[10];
        device[11] = bytes[11];
        int start = 12;
        message = stringBytes(bytes, start);
    }

    // 从字节数组中读取字符串
    private byte[] stringBytes(byte[] bytes, int start) {
        int index = start;
        while (bytes[index++] != 0) {
        }
        index--;
        byte[] bs = new byte[index - start];
        for (int i = 0; i < bs.length; i++) {
            bs[i] = bytes[i + start];
        }
        return bs;
    }

    public String getDevice() {
        return new String(device);
    }

    public String getMessage() {
        try {
            return new String(message, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        return "{length:" + length + ",device:" + getDevice() + ",message:" + getMessage() + "}";
    }

}
