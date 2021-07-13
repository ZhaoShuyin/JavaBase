package com.zsy.netty.protocol.message;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
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

    public String TAG = " 服务端 ================== ";

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
            System.out.println(TAG+"启动服务端");
            // 服务器辅助启动类配置
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)         // 指定使用的channel
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            // 添加自定义协议编码工具
                            socketChannel.pipeline().addLast(new LsEncoder());    //协议编码器
                            socketChannel.pipeline().addLast(new LsDecoder());    //协议解码器
                            // 处理网络IO
                            socketChannel.pipeline().addLast(new ServerHandler());//
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 1024) // 设置tcp缓冲区
                    .option(ChannelOption.SO_KEEPALIVE, true);
            // 绑定端口，同步等待绑定成功
            ChannelFuture f = b.bind(port).sync();
            System.out.println(TAG+"绑定端口 启动服务");
            // 等待服务端监听端口关闭
            f.channel().closeFuture().sync();
            System.out.println(TAG+"绑定端口 启动完毕");
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public class ServerHandler extends ChannelInboundHandlerAdapter {

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            // 用于获取客户端发来的数据信息
            Protocol body = (Protocol) msg;
            System.out.println("Server接收到的客户端信息：" + body.toString());

            // 写数据给客户端
            String str = "我是服务端,接到消息了";
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

    public static void main(String[] args) throws Exception {
        new Server().bind(9999);
    }
}