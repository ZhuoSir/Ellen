package com.chen.ellen.im.core.server.codec;

import com.chen.ellen.im.core.seria.ProtostuffUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MessageEncoder extends MessageToByteEncoder<Object> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out)
            throws Exception {
        byte[] array = ProtostuffUtils.serializer(msg);
        out.writeBytes(array);
    }
}
