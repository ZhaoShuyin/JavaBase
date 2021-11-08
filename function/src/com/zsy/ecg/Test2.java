package com.zsy.ecg;

import java.util.Arrays;

/**
 * @Title com.zsy.ecg
 * @date 2021/8/6
 * @autor Zsy
 */

class Test2 {


    private static int[] waveform = new int[400];

    static {
        int length = waveform.length;
        int part = length / 4;
        float max = 100f;
        float rate = 100 / part;
        float startY = 0;
        for (int i = 0; i < 4; i++) {
            switch (i) {
                case 0:
                    rate = max / part;
                    startY = 0;
                    break;
                case 1:
                    rate = -max / part;
                    startY = max;
                    break;
                case 2:
                    rate = -max / part;
                    startY = 0;
                    break;
                case 3:
                    rate = max / part;
                    startY = -max;
                    break;
            }
            for (int j = 0; j < part; j++) {
                waveform[part * i + j] = (int) (startY + j * rate);
            }
        }
    }


    public static void main(String[] args) {
        System.out.println(Arrays.toString(waveform));
    }

}
