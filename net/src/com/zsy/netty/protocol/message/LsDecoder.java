package com.zsy.netty.protocol.message;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 自定义协议解码器 (字节转对象)
 *
 * @author stone
 * @date 2019/7/29 17:43
 */
public class LsDecoder extends ByteToMessageDecoder {
    /**
     * 报文开始的标志 header是int类型，占4个字节
     * 表示报文长度的contentLength是int类型，占4个字节
     */
    public final int BASE_LENGTH = 8;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        // 可读长度必须大于基本长度
        if (in.readableBytes() >= BASE_LENGTH) {
            // 防止socket字节流攻击
            // 防止客户端传来的数据过大（太大的数据是不合理的）
            if (in.readableBytes() > 2048) {
                in.skipBytes(in.readableBytes());
            }

            // 记录包头开始的index
            int beginReader;
            while (true) {
                // 获取包头开始的index
                beginReader = in.readerIndex();
                // 标记包头开始的index
                in.markReaderIndex();
                // 读到协议的开始标志，结束while循环
                if (in.readInt() == ConstantValue.HEAD_DATA) {
                    break;
                }

                // 未读到包头，跳过一个字节
                // 每次跳过一个字节后，再去读取包头信息的开始标记
                in.resetReaderIndex();
                in.readByte();

                // 当跳过一个字节后，数据包的长度又变的不满足，此时应该结束，等待后边数据流的到达
                if (in.readableBytes() < BASE_LENGTH) {
                    return;
                }
            }

            // 代码到这里，说明已经读到了报文标志

            // 消息长度
            int length = in.readInt();
            // 判断请求数据包是否到齐
            if (in.readableBytes() < length) { // 数据不齐，回退读指针
                // 还原读指针
                in.readerIndex(beginReader);
                return;
            }

            // 至此，读到一条完整报文
            byte[] data = new byte[length];
            in.readBytes(data);
            Protocol protocol = new Protocol(data.length, data);
            out.add(protocol);
        }
    }
}