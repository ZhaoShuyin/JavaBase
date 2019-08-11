package com.zsy.java.io;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.*;

/**
 * Title com.zsy.java.io
 *
 * @author Zsy
 * @date 2019/8/11 22:17
 */
public class ZipIoTest {

    public static void main(String[] args)  {
//        zipTest();//压缩文件
        //zipsTest();//多文件压缩
        try {
            unzipsTest();//多文件解压
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void unzipsTest() throws Exception {
        FileInputStream fi = new FileInputStream("files/files.zip");
        CheckedInputStream ci = new CheckedInputStream(fi, new Adler32());
        ZipInputStream zi = new ZipInputStream(ci);
        BufferedInputStream bi = new BufferedInputStream(zi);

        /*ZipEntry ze;//逐个检测压缩包内文件
        while ((ze = zi.getNextEntry()) != null) {//
            String name = ze.getName();
            int filenameIndex = name.lastIndexOf("/");
            String filename = name.substring(filenameIndex + 1, name.length());
            System.out.println("压缩包内文件名 " + filename);
        }*/
        ZipFile zipFile = new ZipFile("files/files.zip");
        Enumeration entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            ZipEntry zipEntry = (ZipEntry) entries.nextElement();
            String name = zipEntry.getName();
            int filenameIndex = name.lastIndexOf("/");
            String filename = name.substring(filenameIndex + 1, name.length());
            System.out.println("压缩包内文件名 " + filename);
            File file = new File("files/unzip/" + filename);
            if (!file.exists()) {
                file.createNewFile();
            }
            InputStream fileIn = zipFile.getInputStream(zipEntry);
            FileOutputStream fo = new FileOutputStream(file);
            int x;
            while ((x = fileIn.read()) != -1) {
                fo.write(x);
            }
            fo.close();
            fileIn.close();
        }
        bi.close();
        zi.close();
        ci.close();
        fi.close();
    }

    /**
     * Adler32 ,快,不准确
     * CRC32  ,不快,准确
     */
    private static void zipsTest() throws Exception {
        FileOutputStream fileOutputStream = new FileOutputStream("files/files.zip");
        CheckedOutputStream checkedOutputStream = new CheckedOutputStream(fileOutputStream, new Adler32());
        ZipOutputStream zipOutputStream = new ZipOutputStream(checkedOutputStream);

        zipOutputStream.setComment("files test");

        String[] filesName = new String[]{"files/tst.txt", "files/random.txt"};

        for (String file : filesName) {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            zipOutputStream.putNextEntry(new ZipEntry(file));
            int len;
            while ((len = bufferedReader.read()) != -1) {
                zipOutputStream.write(len);
            }
            bufferedReader.close();
            fileReader.close();
        }

        zipOutputStream.close();
        checkedOutputStream.close();
        fileOutputStream.close();
    }

    /**
     * 压缩流 压缩文件
     *
     * @throws IOException
     */
    private static void zipTest() throws IOException {
        FileReader fileReader = new FileReader("files/tst.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        FileOutputStream fileOutputStream = new FileOutputStream("files/test.gz");
        GZIPOutputStream gzipOutputStream = new GZIPOutputStream(fileOutputStream);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(gzipOutputStream);
        int y;
        while ((y = bufferedReader.read()) != -1) {
            bufferedOutputStream.write(y);
        }

        bufferedOutputStream.close();
        gzipOutputStream.close();
        fileOutputStream.close();

        bufferedReader.close();
        fileReader.close();
    }

}
