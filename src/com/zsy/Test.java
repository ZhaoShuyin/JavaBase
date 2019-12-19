package com.zsy;

import javafx.scene.input.DataFormat;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
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

        /*int count = 4;
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
        }*/


        /*int[] ints = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(Arrays.toString(conver(ints, 0.1f)));*/
        //2019-11/05 09:33:56.000
        //2019-11/05 09:34:56.000
       /* Double aFloat1 = Double.valueOf("20191105093356.000");
        System.out.println(aFloat1 + "");
        Double aFloat2 = Double.valueOf("20191105093456.000");
        System.out.println(aFloat2 + "");

        System.out.println(aFloat2 + " - " + aFloat1 + " = " + (aFloat2 - aFloat1));*/
        /*DataFormat dataFormat = new DataFormat();
        String s = "20191105093356.000";
        String substring = s.substring(0,14);
        System.out.println(substring);*/

        //yyyy-MM-dd HH:mm:ss
        /*SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            Date date = formatter.parse("20191105093356");
            System.out.println(date.getTime()+"");
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }*/


       /* int i = 0x0100 | 0x4000;
        System.out.println(i+"");*/
//        System.out.println( Integer.toHexString('聸'));


        final long start = System.nanoTime();

        File file = new File("C:/Users/pengyang/Desktop/apk");
        final long total = getTotalSizeOfFilesInDir(file);
        final long end = System.nanoTime();
        System.out.println("Total Size: " + total);
        System.out.println("Time taken: " + (end - start) / 1.0e9);

        boolean delete = delFile(file, file.getName());
        System.out.println("< " + file.getName() + "> 删除 : " + delete);

    }

    public static boolean delFile(File file, String name) {
        if (!file.exists()) {
            return false;
        }

        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                delFile(f, name);
            }
        }
        return file.getName().equals(name) || file.delete();
//        return file.delete();
    }

    private static int[] conver(int[] origin, float scale) {
        if (scale == 1 || scale <= 0) {
            return origin;
        }
        int number = (int) (origin.length / scale);
        int[] ints = new int[number];
        for (int i = 0; i < number; i++) {
            ints[i] = origin[(int) (i * scale)];
        }
        return ints;
        /*if (scale < 1) { //对数据进行扩
            return enlarge(origin, scale);
        } else {        //对数据进行缩
            return shrink(origin, scale);
        }*/
    }


    public static long getTotalSizeOfFilesInDir(final File file) {
        if (file.isFile())
            return file.length();
        final File[] children = file.listFiles();
        long total = 0;
        if (children != null)
            for (final File child : children)
                total += getTotalSizeOfFilesInDir(child);
        return total;
    }




}
