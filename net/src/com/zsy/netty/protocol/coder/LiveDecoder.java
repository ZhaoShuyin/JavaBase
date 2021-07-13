package com.zsy.netty.protocol.coder;

import com.zsy.util.HexUtil;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

public class LiveDecoder extends ReplayingDecoder<LiveDecoder.LiveState> { //1

    public String TAG = " Decoder ================== ";

    public enum LiveState { //2
        LENGTH,
        CONTENT
    }

    private LiveMessage message = new LiveMessage();

    public LiveDecoder() {
        super(LiveState.LENGTH); // 3
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        switch (state()) { // 4
            case LENGTH:
                int length = byteBuf.readInt();
                if (length > 0) {
                    checkpoint(LiveState.CONTENT); // 5
                } else {
                    list.add(message);             // 6
                }
                System.out.println(TAG+" LENGTH : "+length);
                break;
            case CONTENT:
                byte[] bytes = new byte[message.getLength()];
                byteBuf.readBytes(bytes);
                String content = HexUtil.encodeHexStr(bytes);
                message.setContent(content);
                list.add(message);
                System.out.println(TAG+" CONTENT : "+content);
                break;
            default:
                throw new IllegalStateException("invalid state:" + state());
        }
    }
}