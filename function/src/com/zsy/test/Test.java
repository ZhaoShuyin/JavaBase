package com.zsy.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;

/**
 * @Title com.zsy.test
 * @date 2021/5/26
 * @autor Zsy
 */

public class Test {

    public static void main(String[] args) throws Exception {
        File file = new File("D:/abc/origin.data");
        File txt = new File("D:/abc/origin.txt");
        FileInputStream inputStream = new FileInputStream(file);
        FileWriter writer = new FileWriter(txt);
        byte[] read = new byte[2];
        while (inputStream.read(read) != -1) {
            writer.write(String.valueOf(read[0] & 255 | read[1] << 8));
            writer.write(" ");
        }
        inputStream.close();
        writer.close();
        System.out.println("==");
    }

}
