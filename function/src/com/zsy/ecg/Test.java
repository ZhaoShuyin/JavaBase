package com.zsy.ecg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @Title com.zsy.ecg
 * @date 2021/8/5
 * @autor Zsy
 */

class Test {


    public int[] ints = {
            13, 44, 64, 87, 135, 157, 174, 194, 197, 186, 172, 153, 105, 81, 56, 20, 6, -12, -18, -22, -21, -19, -17, -13, -12,
            -12, -11, -12, -11, -11, -11, -10, -11, -12, -12, -12, -12, -11, -11, -10, -9, -9, -9, -8, -7, -5, -5, -2, -1, 2, 3,
            5, 10, 12, 14, 19, 22, 26, 28, 30, 32, 34, 35, 38, 39, 41, 40, 41, 42, 42, 41, 39, 37, 35, 34, 32, 29, 26, 23, 18, 16,
            9, 7, 3, -2, -3, -5, -8, -9, -10, -10, -11, -11, -11, -11, -11, -12, -12, -12, -12, -12, -13, -12, -12, -11, -11, -11,
            -11, -11, -11, -11, -11, -11, -11, -10, -11, -11, -11, -11, -11, -11, -11, -12, -12, -13, -13, -13, -13, -13, -13, -13,
            -13, -12, -12, -12, -11, -11, -11, -10, -10, -10, -10, -9, -9, -9, -9, -9, -8, -9, -8, -7, -7, -7, -7, -6, -6, -5, -4,
            -4, -5, -6, -5, -5, -6, -7, -8, -8, -8, -9, -9, -9, -9, -10, -10, -10, -9, -10, -9, -9, -10, -13, -14, -16, -13, -11,
            -6, 12, 25, 60, 82, 106, 150, 167, 180, 191, 190, 169, 152, 132, 82, 60, 39, 8, -3, -17, -20, -22, -20, -17, -15, -12,
            -12, -11, -11, -10, -11, -11, -11, -10, -11, -12, -12, -12, -11, -11, -12, -10, -11, -9, -8, -8, -5, -5, -4, -1, 1, 5,
            7, 10, 14, 17, 18, 23, 25, 29, 30, 31, 34, 36, 36, 38, 40, 41, 41, 41, 42, 41, 41, 39, 38, 36, 34, 33, 28, 25, 22, 16,
            12, 7, 3, 0, -5, -6, -8, -10, -11, -12, -12, -12, -11, -12, -12, -12, -12, -12, -12, -12, -12, -11, -12, -11, -10, -11,
            -11, -10, -10, -10, -10, -12, -12, -12, -13, -13, -14, -14, -14, -13, -13, -12, -12, -11, -11, -10, -10, -11, -10, -10,
            -11, -11, -11, -11, -10, -10, -10, -10, -10, -10, -10, -10, -9, -10, -10, -9, -10, -10, -9, -9, -9, -8, -7, -7, -7, -6,
            -6, -5, -5, -6, -6, -6, -6, -6, -8, -8, -8, -8, -8, -8, -8, -8, -7, -8, -7, -8, -8, -8, -8, -10, -14, -15, -17, -12, -7,
            1, 24, 42, 85, 110, 135, 176, 190, 199, 199, 190, 159, 135, 110, 60, 38, 20, -5, -13, -21, -22, -21, -16, -13, -12, -11,
            -11, -12, -12, -12, -12, -12, -11, -12, -11, -11, -11, -11, -11, -10, -11, -11, -11, -10, -9, -9, -7, -5, -5, -1, 0, 5, 7,
            9, 14, 16, 18, 22, 24, 28, 30, 32, 34, 36, 37, 40, 40, 41, 42, 42, 40, 40, 40, 38, 36, 34, 32, 29, 25, 22, 19, 13, 10, 5,
            1, -2, -6, -8, -10, -11, -12, -12, -12, -12, -12, -12, -12, -12, -12, -11, -12, -12, -12, -11, -12, -12, -12, -12, -12, -12,
            -12, -11, -11, -11, -11, -11, -10, -10, -11, -11, -11, -11, -10, -12, -12, -12, -11, -11, -11, -12, -13, -13, -12, -13,
            -13, -13, -13, -11, -11, -11, -11, -11, -10, -9, -8, -9, -8, -8, -8, -7, -8, -6, -7, -7, -6, -7, -6, -7, -7, -7, -8, -8, -7,
            -8, -8, -8, -8, -8, -8, -8, -8, -8, -8, -9, -8, -8, -9, -8, -8, -8, -10, -11, -15, -16, -15, -8, -1, 8, 38, 58, 103, 128,
            152, 185, 194, 198, 191, 177, 139, 114, 88, 42, 23, 9, -12, -17, -22, -21, -19, -14, -13, -12, -11, -12, -13, -13, -13, -12,
            -12, -12, -12, -12, -12, -12, -11, -12, -10, -11, -9, -9, -8, -7, -6, -3, -2, -1, 3, 5, 8, 10, 12, 16, 18, 21, 25, 27, 30, 32,
            34, 36, 37, 39, 40, 40, 41, 42, 42, 41, 40, 39, 37, 36, 33, 30, 28, 22, 19, 16, 8, 5, -2, -5, -7, -11, -12, -14, -13, -14, -13,
            -12, -12, -12, -11, -10, -11, -11, -11, -11, -12, -12, -12, -12, -12, -13, -12, -12, -12, -12, -12, -12, -12, -12, -12, -12, -12,
            -12, -12, -12, -11, -11, -11, -10, -10, -10, -9, -9, -10, -10, -10, -11, -11, -9, -10, -9, -9, -9, -9, -9, -9, -10, -10, -10, -10,
            -10, -10, -10, -9, -8, -7, -7, -6, -6, -5, -5, -5, -5, -5, -6, -6, -6, -6, -7, -7, -7, -7, -7, -7, -8, -8, -8, -8, -8, -8, -8, -8,
            -8, -9, -11, -13, -15, -13, -11, 1, 11, 23, 57, 79, 126, 147, 165, 189, 192, 191, 173, 158, 113, 88, 63, 24, 9, -2, -18, -21, -23,
            -21, -18, -14, -14, -13, -13, -13, -13, -13, -13, -13, -12, -11, -11, -11, -11, -11, -11, -11, -11, -11, -10, -10, -9, -8, -7, -4,
            -2, -1, 4, 5, 10, 12, 14, 20, 22, 24, 27, 30, 31, 34, 34, 37, 38, 38, 39, 39, 40, 41, 41, 41, 40, 39, 36, 36, 32, 29, 26, 20, 17, 14,
            9, 5, 0, -3, -6, -9, -9, -11, -12, -12, -12, -12, -12, -11, -11, -11, -12, -11, -11, -11, -11, -12, -11, -11, -11, -11, -11, -12, -12,
            -12, -12, -12, -12, -12, -12, -10, -11, -11, -10, -10, -10, -9, -10, -10, -11, -12, -12, -13, -13, -13, -13, -13, -13, -13, -12, -11,
            -11, -11, -10, -11, -11, -10, -10, -11, -11, -10, -10, -8, -9, -7, -7, -6, -6, -5, -5, -4, -4, -4, -5, -5, -6, -5, -7, -8, -8, -8, -8,
            -9, -9, -9, -9, -10, -9, -9, -9, -9, -9, -9, -11, -14, -15, -15, -14, -9, 8, 20, 36, 75, 99, 146, 165, 181, 197, 196, 191, 164, 143, 94,
            69, 47, 12, 1, -8, -19, -21, -20, -17, -15, -12, -12, -13, -12, -12, -12, -12, -11, -10, -11, -10, -11, -11, -12, -12, -12, -11, -11, -11,
            -10, -9, -7, -6, -4, -1, -1, 2, 6, 8, 12, 14, 17, 20, 22, 25, 28, 29, 32, 33, 34, 36, 38, 39, 39, 39, 41, 41, 40, 40, 38, 37, 34, 33, 29,
            26, 23, 17, 15, 12, 5, 3, -1, -6, -7, -9, -10, -12, -13, -12, -12, -11, -12, -12, -11, -12, -12, -12, -12, -12, -11, -11, -11, -11, -11,
            -11, -11, -11, -11, -11, -11, -11, -12, -12, -12, -12, -13, -13, -12, -12, -13, -13, -12, -10, -10, -10, -9, -9, -9, -9, -9, -9, -9, -9,
            -9, -9, -9, -9, -9, -9, -9, -9, -9, -7, -7, -6, -7, -6, -5, -6, -6, -6, -6, -5, -6, -5, -4, -4, -4, -5, -5, -6, -7, -7, -8, -9, -9, -9,
            -10, -9, -10, -9, -9, -10, -9, -10, -10, -10, -12, -15, -16, -13, -9, -2, 19, 34, 52, 97, 121, 164, 180, 190, 195, 191, 180, 144, 121,
            70, 48, 29, 2, -8, -15, -22, -22, -17, -15, -14, -13, -11, -11, -12, -12, -13, -12, -12, -11, -12, -12, -12, -11, -11, -11, -11, -9, -9,
            -9, -6, -5, -5, -3, -2, 1, 2, 3, 7, 9, 13, 15, 18, 22, 25, 27, 30, 32, 34, 36, 37, 39, 39, 40, 40, 42, 41, 41, 40, 39, 37, 36, 34, 31, 29,
            24, 21, 14, 10, 8, 2, -2, -4, -7, -8, -9, -9, -10, -10, -10, -10, -11, -12, -12, -13, -13, -13, -14, -13, -13, -12, -12, -12, -10, -11, -11,
            -12, -11, -12, -13, -12, -13, -13, -13, -13, -12, -12, -11, -11, -10, -10, -10, -10, -11, -10, -10, -11, -10, -10, -10, -10, -9, -10, -9, -8,
            -8, -8, -7, -8, -7, -7, -7, -7, -7, -6, -7, -6, -6, -6, -5, -5, -5, -4, -5, -4, -4, -5, -4, -6, -6, -7, -6, -7, -9, -8, -9, -9, -9, -8, -8,
            -9, -8, -8, -8, -9, -8, -11, -11, -13, -17, -17, -10, -3, 6, 33, 51, 73, 121, 145, 181, 192, 196, 192, 181, 165, 120, 94, 47, 27, 12, -9,
            -17, -21, -21, -20, -15, -13, -11, -10, -10, -10, -11, -11, -11, -10, -11, -11, -11, -11, -12, -12, -12, -13, -13, -12, -11, -11, -10, -8,
            -7, -4, -2, 1, 3, 6, 10, 12, 15, 19, 20, 24, 27, 28, 31, 32, 34, 37, 38, 40, 41, 41, 42, 42, 41, 40, 40, 37, 37, 35, 32, 29, 27, 22, 19,
            13, 9, 5, -1, -4, -6, -10, -11, -13, -13, -13, -13, -13, -13, -13, -12, -12, -12, -11, -11, -11, -11, -10, -11, -12, -12, -11, -12, -11, -11,
            -12, -10, -10, -10, -10, -10, -10, -11, -10, -10, -11, -11, -11, -11, -11, -11, -10, -11, -10, -9, -10, -9, -9, -9, -9, -8, -9, -9, -9, -9, -9,
            -8, -8, -8, -8, -7, -8, -7, -8, -7, -7, -6, -7, -7, -7, -5, -7, -6, -7, -7, -6, -6, -7, -7, -7, -7, -8, -8, -8, -8, -9, -9, -10, -9, -9, -9, -9,
            -9, -9, -12, -14, -15, -17, -15, -5, 4, 16, 50, 72, 96, 144, 165, 192, 199, 199, 183, 168, 147, 98, 71, 29, 14, 1, -16, -20, -22, -20, -17, -12,
            -11, -10, -10, -11, -12, -13, -13, -13, -14, -14, -14, -14, -14, -12, -11, -11, -11, -10, -9, -8, -8, -6, -6, -5, -1, -1, 3, 4, 6, 12, 14, 16, 21,
            23, 27, 29, 30, 34, 34, 36, 39, 39, 40, 41, 41, 41, 41, 40, 39, 39, 36, 35, 33, 29, 26, 24, 18, 14, 7, 4, 1, -5, -8, -10, -12, -13, -14, -13, -14,
            -14, -14, -13, -13, -12, -12, -12, -12, -12, -12, -12, -12, -11, -12, -12, -12, -12, -12, -12, -12, -11, -11, -11, -12, -12, -12, -12, -12, -11,
            -11, -10, -9, -9, -10, -9, -9, -8, -8, -8, -8, -9, -8, -9, -9, -8, -8, -8, -8, -8, -8, -8, -9, -9, -9, -9, -9, -9, -9, -8, -8, -7, -6, -7, -6, -5,
            -5, -5, -5, -5, -5, -6, -7, -8, -8, -9, -9, -9, -10, -9, -10, -9, -9, -9, -9, -9, -9, -9, -11, -15, -16, -17, -15, -11, 5, 16, 31, 70, 94, 118, 162,
            179, 190, 196, 192, 168, 149, 125, 75, 52, 32, 4, -6, -18, -20, -21, -17, -15, -13, -11, -10, -11, -11, -11, -12, -12, -12, -13, -12, -13, -13, -12,
            -12, -12, -12, -12, -11, -11, -10, -10, -8, -8, -7, -3, -1, 3, 5, 8, 13, 15, 17, 21, 23, 27, 27, 29, 32, 34, 35, 37, 39, 40, 41, 41, 41, 41, 40, 39,
            38, 34, 33, 31, 27, 24, 21, 14, 12, 6, 2, -2, -6, -7, -9, -10, -11, -12, -12, -12, -11, -11, -11, -11, -11, -11, -11, -11, -12, -12, -12, -12, -12,
            -13, -13, -12, -12, -12, -12, -12, -12, -12, -12, -11, -11, -11, -11, -10, -9, -10, -10, -10, -8, -8, -9, -10, -8, -8, -9, -9, -10, -10, -10, -10,
            -10, -11, -11, -11, -10, -10, -10, -10, -10, -9, -8, -9, -8, -8, -6, -7, -6, -5, -5, -5, -5, -4, -4, -5, -6, -6, -7, -7, -8, -9, -9, -9, -9, -9, -9,
            -9, -10, -9, -9, -9, -9, -9, -10, -12, -14, -15, -15, -9, -4, 4, 29, 47, 90, 114, 137, 175, 187, 194, 193, 183, 151, 128, 103, 54, 34, 17, -6, -15,
            -21, -22, -21, -16, -14, -12, -11, -12, -12, -13, -12, -12, -12, -12, -12, -12, -12, -12, -12, -13, -14, -13, -13, -13, -11, -11, -9, -6, -5, -3, 1,
            4, 7, 9, 11, 15, 18, 19, 22, 25, 28, 29, 31, 34, 34, 36, 37, 38, 39, 39, 39, 39, 39, 38, 37, 36, 33, 30, 28, 23, 20, 17, 11, 8, 2, -1, -4, -7, -9, -10,
            -11, -11, -11, -12, -11, -11, -11, -11, -12, -12, -11, -11, -11, -11, -11, -11, -11, -11, -12, -11, -12, -11, -11, -11, -11, -11, -11, -11, -11, -10,
            -10, -10, -10, -9, -9, -9, -9, -9, -10, -10, -11, -11, -12, -12, -13, -12, -12, -12, -11, -10, -10, -10, -9, -8, -9, -9, -8, -8, -9, -9, -8, -8, -7, -7,
            -6, -7, -6, -6, -5, -5, -6, -6, -6, -6, -5, -6, -6, -7, -8, -8, -8, -8, -10, -11, -10, -11, -10, -10, -10, -10, -10, -12, -13, -15, -15, -15, -6, 2, 13,
            42, 64, 112, 136, 158, 189, 198, 199, 188, 174, 133, 107, 81, 35, 18, 4, -14, -19, -22, -22, -19, -15, -12, -13, -13, -13, -12, -13, -13, -13, -12, -12,
            -11, -12, -11, -11, -10, -11, -10, -10, -10, -9, -8, -8, -7, -6, -4, -2, 1, 3, 8, 10, 12, 15, 19, 21, 25, 27, 32, 33, 34, 38, 38, 39, 41, 42, 42, 42, 42,
            41, 40, 40, 37, 35, 31, 30, 27, 21, 18, 15, 8, 6, 0, -2, -4, -8, -9, -11, -12, -11, -12, -12, -13, -12, -11, -11, -10, -11, -10, -10, -10, -11, -12, -12,
            -13, -13, -14, -14, -13, -13, -13, -13, -13, -11, -11, -11, -10, -11, -11, -10, -10, -11, -11, -11, -11, -10, -11, -10, -10, -9, -9, -9, -8, -9, -9, -9,
            -9, -9, -10, -9, -9, -9, -9, -8, -9, -8, -8, -8, -8, -6, -7, -6, -5, -5, -6, -4, -5, -5, -5, -6, -7, -8, -8, -8, -9, -9, -10, -10, -10, -10, -9, -10, -10,
            -10, -10, -9, -10, -10, -11, -13, -16, -18, -17, -15, -3, 7, 20, 55, 78, 128, 151, 171, 195, 200, 198, 179, 162, 114, 89, 64, 24, 8, -4, -18, -22, -22, -19,
            -17, -12, -12, -11, -11, -11, -11, -10, -10, -11, -11, -11, -11, -12, -13, -13, -13, -13, -12, -13, -12, -11, -10, -7, -7, -4, -3, -1, 3, 5, 9, 11, 13, 19,
            21, 22, 27, 28, 32, 34, 34, 38, 38, 40, 41, 41, 42, 42, 42, 41, 41, 39, 37, 35, 31, 29, 25, 19, 16, 12, 7, 3, -2, -6, -7, -9, -11, -11, -13, -13, -13, -13,
            -12, -11, -11, -11, -11, -11, -11, -11, -11, -11, -11, -10, -12, -10, -11, -10, -11, -11, -11, -11, -12, -11, -12, -13, -13, -11, -11, -11, -10, -10, -11, -10,
            -10, -11, -10, -10, -11, -11, -11, -11, -11, -11, -11, -11, -11, -11, -11, -11, -11, -11, -11, -10, -10, -9, -9, -9, -8, -9, -8, -8, -8, -8, -8, -7, -7, -7,
            -7, -8, -7, -7, -7, -8, -9, -9, -9, -9, -10, -10, -10, -10, -11, -10, -10, -10, -9, -10, -10, -14, -15, -15, -14, -10, 7, 18, 35, 76, 99, 146, 165, 180, 195,
            196, 190, 162, 141, 93, 67, 45, 11, 0, -9, -19, -20, -17, -16, -13, -10, -10, -11, -11, -11, -12, -12, -12, -11, -11, -11, -10, -10, -9, -9, -9, -9, -8, -9,
            -7, -7, -6, -4, -4, -1, 0, 2, 5, 7, 12, 14, 16, 21, 23, 25, 29, 30, 33, 35, 37, 39, 39, 40, 41, 42, 42, 41, 40, 39, 38, 37, 33, 32, 27};


    public static void main(String[] args) throws Exception {
        File file = new File("D:/abc/ecg/origin.data");
        FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[24];
        StringBuilder builder = new StringBuilder();
        builder.append("{ ");
        int count = 0;
        while (inputStream.read(bytes) != -1) {
            int i = bytes[22] & 255 | bytes[23] << 8;
            if (count % 2 == 0) {
                builder.append(i + ", ");
            }
            count++;
        }
        builder.append(" }");
        System.out.println(" count: " + count);
        System.out.println(builder.toString());
    }




}
