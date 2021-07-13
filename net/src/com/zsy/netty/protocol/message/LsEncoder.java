package com.zsy.netty.protocol.message;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 自定义协议编码器（对象转为字节）
 *
 * @author stone
 * @date 2019/7/29 17:41
 */
public class LsEncoder extends MessageToByteEncoder<Protocol> {
    @Override
    public void encode(ChannelHandlerContext ctx, Protocol msg, ByteBuf out) throws Exception {
        System.out.println("编码转字节....................");
        out.writeInt(1);
        out.writeInt(msg.getHeader());
        out.writeInt(msg.getContentLength());
        out.writeBytes(msg.getContent());
    }
}