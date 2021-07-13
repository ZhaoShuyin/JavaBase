package com.zsy.netty.protocol.message;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.ReferenceCountUtil;

/**
 * 客户端
 */
public class  Client {

    public String TAG = "客户端 ====================== ";

    public void connect(int port, String host) throws Exception {
        System.out.println(TAG+"开始连接");
        // 配置客户端NIO线程组
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            // 配置启动辅助类
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new LsEncoder());
                            socketChannel.pipeline().addLast(new LsDecoder());
                            // 处理网络IO
                            socketChannel.pipeline().addLast(new ClientHandler());
                        }
                    });
            // 异步连接服务器，同步等待连接成功
            ChannelFuture f = b.connect(host, port).sync();
            // 等待连接关闭
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }

    public class ClientHandler extends ChannelInboundHandlerAdapter {

        /**
         * 客户端一旦与服务端建立好连接，就会触发该方法
         */
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {

            byte[] content = "我是客户端".getBytes();   // 获取要发送信息的字节数组
            int contentLength = content.length;         // 要发送信息的长度
            Protocol protocol = new Protocol(contentLength, content);
            ctx.writeAndFlush(protocol);
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            try {
                // 用于获取客户端发来的数据信息
                Protocol body = (Protocol) msg;
                System.out.println(TAG+"Client接收的客户端的信息：" + body.toString());
            } finally {
                ReferenceCountUtil.release(msg);
            }
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            ctx.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Client client = new Client();
        client.connect(9999, "127.0.0.1");
    }
}