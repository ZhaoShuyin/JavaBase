package com.zsy.data.base64;

import com.zsy.data.base64.a.Base64UtilA;

/**
 * @Title com.zsy.data.base64
 * @date 2021/8/17
 * @autor Zsy
 */

class Test {

    public static void main(String[] args) throws Exception {
        String s = "eyJ1c2VySW5mbyI6eyJuYW1lIjoi5byg6aOO55C0IiwidXNlcklkIjoiZmY4MDgwODE3NTUwNDU5YzAxNzU1MzI0OGQ1YjAwMGMiLCJpZENhcmQiOiIiLCJhaWQiOjM3MDIwMDAwMDAxNSwicGF0aWQiOjIwMTgxMDAwMDc0Nn0sImFwcEluZm8iOnsidXJsU2NoZW1lIjoiYm9ob3U6Ly9hbmRyb2lkOjgwODgvZXh0ZXJuYWw/RWNnX1Jlc3BvbnNlPSJ9fQ==";
        byte[] decode = Base64UtilA.decode(s);
        System.out.println(new String(decode));
        String s1 = new Base64Util().decodetoString(s);
        System.out.println(s1);
    }


}
