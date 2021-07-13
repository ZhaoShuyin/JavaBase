package com.zsy.netty.websocket;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * 服务端
 */
public class WebSocketService {

    public String TAG = " WebSocketService ================== ";


    /**
     * 绑定端口开启服务
     */
    public void bind(int port) throws Exception {
        //处理socket连接的线程
        EventLoopGroup bossGroup = new NioEventLoopGroup();    //parentGroup
        //channel Worker线程
        EventLoopGroup workerGroup = new NioEventLoopGroup();  //childGroup
        try {
            System.out.println(TAG + "启动服务端");
            // 服务器辅助启动类配置
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)         // 指定使用的channel
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            //设置log监听器，并且日志级别为debug，方便观察运行流程
//                            socketChannel.pipeline().addLast("logging", new LoggingHandler("DEBUG"));

                            ChannelPipeline pipeline = socketChannel.pipeline();

                            pipeline.addLast(new HttpServerCodec());                              //设置解码器
                            pipeline.addLast(new HttpObjectAggregator(65536));  //聚合器，使用websocket会用到

                            pipeline.addLast(new WebDecoder());
                            pipeline.addLast(new WebEncoder());

//                            pipeline.addLast(new WebSocketServerCompressionHandler());
//                            pipeline.addLast(new WebSocketServerProtocolHandler("WEBSOCKET_PATH", null, true));
//                            pipeline.addLast(new ProtobufVarint32FrameDecoder());
//                            pipeline.addLast(new ProtobufDecoder(MyProtoBufType.getDefaultInstance()));
//                            pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
//                            pipeline.addLast(new ProtobufEncoder());

                            //自定义的业务handler
                            pipeline.addLast( new WebSocketHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 8192)                    // 设置tcp缓冲区
                    .option(ChannelOption.SO_KEEPALIVE, true);
            // 绑定端口，同步等待绑定成功
            ChannelFuture f = b.bind(port).sync();
            System.out.println(TAG + " 启动成功");
            // 等待服务端监听端口关闭
            f.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

//        WebSocketFrameEncoder
    }

    public static void main(String[] args) throws Exception {
        WebSocketService webSocketService = new WebSocketService();
        webSocketService.bind(9999);
    }

}