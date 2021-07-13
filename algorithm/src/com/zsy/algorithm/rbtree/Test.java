package com.zsy.algorithm.rbtree;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Title com.zsy.algorithm.rbtree
 * @date 2021/6/1
 * @autor Zsy
 */

public class Test {

    private static int[][] datas = new int[12][5000];
    private static int sampling = 500;

    private static int[][] pacemaker;

    /**
     * 设置心电起搏数据
     */
    public static void setPacemaker(int[][] pacemakers) {
        if (datas == null) {
            return;
        }
        pacemaker = new int[datas[0].length][2];
        float difmis = 0f;
        int last = 0;
        int index = 0;
        for (int i = 0; i < pacemakers.length; i++) {
            index = pacemakers[i][0];
            if (index >= pacemaker.length){
                continue;
            }
            //计算跟上次的差值
            difmis = index - last;
            //一分钟/(差值/采样率(相距时差)) = 心率
            last = index;
            if (i == 0) {
                continue;
            }
            pacemaker[index][0] = (int) (60f / (difmis / sampling));
            pacemaker[index][1] = pacemakers[i][1];
        }
    }

    /**
     *
     */
    public static void main(String[] args) {
        int[][] pacemakers = new int[10][2];
        for (int i = 0; i < 10; i++) {
            pacemakers[i][0] = i * 500;
            pacemakers[i][1] = i;
        }
        setPacemaker(pacemakers);
        for (int i = 0; i < pacemaker.length; i++) {
            if (pacemaker[i][0] != 0)
                System.out.println("i:"+i+" , 0: " + pacemaker[i][0] + " , 1: " + pacemaker[i][1]);
        }

    }

}
