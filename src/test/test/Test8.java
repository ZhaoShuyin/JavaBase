package test.test;

import java.util.Random;

/**
 * @Title com.zsy.test
 * @date 2019/12/23
 * @autor Zsy
 */

class Test8 {
    public static void main(String[] args) {
        String colorCode = getRandColorCode();

        System.out.println(colorCode);
    }


    public static String getRandColorCode(){
        String r,g,b;
        Random random = new Random();
        r = Integer.toHexString(random.nextInt(256)).toUpperCase();
        g = Integer.toHexString(random.nextInt(256)).toUpperCase();
        b = Integer.toHexString(random.nextInt(256)).toUpperCase();

        r = r.length()==1 ? "0" + r : r ;
        g = g.length()==1 ? "0" + g : g ;
        b = b.length()==1 ? "0" + b : b ;

        return r+g+b;
    }
}
