package com.zsy.netty.protocol.coder;

/**
 * @Title com.zsy.netty.protocol.coder
 * @date 2021/6/1
 * @autor Zsy
 */

class Test {
    public static byte[] bytes1 = {
            0x00, 0x01, 0x02, 0x03, 0x04
    };
    public static void main(String[] args) {
        int i = bytes1[0] << 32 | bytes1[1] << 16 | bytes1[2] << 8 | bytes1[3] & 255;
        System.out.println(i);
    }

}
