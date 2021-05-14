/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.zsy.netty.base.client;

//import org.ych.techDemo.netty.encoder.Header;
//import org.ych.techDemo.netty.encoder.Message;

import com.zsy.netty.base.bean.Header;
import com.zsy.netty.base.bean.Message;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Handler implementation for the echo client.  It initiates the ping-pong
 * traffic between the echo client and server by sending the first message to
 * the server.
 */
public class EchoClientHandler extends ChannelInboundHandlerAdapter {

//    private final ByteBuf firstMessage;

    /**
     * Creates a client-side handler.
     *
     * @throws Exception
     */

    //客户端连接服务器后调用，简单的就ctx.writeAndFlush(ByteBuf)，复杂点就自定义编解码器
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive");
        //ctx.writeAndFlush(Unpooled.copiedBuffer("hello world",CharsetUtil.UTF_8));

        String content = "hello world,this is netty client";
        Header header = new Header((byte) 0, (byte) 1, (byte) 1, (byte) 1, (byte) 0, "713f17ca614361fb257dc6741332caf2", content.getBytes("UTF-8").length, 1);
        Message message = new Message(header, content);
        ctx.writeAndFlush(message);

    }

    //接收到数据后调用
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {

        Message msg1 = (Message) msg;

        System.out.println(msg1.getData());
    }

    //完成时调用
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        System.out.println("channelReadComplete");
        ctx.flush();
    }

    //发生异常时调用
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}