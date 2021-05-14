package com.zsy.netty.protocol.client;

import com.zsy.netty.protocol.message.LsDecoder;
import com.zsy.netty.protocol.message.LsEncoder;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 客户端
 */
public class  Client {

    public void connect(int port, String host) throws Exception {
        // 配置客户端NIO线程组
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            // 配置启动辅助类
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new MyChannelHandler());
            // 异步连接服务器，同步等待连接成功
            ChannelFuture f = b.connect(host, port).sync();
            // 等待连接关闭
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        new Client().connect(8888, "127.0.0.1");
    }

// 客户端加入的协议编码/解码器
    public class MyChannelHandler extends ChannelInitializer<SocketChannel> {

        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            ch.pipeline().addLast(new LsEncoder());
            ch.pipeline().addLast(new LsDecoder());
            // 处理网络IO
            ch.pipeline().addLast(new ClientHandler());
        }
    }
}