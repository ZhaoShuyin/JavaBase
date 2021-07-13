package com.zsy.netty.websocket;

import java.util.List;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

/**
 * @Title com.zsy.netty.websocket
 * @date 2021/6/15
 * @autor Zsy
 */

public class WebDecoder extends MessageToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, Object msg, List out) throws Exception {
        System.out.println("WebDecoder  **********   msg:" + msg.getClass().getName());
        out.add(msg);
    }
}
