package com.zsy.net.webservice;

import com.zsy.net.webservice.service.WeatherInterfaceImpl;
import com.zsy.net.webservice.client.WeatherInterfaceImplService;

public class WeatherClient {

    public static void main(String[] args) {
        //创建服务视图，视图是从wsdl文件的service标签的name属性获取
        WeatherInterfaceImplService weatherInterfaceImplService
                = new WeatherInterfaceImplService();

        //获取服务实现类，实现类从wsdl文件的portType的name属性获取
        WeatherInterfaceImpl weatherInterfaceImpl
                = weatherInterfaceImplService.getPort(WeatherInterfaceImpl.class);
        //获取查询方法，从portType的operation标签获取
        String weather = weatherInterfaceImpl.queryWeather("北京");
        System.out.println(weather);

    }

}