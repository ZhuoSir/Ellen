package com.chen.ellen.im.core.server.handler;

import com.chen.ellen.im.core.message.IMessageWrapper;
import com.chen.ellen.im.core.proxy.ImMessageProxy;
import com.chen.ellen.im.core.service.ImServerResponse;
import com.chen.ellen.im.core.session.ImConnect;
import com.chen.ellen.im.core.session.ImSession;
import com.chen.ellen.proto.C2SPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.log4j.Logger;

public class ImAcceptorHandler extends ChannelInboundHandlerAdapter {

    private Logger logger = Logger.getLogger(ImAcceptorHandler.class);

    private ImConnect imConnect;

    private ImServerResponse imServerResponse;

    private ImMessageProxy imMessageProxy;

    public ImAcceptorHandler() {
    }

    public ImAcceptorHandler(ImConnect imConnect, ImServerResponse imServerResponse, ImMessageProxy imMessageProxy) {
        this.imConnect = imConnect;
        this.imServerResponse = imServerResponse;
        this.imMessageProxy = imMessageProxy;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        imConnect.connect(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);

        if (msg instanceof C2SPacket) {
            C2SPacket packet = (C2SPacket) msg;
            logger.info("服务端接收消息:" + packet.toString());
            ImSession imSession = imConnect.getSession(ctx);
            IMessageWrapper wrapper = imMessageProxy.convert2MessageWrapper(imSession.getSessionId(), ctx, packet);
            imServerResponse.response(imSession, wrapper);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        cause.printStackTrace();
    }

    public void setImConnect(ImConnect imConnect) {
        this.imConnect = imConnect;
    }

    public void setImServerResponse(ImServerResponse imServerResponse) {
        this.imServerResponse = imServerResponse;
    }

    public void setImMessageProxy(ImMessageProxy imMessageProxy) {
        this.imMessageProxy = imMessageProxy;
    }
}
