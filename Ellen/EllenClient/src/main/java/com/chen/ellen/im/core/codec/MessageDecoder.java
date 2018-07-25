package com.chen.ellen.im.core.codec;

import com.chen.ellen.im.core.seria.ProtostuffUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

public class MessageDecoder extends MessageToMessageDecoder<ByteBuf> {

    private Class<?> generateClass;

    public MessageDecoder(Class<?> generateClass) {
        this.generateClass = generateClass;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out)
            throws Exception {

        int length = msg.readableBytes();
        if (length < 4) {
            return;
        }

        byte[] array = new byte[length];
        msg.readBytes(array);

        Object obj = ProtostuffUtils.deserializer(array, generateClass);
        out.add(obj);
    }
}
