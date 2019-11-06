package com.zsy;

import java.util.Arrays;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @Title com.zsy
 * @Date 2019/10/24
 * @Autor Zsy
 */
public class Test {


    public static void main(String[] args) {
        /*String s = "123-abc";
        String[] split = s.split("-");
        System.out.println(Arrays.toString(split));*/

       /* ConcurrentLinkedQueue<Short> queue = new ConcurrentLinkedQueue<>();
        for (int i = 0; i < 5; i++) {
            queue.offer((short) i);
        }
        for (int i = 0; i < 9; i++) {
            System.out.println(queue.poll());
        }*/

     /*   String abc = "01234567893215";
        String a = "3";
        System.out.println("位置: "+abc.indexOf(a));;*/

       /* System.out.println( Integer.toHexString('聴'));
        System.out.println( Integer.toHexString('聸'));*/

        /*System.out.println("90度 sin : "+Math.sin(Math.PI / 2));
        System.out.println("90度 cos : "+(double)Math.cos(Math.PI / 2));*/

        int count = 4;
        // 顶点的个数，我们分割count个三角形，有count+1个点，再加上圆心共有count+2个点
        float circleCoords[] = new float[(count + 1) * 2];
        // x y
        float r = 100;
        circleCoords[0] = 0;// 中心点
        circleCoords[1] = 0;
        double angle = Math.PI * 2 / count;
        for (int i = 0; i < count; i++) {
            int x = 2 * i + 2;
            int y = 2 * i + 3;
            circleCoords[x] = (float) ((int)(r * Math.sin(angle * i)))/100 ;
            circleCoords[y] = (float) ((int)(r * Math.cos(angle * i)))/100 ;
            System.out.println("<x: " + circleCoords[x] + " > < y:" + circleCoords[y] + ">");
        }

    }
}
