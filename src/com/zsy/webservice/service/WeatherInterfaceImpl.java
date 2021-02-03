package com.zsy.webservice;

import javax.jws.WebService;

//@WebService表示该类是一个服务类，需要发布其中的public的方法
@WebService
public class WeatherInterfaceImpl implements WeatherInterface {

    @Override
    public String queryWeather(String cityName) {
        System.out.println("获取城市名"+cityName);
        String weather="暴雨";    
        return weather;
    }

}