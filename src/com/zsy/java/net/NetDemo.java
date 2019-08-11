package com.zsy.java.net;


import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Title Java网络通信
 *
 * @author Zsy
 * @date 2019/8/11 17:28
 */
public class NetDemo {

    public static void main(String[] args) {
        //javaNetBase();
    }

    private static void javaNetBase() {
        try {
            InetAddress inetAddress = InetAddress.getByName("ZSY");
            String hostAddress = inetAddress.getHostAddress();
            System.out.println("主机地址 "+hostAddress);
            String hostName = inetAddress.getHostName();
            System.out.println("主机名称 "+hostName);
            String canonicalHostName = inetAddress.getCanonicalHostName();
            System.out.println("主机滚翻名称 "+canonicalHostName);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

}
