package com.chen.ellen.im.core.handler;

import com.chen.ellen.im.core.ImClientMsgWrapper;
import com.chen.ellen.proto.S2CPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ImClientHandler extends ChannelInboundHandlerAdapter {

    private ChatReadListener chatReadListener;

    public ImClientHandler() {
    }

    public ImClientHandler(ChatReadListener chatReadListener) {
        this.chatReadListener = chatReadListener;
    }

    public void setChatReadListener(ChatReadListener chatReadListener) {
        this.chatReadListener = chatReadListener;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);

        if (chatReadListener != null) {
            S2CPacket packet = (S2CPacket) msg;
            chatReadListener.read(new ImClientMsgWrapper(packet));
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }

}
