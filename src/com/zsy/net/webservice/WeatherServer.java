package com.zsy.net.webservice;

import com.zsy.net.webservice.service.WeatherInterfaceImpl;

import javax.xml.ws.Endpoint;

public class WeatherServer {
    public static void main(String[] args) {
        Endpoint.publish("http://127.0.0.1:12345/weather", new WeatherInterfaceImpl());
    }
}