package com.zsy.test;

/**
 * @Title com.pengyang.ecg.bean.wave
 * @date 2019/11/4
 * @autor Zsy
 */
public class EcgBean {

    public static final String TAG_I = "MDC_ECG_LEAD_I";
    public static final String TAG_II = "MDC_ECG_LEAD_II";
    public static final String TAG_III = "MDC_ECG_LEAD_III";
    public static final String TAG_AVR = "MDC_ECG_LEAD_AVR";
    public static final String TAG_AVL = "MDC_ECG_LEAD_AVL";
    public static final String TAG_AVF = "MDC_ECG_LEAD_AVF";
    public static final String TAG_V1 = "MDC_ECG_LEAD_V1";
    public static final String TAG_V2 = "MDC_ECG_LEAD_V2";
    public static final String TAG_V3 = "MDC_ECG_LEAD_V3";
    public static final String TAG_V4 = "MDC_ECG_LEAD_V4";
    public static final String TAG_V5 = "MDC_ECG_LEAD_V5";
    public static final String TAG_V6 = "MDC_ECG_LEAD_V6";

    private String[] strings = new String[]{"I", "II", "III", "AVR", "AVL", "AVF", "V1", "V2", "V3", "V4", "V5", "V6"};

    public float low;         //录制开始
    public float high;        //录制结束
    public int duration;      //录制时长

    public int gain = 10;     //数据增益(每个导联数据,单位值 : 小单元格高度个数)

    public int speed;         //走纸速度
    public String model;      //显示模式

    public int[] I;
    public int[] II;
    public int[] III;
    public int[] AVR;
    public int[] AVL;
    public int[] AVF;
    public int[] V1;
    public int[] V2;
    public int[] V3;
    public int[] V4;
    public int[] V5;
    public int[] V6;

    /**
     *
     * @param number
     */
    public void conver(int number) {
        conversion(I, number);
        conversion(II, number);
        conversion(III, number);
        conversion(AVR, number);
        conversion(AVL, number);
        conversion(AVF, number);
        conversion(V1, number);
        conversion(V2, number);
        conversion(V3, number);
        conversion(V4, number);
        conversion(V5, number);
        conversion(V6, number);
    }


    private void conversion(int[] origin, int number) {
        int[] ints = I;
        I = new int[ints.length];
        for (int j = 0; j < ints.length; j++) {
            if (j % number == 0) {
                I[j / number] = getTotal(ints, number);
            }
        }
    }

    private int getTotal(int[] ints, int number) {
        int total = 0;
        for (int i = 0; i < number; i++) {
            total += ints[i + number];
        }
        return total;
    }


}
