package com.zsy.java.io;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

/**
 * Title com.zsy.java.io
 *
 * @author Zsy
 * @date 2019/8/10 18:55
 */
public class FileDemo {

    public static void main(String[] args) {
        File file = new File("files/iotest.txt");
        System.out.println(file.length() + "");
        File file1 = new File("files");
        //创建文件夹
        if (!file1.exists()) {
            boolean mkdir = file1.mkdir();
            System.out.println("创建文件夹" + mkdir);
        } else {
            System.out.println("文件夹已存在");
        }
        //创建文件
        File file2 = new File("files/tst.txt");
        if (!file2.exists()) {
            try {
                file2.createNewFile();
//                file2.delete();//删除文件,也可以删除(空)文件夹
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("文件已存在");
        }
        /**
         * File类中和判断功能相关的方法:
         * 		public boolean isDirectory(): 判断是否是目录
         public boolean isFile():		      判断是否是文件
         public boolean exists():		      判断是否存在
         public boolean canRead():		      判断是否可读
         public boolean canWrite():		      判断是否可写
         public boolean isHidden():		      判断是否隐藏
         */

        /**
         * File类中和获取功能相关的方法:
         * public String getAbsolutePath(): 		获取绝对路径
         * public String getPath():					获取相对路径
         * public String getName(): 				获取文件名称
         * public long length(): 					获取文件长度 ,单位是字节
         * public long lastModified():				获取最后一次修改时间
         *
         * public String[] list():					获取指定目录下所有的文件和文件夹对应的名称数组
         * public File[] listFiles():				获取指定目录下所有的文件和文件夹对应的File数组
         */
        System.out.println("获取绝对路径 " + file2.getAbsolutePath());
        System.out.println("获取相对路径 " + file2.getPath());
        System.out.println("获取文件名称 " + file2.getName());
        System.out.println("最近修改时间 " + file2.lastModified());
        String[] list = file1.list();
        for (int i = 0; i < list.length; i++) {
            System.out.println("file1.list(): " + list[i]);
        }


        //
        File[] files = file1.listFiles(new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {
                return new File(dir, name).isFile() && name.endsWith(".txt");
            }
        });
        for (File f : files) {
            System.out.println("txt文件有 "+f.getName());
        }
    }
}
