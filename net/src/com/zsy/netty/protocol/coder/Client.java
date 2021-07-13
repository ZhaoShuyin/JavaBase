package com.zsy.netty.protocol.coder;

import com.zsy.netty.protocol.message.LsDecoder;
import com.zsy.netty.protocol.message.LsEncoder;
import com.zsy.netty.protocol.message.Protocol;

import java.nio.ByteBuffer;
import java.util.Timer;
import java.util.TimerTask;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
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
 * @Title com.zsy.netty.protocol.coder
 * @date 2021/6/1
 * @autor Zsy
 */

public class Client {

    public static String TAG = "客户端 ====================== ";
    private ChannelFuture channelFuture;
    private Channel channel;

    public void connect(int port, String host) throws Exception {
        System.out.println(TAG + "准备连接");
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
                            // 处理网络IO
                            socketChannel.pipeline().addLast(new ClientHandler());
                        }
                    });
            // 异步连接服务器，同步等待连接成功
            ChannelFuture channelFuture = b.connect(host, port).sync();

            System.out.println(TAG + "开始连接");
            // 等待连接关闭
            channel = channelFuture.channel();
            channel.closeFuture().sync();
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
                System.out.println(TAG + "Client接收的客户端的信息：" + body.toString());
            } finally {
                ReferenceCountUtil.release(msg);
            }
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            ctx.close();
        }
    }

    public void send(byte[] bytes) {
        ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
        writeBuffer.put(bytes);
        writeBuffer.flip();
        writeBuffer.rewind();
        ByteBuf buf = Unpooled.copiedBuffer(writeBuffer);   // 转为ByteBuf
        channel.writeAndFlush(buf);                         // 写消息到管道
        writeBuffer.clear();                                // 清理缓冲区
    }

    public static int index = 0;

    public static byte[] bytes1 = {
            0x00, 0x01, 0x02, 0x03, 0x04
    };

    public static byte[] bytes2 = {
            0x05, 0x06, 0x07, 0x08, 0x09
    };

    public static void main(String[] args) throws Exception {
        Client client = new Client();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    client.connect(9999, "127.0.0.1");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (index % 2 == 0) {
                    client.send(bytes1);
                    System.out.println(TAG+" send(bytes1)");
                } else {
                    client.send(bytes2);
                    System.out.println(TAG+" send(bytes2)");
                }
                index++;
            }
        }, 1000, 15000);
    }


}
