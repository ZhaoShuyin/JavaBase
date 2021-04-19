package com.zsy.net.netty.base.client;
 
//import org.ych.techDemo.netty.encoder.MessageDecoder;
//import org.ych.techDemo.netty.encoder.MessageEncoder;

import com.zsy.net.netty.base.bean.MessageDecoder;
import com.zsy.net.netty.base.bean.MessageEncoder;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
 
/**
 * Sends one message when a connection is open and echoes back any received
 * data to the server.  Simply put, the echo client initiates the ping-pong
 * traffic between the echo client and server by sending the first message to
 * the server.
 */
public final class EchoClient {
 
    static final boolean SSL = System.getProperty("ssl") != null;
    static final String HOST = System.getProperty("host", "127.0.0.1");
    static final int PORT = Integer.parseInt(System.getProperty("port", "8007"));
    static final int SIZE = Integer.parseInt(System.getProperty("size", "256"));
 
    public static void main(String[] args) throws Exception {
    	System.out.println("EchoClient.main");
        // Configure SSL.git
        final SslContext sslCtx;
        if (SSL) {
            sslCtx = SslContextBuilder.forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE).build();
        } else {
            sslCtx = null;
        }
 
        // Configure the client.
        /*创建一个Bootstrap b实例用来配置启动客户端
         * b.group指定NioEventLoopGroup来处理连接，接收数据
         * b.channel指定通道类型
         * b.option配置参数
         * b.handler客户端成功连接服务器后就会执行
         * b.connect客户端连接服务器
         * b.sync阻塞配置完成并启动
        */
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
             .channel(NioSocketChannel.class)
             //.option(ChannelOption.TCP_NODELAY, true)
             .handler(new ChannelInitializer<SocketChannel>() {
                 @Override
                 public void initChannel(SocketChannel ch) throws Exception {
                     ChannelPipeline pipeline = ch.pipeline();
                     if (sslCtx != null) {
                         pipeline.addLast(sslCtx.newHandler(ch.alloc(), HOST, PORT));
                     }
                     //pipeline.addLast(new LoggingHandler(LogLevel.INFO));
                     pipeline.addLast(new MessageDecoder());
                     pipeline.addLast(new MessageEncoder());
                     //pipeline.addLast("encoder", new MessageEncoder());
                  	 //pipeline.addLast("decoder", new MessageDecoder());
                 	 //pipeline.addFirst(new LineBasedFrameDecoder(65535));
                     pipeline.addLast(new EchoClientHandler());
                 }
             });
 
            // Start the client.
            ChannelFuture f = b.connect(HOST, PORT).sync();
            System.out.println("EchoClient.main ServerBootstrap配置启动完成");
 
            // Wait until the connection is closed.
            f.channel().closeFuture().sync();
        	System.out.println("EchoClient.end");
        } finally {
            // Shut down the event loop to terminate all threads.
            group.shutdownGracefully();
        }
    }
}