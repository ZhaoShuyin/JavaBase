package test.other;

import java.util.Arrays;

/**
 * @Title test
 */

class TestData {

    private static String bytesString(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (int i = 0; i < bytes.length; i++) {
            builder.append(""+Integer.toHexString(bytes[i])+", ");
        }
        builder.append("]");
        return builder.toString();
    }

    public static void main(String[] args) {
        int[] ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        byte[] bytes = getBytes(ints);
        System.out.println("字节数据"+bytesString(bytes));
        deal(32, bytes);
        System.out.println("转换数字"+Arrays.toString(temp));
    }

    private static int[] temp = new int[18];         //暂存处理数据数组

    private static void deal(int position, byte[] read) {
        if (position < 31) {
            return;
        }
        temp[1] = (read[3] & 255 | read[4] << 8) - 0x800;      //II      //0000 0000 0000 0000 1111 1111 1111 1111 & 0xffff - 0x800
        temp[2] = (read[5] & 255 | read[6] << 8) - 0x800;      //III
        temp[0] = temp[1] - temp[2];                          //I   = II-III
        temp[3] = temp[2] / 2 - temp[1];                      //avR = III/2-II
        temp[4] = temp[1] / 2 - temp[2];                      //avL = II/2-III
        temp[5] = (temp[1] + temp[2]) / 2;                    //avF = (II+III)/2
        temp[6] = (read[7] & 255 | read[8] << 8) - 0x800;      //C1
        temp[7] = (read[9] & 255 | read[10] << 8) - 0x800;     //C2
        temp[8] = (read[11] & 255 | read[12] << 8) - 0x800;    //C3
        temp[9] = (read[13] & 255 | read[14] << 8) - 0x800;    //C4
        temp[10] = (read[15] & 255 | read[16] << 8) - 0x800;   //C5
        temp[11] = (read[17] & 255 | read[18] << 8) - 0x800;   //C6
        temp[12] = (read[19] & 255 | read[20] << 8) - 0x800;   //C7
        temp[13] = (read[21] & 255 | read[22] << 8) - 0x800;   //C8
        temp[14] = (read[23] & 255 | read[24] << 8) - 0x800;   //C9
        temp[15] = (read[25] & 255 | read[26] << 8) - 0x800;   //C3R
        temp[16] = (read[27] & 255 | read[28] << 8) - 0x800;   //C4R
        temp[17] = (read[29] & 255 | read[30] << 8) - 0x800;   //C5R
    }

    /**
     * 从int[]换算byte[]的18导联数据
     */
    public static byte[] dataByte = new byte[36];

    /**
     * int[]数据 转换为二字节 byte[]数据
     *
     * @param ints
     * @return
     */
    public static byte[] getBytes(int[] ints) {
        dataByte[0] = (byte) (ints[0] & 255);
        dataByte[1] = (byte) (ints[0] >> 8);
        dataByte[2] = (byte) (ints[1] & 255);
        dataByte[3] = (byte) (ints[1] >> 8);
        dataByte[4] = (byte) (ints[2] & 255);
        dataByte[5] = (byte) (ints[2] >> 8);
        dataByte[6] = (byte) (ints[3] & 255);
        dataByte[7] = (byte) (ints[3] >> 8);
        dataByte[8] = (byte) (ints[4] & 255);
        dataByte[9] = (byte) (ints[4] >> 8);
        dataByte[10] = (byte) (ints[5] & 255);
        dataByte[11] = (byte) (ints[5] >> 8);
        dataByte[12] = (byte) (ints[6] & 255);
        dataByte[13] = (byte) (ints[6] >> 8);
        dataByte[14] = (byte) (ints[7] & 255);
        dataByte[15] = (byte) (ints[7] >> 8);
        dataByte[16] = (byte) (ints[8] & 255);
        dataByte[17] = (byte) (ints[8] >> 8);
        dataByte[18] = (byte) (ints[9] & 255);
        dataByte[19] = (byte) (ints[9] >> 8);
        dataByte[20] = (byte) (ints[10] & 255);
        dataByte[21] = (byte) (ints[10] >> 8);
        dataByte[22] = (byte) (ints[11] & 255);
        dataByte[23] = (byte) (ints[11] >> 8);
        dataByte[24] = (byte) (ints[12] & 255);
        dataByte[25] = (byte) (ints[12] >> 8);
        dataByte[26] = (byte) (ints[13] & 255);
        dataByte[27] = (byte) (ints[13] >> 8);
        dataByte[28] = (byte) (ints[14] & 255);
        dataByte[29] = (byte) (ints[14] >> 8);
        dataByte[30] = (byte) (ints[15] & 255);
        dataByte[31] = (byte) (ints[15] >> 8);
        dataByte[32] = (byte) (ints[16] & 255);
        dataByte[33] = (byte) (ints[16] >> 8);
        dataByte[34] = (byte) (ints[17] & 255);
        dataByte[35] = (byte) (ints[17] >> 8);
        return dataByte;
    }


}
