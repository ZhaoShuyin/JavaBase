package com.zsy.pdf;

import java.util.Arrays;

/**
 * @Title com.zsy.pdf
 * @date 2021/5/25
 * @autor Zsy
 */

public class Test2 {

    public static void main(String[] args) {
        float[][] xPoints; //横向的
        float[][] yPoints; //纵向的

        int mW = 10;
        int mH = 10;
        int mGridSize = 2;

        int hSize = mH / mGridSize * 2;
        xPoints = new float[mW][hSize];
        for (int i = 0; i < mW; i++) {
            for (int j = 0; j < hSize / 2; j++) {
                xPoints[i][2 * j] = i;
                xPoints[i][2 * j + 1] = j * mGridSize;
            }
        }
        for (int i = 0; i < xPoints.length; i++) {
            System.out.println(Arrays.toString(xPoints[i]));
        }

        System.out.println("====================================================================================================");

        int wSize = mW / mGridSize * 2;
        yPoints = new float[mH][wSize];
        for (int i = 0; i < mH; i++) {
            for (int j = 0; j < wSize / 2; j++) {
                yPoints[i][2 * j + 1] = i;
                yPoints[i][2 * j] = j * mGridSize;
            }
        }
        for (int i = 0; i < yPoints.length; i++) {
            System.out.println(Arrays.toString(yPoints[i]));
        }


    }

}
