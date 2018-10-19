package com.chen.ellen.service.impl;

import com.chen.ellen.im.core.listener.ImSendListener;
import com.chen.ellen.im.core.message.IMessageWrapper;
import com.chen.ellen.im.core.service.ImServerResponse;
import com.chen.ellen.im.core.session.ImGroup;
import com.chen.ellen.im.core.session.ImSession;
import com.chen.ellen.proto.C2SPacket;
import com.chen.ellen.proto.S2CPacket;
import com.chen.ellen.service.ImMessageProxyImpl;
import com.chen.ellen.service.handlers.CmdHandler;
import com.chen.ellen.service.handlers.CmdHandlerManager;
import com.chen.ellen.session.SessionManager;
import io.netty.channel.ChannelFuture;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.chen.ellen.proto.ProtoTranslator.transMessage;


/**
 *
 * Created by sunny-chen on 2018/1/22.
 */
@Component
public class ServerResponseImpl implements ImServerResponse {

    private Logger logger = Logger.getLogger(ServerResponseImpl.class);

    @Autowired
    private SessionManager sessionManager;

    @Autowired
    private CmdHandlerManager cmdHandlerManager;

    @Autowired
    private ImMessageProxyImpl messageProxy;

    @Override
    public void response(ImSession imSession, IMessageWrapper wrapper) {
        if (null == imSession || null == wrapper) return;

        try {
            CmdHandler cmdHandler = cmdHandlerManager.getCmdHandler(wrapper.getCmd());
            if (null != cmdHandler) {
                cmdHandler.handle(imSession, wrapper);
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            serverBusy(imSession);
        }

    }

    @Override
    public void requestSuccess(ImSession imSession) {
        Object resp = messageProxy.genRequestSuccResp();
        imSession.writeAndFlush(resp);
    }

    @Override
    public void requestFail(ImSession imSession) {

    }

    @Override
    public void serverBusy(ImSession imSession) {

    }

    @Override
    public void error(ImSession imSession) {

    }

    @Override
    public void pushMessage(ImSession imSession, IMessageWrapper wrapper) {
        C2SPacket c2SPacket = wrapper.getPacket();
        ImSession toImSession = sessionManager.getSession(wrapper.getReceiver());
        if (null != toImSession && toImSession.isConnect()) {
            S2CPacket s2CPacket = transMessage(c2SPacket);
            toImSession.writeAndFlush(s2CPacket).addListener(new ImSendListener(imSession, toImSession, c2SPacket));
        }
    }

    @Override
    public void pushGroupMessage(ImSession imSession, IMessageWrapper wrapper) {
        String groupId = wrapper.getGroupId();
        ImGroup imGroup = sessionManager.getGroup(groupId);
        C2SPacket c2SPacket = wrapper.getPacket();
        if (null != imGroup) {
            S2CPacket s2CPacket = transMessage(c2SPacket);
            List<ChannelFuture> futures = imGroup.sendGroupMessage(s2CPacket);
//            if (null != futures && !futures.isEmpty()) {
////                future.addListener(new ImSendListener(imSession));
//                for (ChannelFuture future : futures) {
//
//                }
//            }
        }
    }
}
