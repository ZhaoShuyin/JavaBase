package com.zsy.net.webservice.demo;

public class MobileClient {

    public static void main(String[] args) {
        //创建服务视图
        MobileCodeWS mobileCodeWS=new MobileCodeWS();
        //获取服务实现类
        MobileCodeWSSoap mobileCodeWSSoap= mobileCodeWS.getPort(MobileCodeWSSoap.class);
        //调用查询方法
        String message=mobileCodeWSSoap.getMobileCodeInfo("18610241234", null);
        System.out.println(message);

    }
}