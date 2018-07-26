package com.chen.ellen.service.impl;

import com.chen.ellen.im.core.listener.ImSendListener;
import com.chen.ellen.im.core.message.IMessageWrapper;
import com.chen.ellen.im.core.service.ImServerResponse;
import com.chen.ellen.im.core.session.ImGroup;
import com.chen.ellen.im.core.session.Session;
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
    public void response(Session session, IMessageWrapper wrapper) {
        if (null == session || null == wrapper) return;

        try {
            CmdHandler cmdHandler = cmdHandlerManager.getCmdHandler(wrapper.getCmd());
            if (null != cmdHandler) {
                cmdHandler.handle(session, wrapper);
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            serverBusy(session);
        }

    }

    @Override
    public void requestSuccess(Session session) {
        Object resp = messageProxy.genRequestSuccResp();
        session.writeAndFlush(resp);
    }

    @Override
    public void requestFail(Session session) {

    }

    @Override
    public void serverBusy(Session session) {

    }

    @Override
    public void error(Session session) {

    }

    @Override
    public void pushMessage(Session session, IMessageWrapper wrapper) {
        C2SPacket c2SPacket = wrapper.getPacket();
        Session toSession   = sessionManager.getSession(wrapper.getReceiver());
        if (null != toSession && toSession.isConnect()) {
            S2CPacket s2CPacket = transMessage(c2SPacket);
            toSession.writeAndFlush(s2CPacket).addListener(new ImSendListener(session, c2SPacket));
        }
    }

    @Override
    public void pushGroupMessage(Session session, IMessageWrapper wrapper) {
        String groupId = wrapper.getGroupId();
        ImGroup imGroup = sessionManager.getGroup(groupId);
        C2SPacket c2SPacket = wrapper.getPacket();
        if (null != imGroup) {
            S2CPacket s2CPacket = transMessage(c2SPacket);
            List<ChannelFuture> futures = imGroup.sendGroupMessage(s2CPacket);
            if (null != futures && !futures.isEmpty()) {
//                future.addListener(new ImSendListener(session));
                for (ChannelFuture future : futures) {

                }
            }
        }
    }
}
