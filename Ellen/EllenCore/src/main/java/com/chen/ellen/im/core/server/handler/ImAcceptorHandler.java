package com.chen.ellen.im.core.server.handler;

import com.chen.ellen.im.core.message.IMessageWrapper;
import com.chen.ellen.im.core.proxy.MessageProxy;
import com.chen.ellen.im.core.service.ServerRespService;
import com.chen.ellen.im.core.session.ImConnect;
import com.chen.ellen.im.core.session.Session;
import com.chen.ellen.proto.C2SPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.log4j.Logger;

public class ImAcceptorHandler extends ChannelInboundHandlerAdapter {

    private Logger logger = Logger.getLogger(ImAcceptorHandler.class);

    private ImConnect imConnect;

    private ServerRespService serverRespService;

    private MessageProxy messageProxy;

    public ImAcceptorHandler() {
    }

    public ImAcceptorHandler(ImConnect imConnect, ServerRespService serverRespService, MessageProxy messageProxy) {
        this.imConnect = imConnect;
        this.serverRespService = serverRespService;
        this.messageProxy = messageProxy;
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
            Session session = imConnect.getSession(ctx);
            IMessageWrapper wrapper = messageProxy.convert2MessageWrapper(session.getSessionId(), ctx, packet);
            serverRespService.response(session, wrapper);
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

    public void setServerRespService(ServerRespService serverRespService) {
        this.serverRespService = serverRespService;
    }

    public void setMessageProxy(MessageProxy messageProxy) {
        this.messageProxy = messageProxy;
    }
}
