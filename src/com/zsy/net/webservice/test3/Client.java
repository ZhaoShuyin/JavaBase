package com.zsy.net.webservice.test3;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Title com.zsy.webservice.test3
 */

class Client {

    public static void main(String[] args) {
        HelloWorldService service = new HelloWorldService();
        HelloWorld helloWorldPort = service.getHelloWorldPort();
        String s = helloWorldPort.sayHelloWorldFrom("1");
        System.out.println(s);
    }

   /* public static void main(String[] args) {
        System.out.println(getAge("19950102"));
        System.out.println(getAge("20090102"));
    }*/

    private static long YEAR = 31536000000l;

    public static int getAge(String date) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            Date parse = dateFormat.parse(date);
            long diffInMillis = Math.abs(new Date().getTime() - parse.getTime());
            return (int) (diffInMillis / YEAR);
//            long diff = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
