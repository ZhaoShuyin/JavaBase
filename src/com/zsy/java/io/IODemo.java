package com.zsy.java.io;

import com.zsy.java.io.sample.Student;

import java.io.*;
import java.util.Random;

/**
 * Title com.zsy.java.io
 * .
 * .   InputStream{                     //字节输入流
 * .       FileInputStream{             //<直接>读取
 * .            FilterInputStream{
 * .                BufferedInputStream://缓冲流(FileInput)
 * .                DataInputStream:    //数据输入流
 * .            }
 * .        }
 * .       ByteArrayInputStream:        //内存操作流,(使用内存为暂存区)关闭此流无效
 * .       ObjectInputStream:           //对象输入流(操作对象)(InputStream)
 * .   }
 * .   OutputStream{                     //字节输出流
 * .        FileOutputStream{            //<直接>写入
 * .            FilterOutputStream{
 * .                BufferedOutputStream://缓冲流(FileOutput)
 * .                DataOutputStream:    //数据输入流(使用内存为暂存区)
 * .             }
 * .        PrintStream:                 //<直接>显示流
 * .        }
 * .       ByteArrayOutputStream:       //内存操作流,(使用内存为暂存区)并且关闭此流无效
* .       ObjectOutputStream:           //对象输入流(操作对象)(OutputStream)
 * .   }
 * .
 * .
 * .    Reader{                          //字符输入流
 * .        InputStreamReader{           //字节流通向字符流的桥梁(FileInput)
 * .                FileReader:         //<直接>操作
 * .             }
 * .        BufferedReader:             //字符缓冲流(FileReader)
 * .    }
 * .    Writer{                         //字符输出流
 * .        OutputStreamWriter{         //字符流通向字节流的桥梁(FileOutput)
 * .                FileWriter:         //<直接>操作
 * .             }
 * .        BufferedWriter:             //字符缓冲流(FileWriter)
 * .   }
 * .
 * .   RandomAccessFile:               //随机访问流(快捷处理文件)
 * .
 *
 * @author Zsy
 * @date 2019/8/10 19:15
 */
public class IODemo {
    public static void main(String[] args) {
        //fileStreamTest();      //文件输入输出流
        //copyFile();            //拷贝文件
        //bufferStreamTest();    //字节缓冲流
        readWriterTest();      //转转输入流
        //fileReadWriterTest();  //字符流
        //bufferReadWriterTest();//字符缓冲流
        //dataStream();          //数据流
        //ByteArrayTest();       //内存操作流
        //printTest();           //方便快捷输出数据
        //randomStreatTest();      //随机访问流
        //objectTest();            //对象操作流
    }

