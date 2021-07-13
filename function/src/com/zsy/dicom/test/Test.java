package com.zsy.dicom.test;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;

/**
 * @Title com.zsy.dicom.test
 * @date 2021/5/25
 * @autor Zsy
 */

class Test {

    public static void main(String[] args) throws Exception {
       /* File file = new File("D:/abc/gain/origin.c8k");
        System.out.println("文件大小: "+file.length());
        turnB(file);*/

        /*File file = new File("D:/abc/gain/origin.data");
        System.out.println("文件大小: "+file.length());
        turnA(file);*/

        File file = new File("D:/abc/gain/6604321052600011/origin.c8k");
        turn(file,0.5f);
        turn(file,0.8f);
        turn(file,0.9f);
        turn(file,1.0f);
        turn(file,1.1f);
        turn(file,1.125f);
        turn(file,1.2f);
        turn(file,1.5f);
        turn(file,2.0f);
    }

    private static void turn(File file,float gain) throws Exception {
        FileInputStream inputStream = new FileInputStream(file);
        int length = (int) file.length();
        byte[] bytes = new byte[length / 2];
        int index = 0;
        int number = 0;
        byte[] read = new byte[4];
        while (inputStream.read(read) != -1) {
            number = (read[0] & 255 | read[1] << 8) - 0x800;
            bytes[index++] = (byte) (number & 255);
            bytes[index++] = (byte) (number >> 8);
        }
        DicomInfo dicomInfo = new DicomInfo(bytes, 12, gain);
        final File target = new File("D:/abc/gain/6604321052600011/ECGL-"+dicomInfo.leadsGain[0]+".dcm");
        boolean b = DicomUtil.dicomFile(dicomInfo, target);
        System.out.println(dicomInfo.leadsGain[0]);
       /* System.out.println(b);
        if (b){
            UploadDicom.upload("6604321052600011", target, new UploadDicom.UploadListener() {
                @Override
                public void callback(boolean respond, String msg) {
                    System.out.println("respond:"+respond+" , msg: "+msg);
                }
            });
        }*/
    }

    private static void turnA(File file) throws Exception {
        long size = 0;
        int[] ints = new int[12];
        int position = 0;
        FileInputStream inputStream = new FileInputStream(file);
        int number = 0;
        byte[] read = new byte[2];
        while (inputStream.read(read) != -1) {
            number = (read[1] & 255 | read[0] << 8) - 0x800;
            if (position >= 12) {
                position = 0;
                System.out.println("turn: " + Arrays.toString(ints));
                size++;
            }
            ints[position++] = number;
        }

        System.out.println("size: " + size);
    }


    private static void turnB(File file) throws Exception {

        long size = 0;

        int[] ints = new int[12];

        int position = 0;
        FileInputStream inputStream = new FileInputStream(file);
        int length = (int) file.length();
        byte[] bytes = new byte[length / 2];
        int index = 0;
        int number = 0;
        byte[] read = new byte[4];
        while (inputStream.read(read) != -1) {
            number = (read[0] & 255 | read[1] << 8) - 0x800;

            if (position >= 12) {
                position = 0;
                System.out.println("turn: " + Arrays.toString(ints));
                size++;
            }
            ints[position++] = number;
        }

        System.out.println("size: " + size);
    }


}
