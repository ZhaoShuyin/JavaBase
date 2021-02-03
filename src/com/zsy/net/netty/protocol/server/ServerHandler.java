package com.zsy.net.netty.protocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author stone
 * @date 2019/7/30 9:33
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 用于获取客户端发来的数据信息
        Protocol body = (Protocol) msg;
        System.out.println("Server接收到的客户端信息：" + body.toString());

        // 写数据给客户端
        String str = "Hi I am Server ...";
        Protocol response = new Protocol(str.getBytes().length, str.getBytes());
        // 当服务端完成写操作后，关闭与客户端的连接
        ctx.writeAndFlush(response);

        // 有写操作时，不需要手动释放msg的引用; 当只有读操作时，才需要手动释放msg的引用
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("Server exceptionCaught");
        cause.printStackTrace();
//        if (ctx.channel().isActive()) {
//            ctx.channel().writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
//        }
        ctx.close();
    }
}