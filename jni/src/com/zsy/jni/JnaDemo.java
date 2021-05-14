package com.zsy.jni;

import com.sun.jna.Library;
import com.sun.jna.Native;

/**
 * 使用Jna
 */
public class JnaDemo {

    public interface CLibrary extends Library{
        //此处我的jdk版本为64位,故加载64位的Dll
        CLibrary INSTANCE = (CLibrary)Native.loadLibrary("jni/src/com/zsy/jni/Demo_dll_64",CLibrary.class);
        //Dll2x64中定义的函数
        int add(int a,int b);
    }
    
    public static void main(String[] args) {
        System.out.println("3+4= Add(3,4) = "+CLibrary.INSTANCE.add(3,4));
        for (int i = 0; i < 100; i++) {
            System.out.println(CLibrary.INSTANCE.add(100,i));
        }
    }
}