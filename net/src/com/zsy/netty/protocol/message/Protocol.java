package com.zsy.netty.protocol.message;

import java.io.UnsupportedEncodingException;

/**
 * @author stone
 * @date 2019/7/29 17:39
 */
public class Protocol {
    /**
     * 消息头标志
     */
    private int header = 0X76;

    /**
     * 消息长度
     */
    private int contentLength;

    /**
     * 消息内容
     */
    private byte[] content;

    public Protocol(int contentLength, byte[] content) {
        this.contentLength = contentLength;
        this.content = content;
    }

    public int getContentLength() {
        return contentLength;
    }

    public void setContentLength(int contentLength) {
        this.contentLength = contentLength;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public int getHeader() {
        return header;
    }

    @Override
    public String toString() {
        try {
            return "MyLsProtocol{" +
                    "header=" + header +
                    ", contentLength=" + contentLength +
                    ", content=" + new String(content, "utf-8") +
                    '}';
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }
}