    /**
     * 对象操作流
     */
    private static void objectTest() {
        try{
        FileOutputStream fos = new FileOutputStream("files/object.txt") ;
        ObjectOutputStream oos = new ObjectOutputStream(fos) ;
        Student sIn = new Student("张三" , 23) ;
        oos.writeObject(sIn) ;
        fos.close() ;
        FileInputStream fis = new FileInputStream("files/object.txt") ;
        ObjectInputStream ois = new ObjectInputStream(fis) ;
        Student sOut = (Student)ois.readObject() ;
        System.out.println(sOut.getName() + "---" + sOut.getAge());
        ois.close() ;
        fis.close() ;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * “r”：以只读的方式打开，调用该对象的任何write（写）方法都会导致IOException异常
     * “rw”：以读、写方式打开，支持文件的读取或写入。若文件不存在，则创建之。
     * “rws”：以读、写方式打开，与“rw”不同的是，还要对文件内容的每次更新都同步更新到潜在的存储设备中去。
     * 这里的“s”表示synchronous（同步）的意思
     * “rwd”：以读、写方式打开，与“rw”不同的是，还要对文件内容的每次更新都同步更新到潜在的存储设备中去。
     * 使用“rwd”模式仅要求将文件的内容更新到存储设备中，
     * 而使用“rws”模式除了更新文件的内容，还要更新文件的元数据（metadata），因此至少要求1次低级别的I/O操作
     */
    private static void randomStreatTest() {
        try {
            File file = new File("files/random.txt");
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            for (int i = 0; i < 5; i++) {
                raf.write("line\n".getBytes());
                System.out.println(i+" - "+raf.getFilePointer());
            }
            raf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile("files/random.txt", "r");
            //raf.skipBytes(12);//跳过12字节
            //raf.seek(12);指定指针位置
            String line;
            while ((line = randomAccessFile.readLine())!=null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 1: 打印流只能操作目的地不能操作数据源
     * 2: 如果启动了自动刷新,那么在调用println、printf 或 format 的其中一个方法时才可能完成此操作
     * 3: 可以操作任意的数据
     * 4: 是可以直接操作文件的流对象
     */
    private static void printTest() {
        try {
            PrintWriter pw = new PrintWriter("files/print.txt");
            pw.write("哈哈");
            pw.write("\r\n");
            pw.write("哈哈");
            pw.write("\r\n");
            pw.write("呵呵");
            pw.write("\r\n");
            pw.write("嘻嘻");
            pw.write("\r\n");
            pw.write("嘿嘿");
            // 释放资源
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * 在创建ByteArrayOutputStream类实例时，
     * 内存中会创建一个byte数组类型的缓冲区，
     * 缓冲区会随着数据的不断写入而自动增长
     */
    private static void ByteArrayTest() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            baos.write("hello".getBytes());
            baos.write(" world".getBytes());
            byte[] bytes = baos.toByteArray();
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            byte[] newByte = new byte[1024];
            int len = 0;
            while ((len = bais.read(newByte)) != -1) {
                System.out.println(new String(newByte, 0, len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 进行基本数据的操作
     */
    private static void dataStream() {
        try {
            DataOutputStream dos = new DataOutputStream(new FileOutputStream("files/data.txt"));
            dos.writeInt(1);
            dos.writeChar('a');
            dos.writeDouble(123456d);
            dos.writeUTF("中文");
            dos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            DataInputStream dis = new DataInputStream(new FileInputStream("files/data.txt"));
            System.out.println(dis.readInt() + "");
            System.out.println(dis.readChar() + "");
            System.out.println(dis.readLong() + "");
            System.out.println(dis.readUTF() + "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void bufferReadWriterTest() {
        try {
            FileReader fileReader = new FileReader("files/iotest.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            char[] chs = new char[1024];
            int len = 0;                // 作用: 记录读取到的有效的字符个数
            while ((len = bufferedReader.read(chs)) != -1) {
                System.out.print(new String(chs, 0, len));
            }
            bufferedReader.close();
            fileReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            FileWriter fileWriter = new FileWriter("files/iotest.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int i = 0; i < 20; i++) {
                bufferedWriter.write("字符转换流");
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void fileReadWriterTest() {
        try {
            FileReader fileReader = new FileReader("files/iotest.txt");
            char[] chs = new char[1024];
            int len = 0;                // 作用: 记录读取到的有效的字符个数
            while ((len = fileReader.read(chs)) != -1) {
                System.out.print(new String(chs, 0, len));
            }
            fileReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            FileWriter fileWriter = new FileWriter("files/iotest.txt");
            for (int i = 0; i < 20; i++) {
                fileWriter.write("fileWriter\n");
            }
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void readWriterTest() {
        try {
            FileInputStream fileInputStream = new FileInputStream("files/iotest.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            // 一次读取一个字符数组
            char[] chs = new char[1024];
            int len = 0;                // 作用: 记录读取到的有效的字符个数
            while ((len = inputStreamReader.read(chs)) != -1) {
                System.out.print(new String(chs, 0, len));
            }
            inputStreamReader.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("files/iotest.txt");
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
            for (int i = 0; i < 20; i++) {
                outputStreamWriter.write("转换输入流 \n");
            }
            outputStreamWriter.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void bufferStreamTest() {
        //
        try {
            FileInputStream fileInputStream = new FileInputStream("files/iotest.txt");
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            // 一次读取一个字节数组
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = bufferedInputStream.read(bytes)) != -1) {
                System.out.print(new String(bytes, 0, len));
            }
            bufferedInputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("files/iotest.txt");
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            byte[] bytes = "文字\n".getBytes();
            for (int i = 0; i < 20; i++) {
                bufferedOutputStream.write(bytes, 0, bytes.length);
            }
            bufferedOutputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void copyFile() {
        try {
            FileInputStream fis = new FileInputStream("files/tst.txt");
            FileOutputStream fos = new FileOutputStream("files/iotest.txt");
            // 一次读取一个字节,一次写一个字节
           /* int by = 0 ;
            while((by = fis.read()) != -1){
                fos.write(by) ;
            }*/
            // 一次读取一个字节数组复制文件
            byte[] bytes = new byte[1024];
            int len = 0;        // 作用: 记录读取到的有效的字节个数
            while ((len = fis.read(bytes)) != -1) {
                fos.write(bytes, 0, len);
            }
            fos.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void fileStreamTest() {
        //文件输入流读取
        try {
            FileInputStream fileInputStream = new FileInputStream(getFile());
            //int read = fileInputStream.read();//读取到有效的字节个数
            byte[] bytes = new byte[128];
            int len = 0;
            while ((len = fileInputStream.read(bytes)) != -1) {
                String s = new String(bytes, 0, len);
                System.out.print("bytes==" + bytes + "\n string==" + s);
            }
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //文件输出流写入
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(getFile());
            String s = String.valueOf(System.currentTimeMillis()) + "\n";
            byte[] bytes = s.getBytes();
            for (int i = 0; i < 50; i++) {
                fileOutputStream.write(bytes, 0, bytes.length);
            }
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static File getFile() {
        return new File("files/iotest.txt");
    }
}
