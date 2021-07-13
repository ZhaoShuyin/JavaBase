package com.zsy.netty.websocket;

import com.zsy.util.HexUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.util.CharsetUtil;

import static io.netty.handler.codec.http.HttpUtil.isKeepAlive;

public class WebSocketHandler extends SimpleChannelInboundHandler<Object> {

    private static String TAG = "WebSocket  *****  ";


    private WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory("ws://localhost:6666", null, false);
    private Map<ChannelHandlerContext, WebSocketServerHandshaker> handshakerMap = new HashMap<>();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //添加连接
        System.out.println(TAG + "<channelActive> 客户端加入连接：" + ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        //断开连接
        System.out.println(TAG + "<channelInactive> 客户端断开连接：" + ctx.channel());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {

        if (msg instanceof FullHttpRequest) {               //以http请求形式接入，但是走的是websocket

            System.out.println(TAG + " <FullHttpRequest> (Http请求形式的) 收到消息：");
            handleHttpRequest(ctx, (FullHttpRequest) msg);

        } else if (msg instanceof PingWebSocketFrame) {     // 判断是否ping消息

            System.out.println(TAG + " fram : <PingWebSocketFrame> ");
            ctx.channel().write(new PongWebSocketFrame(((PingWebSocketFrame) msg).content().retain()));

        } else if (msg instanceof CloseWebSocketFrame) {    // 判断是否关闭链路的指令

            System.out.println(TAG + " fram : <CloseWebSocketFrame> ");
            WebSocketServerHandshaker handshaker = handshakerMap.get(ctx);
            if (handshaker != null) {
                System.out.println("四次挥手");
                handshaker.close(ctx.channel(), ((CloseWebSocketFrame) msg).retain());
                handshakerMap.remove(ctx);
            }

        } else if (msg instanceof TextWebSocketFrame) {         //处理text消息

            System.out.println(TAG + " fram : <TextWebSocketFrame> ");
            onMessage(ctx, ((TextWebSocketFrame) msg).text());

        } else if (msg instanceof BinaryWebSocketFrame) {       //处理byte消息

            System.out.println(TAG + " fram : <BinaryWebSocketFrame> ");
            ByteBuf byteBuf = ((BinaryWebSocketFrame) msg).content();
            byte[] bytes = new byte[byteBuf.readableBytes()];
            byteBuf.readBytes(bytes);
            onMessage(ctx, bytes);

        } else {
            System.out.println(TAG + " msg ==> " + msg);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    /**
     * @param ctx
     * @param message
     */
    private void onMessage(ChannelHandlerContext ctx, String message) {
      /*  TextWebSocketFrame tws = new TextWebSocketFrame(new Date().toString()
                + ctx.channel().id() + "：" + message);*/
        MessageStrust strust = new MessageStrust("AABBCCDDEEFF", "服务端收到：" + message);
        ctx.channel().writeAndFlush(strust);
    }

    /**
     * @param ctx
     * @param bytes
     */
    private void onMessage(ChannelHandlerContext ctx, byte[] bytes) {
        System.out.println(HexUtil.encodeHexStr(bytes));
        ByteBuf buffer = ctx.alloc().buffer(6);
        buffer.writeInt(1);
        buffer.writeInt(2);
        buffer.writeInt(3);
        BinaryWebSocketFrame socketFrame = new BinaryWebSocketFrame(buffer);
        ctx.channel().writeAndFlush(socketFrame);
    }


    /**
     * 唯一的一次http请求，用于创建websocket
     */
    private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest httpRequest) {
        if (!httpRequest.decoderResult().isSuccess() || (!"websocket".equals(httpRequest.headers().get("Upgrade")))) {
            sendHttpResponse(ctx, httpRequest, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }

        WebSocketServerHandshaker handshaker = wsFactory.newHandshaker(httpRequest);
        if (handshaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        } else {
            System.out.println("三次握手");
            ChannelFuture handshake = handshaker.handshake(ctx.channel(), httpRequest);
            System.out.println("ctx:" + ctx.channel() + "  handshake:" + handshake);
            handshakerMap.put(ctx, handshaker);
        }
    }

    /**
     * 拒绝不合法的请求，并返回错误信息
     */
    private static void sendHttpResponse(ChannelHandlerContext ctx,
                                         FullHttpRequest req, DefaultFullHttpResponse res) {
        // 返回应答给客户端
        if (res.status().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(res.status().toString(), CharsetUtil.UTF_8);
            res.content().writeBytes(buf);
            buf.release();
        }
        ChannelFuture f = ctx.channel().writeAndFlush(res);
        // 如果是非Keep-Alive，关闭连接
        if (!isKeepAlive(req) || res.status().code() != 200) {
            f.addListener(ChannelFutureListener.CLOSE);
        }
    }
}