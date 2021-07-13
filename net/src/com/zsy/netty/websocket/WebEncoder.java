package com.zsy.netty.websocket;

import com.zsy.netty.protocol.message.Protocol;
import com.zsy.util.HexUtil;

import java.util.Date;
import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrameEncoder;

/**
 * @Title com.zsy.netty.websocket
 * @date 2021/6/15
 * @autor Zsy
 */

public class WebEncoder extends MessageToMessageEncoder<MessageStrust> implements WebSocketFrameEncoder {
    /*  @Override
      protected void encode(ChannelHandlerContext ctx, WebSocketFrame frame, List<Object> out) throws Exception {
          System.out.println("WebEndcoder  ============== frame:"+frame+"   ==============   out:"+out);
          ByteBuf buf = ((BinaryWebSocketFrame) frame).content();
          out.add(buf);
          buf.retain();
      }*/


    @Override
    protected void encode(ChannelHandlerContext ctx, MessageStrust msg, List<Object> out) throws Exception {

        BinaryWebSocketFrame frame = new BinaryWebSocketFrame();
        ByteBuf buffer = frame.content();
        buffer.clear();
        System.out.println("WebEndcoder  ============== msg:" + msg + "   ==============   out:" + out.getClass().getName() + " " + System.identityHashCode(out) + "  buffer:" + System.identityHashCode(buffer));

//        ByteBuf buffer = ctx.alloc().buffer();
        buffer.writeBytes(msg.message);
//        TextWebSocketFrame tws = new TextWebSocketFrame(new Date().toString()
//                + ctx.channel().id() + "：" + msg.getMessage());
        out.add(frame);
    }
}
