package com.chen.ellen.im.core.session;

import io.netty.channel.ChannelHandlerContext;

public interface ImConnect {

    void connect(ChannelHandlerContext ctx);

    void close(ChannelHandlerContext ctx);

    String getSessionId(ChannelHandlerContext ctx);

    ImSession getSession(ChannelHandlerContext ctx);

}
