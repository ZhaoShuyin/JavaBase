package com.zsy.jni;

import org.xvolks.jnative.JNative;
import org.xvolks.jnative.Type;

/**
 * @Title com.zsy.jni
 * @date 2021/4/19
 * @autor Zsy
 * 使用jNative.jar
 * 必须使用 JDK32位 同时调用32位dll
 */
public class JNativeDemo {

    public static void main(String[] args) {
        try {
            System.setProperty("jnative.debug", "false");  //添加这行 可以打出jnative内部调试信息
            //如果报错含有System.LoadLibrary()，这说没有找到  JNativeCpp.dll 路径，可以用以下方式强制设置
            //System.setProperty("jnative.loadNative","D:\\workspace_gws_c++\\testDllJava\\src\\com\\ks\\Demo_dll.dll");
            //原文链接：https://blog.csdn.net/l09120204/article/details/78839432

            System.out.println("000------000");
            String userDir = System.getProperty("user.dir")+ "/jni/src/com/zsy/jni/";
            System.out.println("11111-----11");
            System.load(userDir.concat("Demo_dll_32.dll"));

//            System.out.println("11111-----11");
//            System.loadLibrary("D:/Projects/CPP/DllCall/Demo_dll.dll");

            System.out.println("222------2");
            JNative n = new JNative("Demo_dll_32", "add");
            n.setRetVal(Type.INT);
            int i = 0;
            n.setParameter( i++, Type.INT,"111");
            n.setParameter( i++, Type.INT,"222");
            n.invoke();
            System.out.println("3333--------");
            String x=n.getRetVal();
            System.out.println("444-----x =  "+x);

            //System.out.println(GetSystemTime());
        } catch (Exception e){
            e.printStackTrace();
        }
//        System.exit(0);
    }

}
