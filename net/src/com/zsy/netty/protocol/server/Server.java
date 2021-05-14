package com.zsy.netty.protocol.server;

import com.zsy.netty.protocol.message.LsDecoder;
import com.zsy.netty.protocol.message.LsEncoder;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * 服务端
 */
public class Server {

    public Server() {
    }

    /**
     * 绑定端口
     */
    public void bind(int port) throws Exception {
        // 配置IO线程组

        //处理socket连接的线程就是Boos线程   //parentGroup
        EventLoopGroup bossGroup = new NioEventLoopGroup();    //parentGroup

        //channel交给Worker线程来处理
        EventLoopGroup workerGroup = new NioEventLoopGroup();  //childGroup

        try {
            // 服务器辅助启动类配置
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)         // 指定使用的channel
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChildChannelHandler())
                    .option(ChannelOption.SO_BACKLOG, 1024) // 设置tcp缓冲区
                    .option(ChannelOption.SO_KEEPALIVE, true);
            // 绑定端口，同步等待绑定成功
            ChannelFuture f = b.bind(port).sync();
            // 等待服务端监听端口关闭
            f.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    /**
     * 服务端加入的协议编码/解码器
     */
    public class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            // 添加自定义协议编码工具
            ch.pipeline().addLast(new LsEncoder());    //协议编码器
            ch.pipeline().addLast(new LsDecoder());    //协议解码器
            // 处理网络IO
            ch.pipeline().addLast(new ServerHandler());//
        }
    }

    public static void main(String[] args) throws Exception {
        new Server().bind(9999);
    }
}