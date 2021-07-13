package com.zsy.netty.protocol.coder;

import java.util.Arrays;
import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

public class IntegerHeaderFrameDecoder extends ReplayingDecoder<Void> {

    public String TAG = " Decoder ================== ";

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> out) throws Exception {
        int length = buf.readInt();
        System.out.println(TAG+" length: "+length);
        ByteBuf bytes = buf.readBytes(length);
        byte[] array = bytes.array();
        System.out.println(TAG+" "+ Arrays.toString(array));
        out.add(bytes);
    }
}