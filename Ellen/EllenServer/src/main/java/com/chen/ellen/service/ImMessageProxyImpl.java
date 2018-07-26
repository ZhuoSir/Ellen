package com.chen.ellen.service;

import com.chen.ellen.im.core.message.IMessageWrapper;
import com.chen.ellen.im.core.proxy.ImMessageProxy;
import com.chen.ellen.proto.C2SPacket;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Component;

@Component
public class ImMessageProxyImpl implements ImMessageProxy {

    @Override
    public IMessageWrapper convert2MessageWrapper(String sessionId, ChannelHandlerContext ctx, C2SPacket packet) {

        return new IMessageWrapper(packet, ctx, sessionId);
    }

    public Object genRequestSuccResp() {

        return null;
    }
}
