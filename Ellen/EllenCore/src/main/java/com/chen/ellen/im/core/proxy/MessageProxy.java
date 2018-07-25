package com.chen.ellen.im.core.proxy;

import com.chen.ellen.im.core.message.IMessageWrapper;
import com.chen.ellen.proto.C2SPacket;
import io.netty.channel.ChannelHandlerContext;

public interface MessageProxy {

    IMessageWrapper convert2MessageWrapper(String sessionId, ChannelHandlerContext ctx, C2SPacket packet);

    Object genRequestSuccResp();
}
