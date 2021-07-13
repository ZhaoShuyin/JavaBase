package com.zsy.utils;

/**
 * @Title com.example.app4
 * @date 2020/12/10
 * @autor Zsy
 */

public class HexUtil {
    public static String encodeHexStr(byte[] value, int length) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            String str = Integer.toHexString(value[i]);
            builder.append(" ");
            if (str.length() == 8) {
                str = str.substring(6);
            }
            if (str.length() == 1) {
                builder.append("0");
            }
            builder.append(str);
        }
        return builder.toString();
    }

    public static String encodeHexStr(byte[] value) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < value.length; i++) {
            String str = Integer.toHexString(value[i]);
            builder.append(" ");
            if (str.length() == 8) {
                str = str.substring(6);
            }
            if (str.length() == 1) {
                builder.append("0");
            }
            builder.append(str);
        }
        return builder.toString();
    }

    public static String bytetoString(byte... b) {
        return Integer.toHexString(b[0]) + Integer.toHexString(b[1]) + Integer.toHexString(b[2]) + Integer.toHexString(b[3]);
    }

    public static String toHexStr(byte... b) {
        return "[" + Integer.toHexString(b[0]) + "," + Integer.toHexString(b[1]) + "," + Integer.toHexString(b[2]) + "]";
    }

    public static void main(String[] args) {
        byte[] bytes = {0x00, 0x00, 0x00, 0x01};
        System.out.println(new String(bytes));
    }

}
