package com.zsy.netty.protocol.client;

import com.zsy.netty.protocol.message.Protocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * 客户端Handler
 * @author
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {

    /**
     * 客户端一旦与服务端建立好连接，就会触发该方法
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        String data = "我是客户端";          // 发送消息

        byte[] content = data.getBytes();   // 获取要发送信息的字节数组

        int contentLength = content.length; // 要发送信息的长度

        Protocol protocol = new Protocol(contentLength, content);
        ctx.writeAndFlush(protocol);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            // 用于获取客户端发来的数据信息
            Protocol body = (Protocol) msg;
            System.out.println("Client接收的客户端的信息：" + body.toString());
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}