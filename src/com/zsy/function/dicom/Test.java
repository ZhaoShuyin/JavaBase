package com.zsy.function.dicom;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Sequence;
import org.dcm4che3.io.DicomEncodingOptions;
import org.dcm4che3.io.DicomInputStream;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * @Title com.zsy.function.dicom
 * @date 2021/5/6
 * @autor Zsy
 */

public class Test {

    public static int tag(int group, int element) {
        return group << 16 | element;
    }

    public static void main(String[] args) {

        /*int number = 12;
        int length = 10;
        byte[] bytes = new byte[number * length * 2];

        int[][] ints = new int[number][length];

        System.out.println("bytes长度 "+bytes.length);

        int index = 0;
        for (int j = 0; j < length; j++) {
            index = number * 2 * j;
            System.out.println(" index ===================================== " + index);
            for (int i = 0; i < number; i++) {
                ints[i][j] = bytes[index + 2 * i] & 255 | bytes[index + 2 * i + 1] << 8;
                System.out.println("ints[" + i + "][" + j + "] = [" + (index + 2 * i) + "] | [" + (index + 2 * i + 1) + "] ");
            }
        }*/

        try {
        DicomInputStream dis = new DicomInputStream(new File("D:/Projects/ECGL.dcm"));
        Attributes attrs = dis.readDataset(-1, -1);
        Attributes dataset = attrs.getNestedDataset(tag(0x5400, 0x0100));
        Sequence sequence = dataset.getSequence(tag(0x003A, 0x0200));

            int size = sequence.size();
            String[] strings = new String[size];
            float[] floats = new float[size];
            for (int i = 0; i < size; i++) {
                Attributes attributes = sequence.get(i);
                strings[i] = attributes.getString(tag(0x003a, 0x0203));
                floats[i] = attributes.getFloat(tag(0x003a, 0x0210),1);
                int anInt = attributes.getInt(tag(0x003a, 0x021a), 16);
                System.out.println(anInt);
            }
            System.out.println(Arrays.toString(strings));
            System.out.println(Arrays.toString(floats));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
