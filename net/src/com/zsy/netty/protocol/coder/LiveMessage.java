package com.zsy.netty.protocol.coder;

/**
 * @Title com.zsy.netty.protocol.coder
 * @date 2021/5/27
 * @autor Zsy
 */

public class LiveMessage {

    public int length = 5;

    public String content;

    public int getLength() {
        return length;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "{ length:" + length + " , content:" + content + " }";
    }
}
