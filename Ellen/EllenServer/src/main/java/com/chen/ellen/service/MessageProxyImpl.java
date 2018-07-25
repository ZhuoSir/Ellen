package com.chen.ellen.service;

import com.chen.ellen.im.core.message.IMessageWrapper;
import com.chen.ellen.im.core.proxy.MessageProxy;
import com.chen.ellen.proto.C2SPacket;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Component;

@Component
public class MessageProxyImpl implements MessageProxy {

    @Override
    public IMessageWrapper convert2MessageWrapper(String sessionId, ChannelHandlerContext ctx, C2SPacket packet) {

        return new IMessageWrapper(packet, ctx, sessionId);
    }

    public Object genRequestSuccResp() {

        return null;
    }
}
