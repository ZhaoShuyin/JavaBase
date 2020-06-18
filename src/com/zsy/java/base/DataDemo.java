package com.zsy.java.base;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * @Title com.zsy.java.base 数据转换Demo
 * @date 2020/4/15
 * @autor Zsy
 */
public class DataDemo {


    public static void main(String[] args) {
//        testA();
//        String sixteen = "A6";
//        int ten = Integer.parseInt(sixteen, 16);
//        System.out.println(String.valueOf(ten));
//        byte[] fes = hexStringToBytes("70");
//        System.out.println(Arrays.toString(fes));
//        System.out.println(String.valueOf(fes[0] & 0xff) );

//        System.out.println(bytesToHexString(new byte[]{119,-52}));

//        System.out.println(byteToChar(new byte[]{119,-52}));

        /*byte[] bytes = new byte[]{
                119, -52,
                32, 0, 0, 9,
                -1, -105, -2, 4,
                2, 72, 1, 81,
                1, -80, 0, 9,
                -1, -123, -1, -113,
                1, -28, -1, 90,
                0, 73, 0, 85,
                0,-88, -2, 54,
                -1,-101,-2, 4,
                2,75, 1, 78,
                1, -80, 0, 9,
                -1, -122, -1, -112,
                1,-27, -1, 90,
                0,69,0, 87,
                0,-86, -2, 56};
        deal(bytes,bytes.length);*/


        System.out.println(bytesToHexString(new byte[]{119, -52}));

    }


    private static void deal(byte[] unit, int position) {
        if (position < 61) {
            return;
        }
        int i = 5;
        int tmpv0 = (unit[i+1]<<8 | unit[i+2]);
        int tmpv1 = (unit[i+3]<<8 | unit[i+4]);

        int[] ints = new int[18];
        ints[0] = tmpv0;
        ints[1] = tmpv1;
        ints[2] = tmpv0 - tmpv1;
        ints[3] = 0 - (tmpv1 + tmpv0) / 2;
        ints[3] = 0-(tmpv1+tmpv0)/2;
        ints[4] = tmpv0-(tmpv1/2);
        ints[5] = tmpv1-(tmpv0/2);

        ints[6] = (unit[i+5]<<8 | unit[i+6]);
        ints[7] = (unit[i+7]<<8 | unit[i+8]);
        ints[8] = (unit[i+9]<<8 | unit[i+10]);
        ints[9] = (unit[i+11]<<8 | unit[i+12]);
        ints[10] = (unit[i+13]<<8 | unit[i+14]);
        ints[11] = (unit[i+15]<<8 | unit[i+16]);

        ints[12] = (unit[i+17]<<8 | unit[i+18]);
        ints[13] = (unit[i+19]<<8 | unit[i+20]);
        ints[14] = (unit[i+21]<<8 | unit[i+22]);
        ints[15] = (unit[i+23]<<8 | unit[i+24]);
        ints[16] = (unit[i+25]<<8 | unit[i+26]);
        ints[17] = (unit[i+27]<<8 | unit[i+28]);

        System.out.println("一组数据: ( " + position + " )  " + Arrays.toString(ints));
    }

    public static char byteToChar(byte[] b) {
        char c = (char) (((b[0] & 0xFF) << 8) | (b[1] & 0xFF));
        return c;
    }

    public static char[] getChars(byte[] bytes) {
        Charset cs = Charset.forName("UTF-8");
        ByteBuffer bb = ByteBuffer.allocate(bytes.length);
        bb.put(bytes);
        bb.flip();
        CharBuffer cb = cs.decode(bb);
        return cb.array();
    }

    public void test() {

    }


    private static void testA() {
        //        System.out.println(Arrays.toString(hexStringToBytes("66CC")));
//        System.out.println(Arrays.toString(hexStringToBytes("AA")));
//
//        System.out.println(bytesToHexString(new byte[]{102, -52}));
//        System.out.println(bytesToHexString(new byte[]{0, -87}));//00a9
//        System.out.println(Arrays.toString(hexStringToBytes("77CC")));
//        System.out.println(Arrays.toString(hexStringToBytes("88")));

        System.out.println("发送: " + bytesToHexString(new byte[]{119, -52, 32, 0, 0, 8, -1})); //77 CC 20 00 00 08 FF
//        System.out.println("发送: "+bytesToHexString(new byte[]{102, -52}));
//        System.out.println("接收: "+bytesToHexString(new byte[]{112, 0}));
//        System.out.println("发送: "+bytesToHexString(new byte[]{-86}));
//        System.out.println("接收: "+bytesToHexString(new byte[]{119, -52, 32, 0, 0, 8, -1, -94, -2, 5, 2}));
//        System.out.println("接收: "+bytesToHexString(new byte[]{119, -52, 32, 0, 0, 8, -1, -94, -2, 5, 2}));
//        System.out.println("接收: "+bytesToHexString(new byte[]{75, 1, 85, 1, -87, 0, 9, -1, -115, -1, -97, 1, -27, -1, 86, 0, 66, 0, 87, 0, -85, -2, 52, -1, -95, -2, 6, 2, 73, 1, 88, 1, -84, 0, 8, -1, -116, -1, -104, 1, -29, -1, 85, 0, 68, 0, 85, 0, -86, -2, 51, 119, -52, 32, 0, 0, 8, -1, -98, -2, 7, 2, 71, 1}));
    }


    public static byte[] hexStringToBytesA(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));

        }
        return d;
    }

    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }


    public static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }


    /**
     * byte——>String
     */
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
            stringBuilder.append(" ");
        }
        return stringBuilder.toString().toUpperCase();
    }

}


