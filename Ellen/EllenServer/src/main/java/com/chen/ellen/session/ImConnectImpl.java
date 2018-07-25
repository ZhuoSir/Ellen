package com.chen.ellen.session;

import com.chen.ellen.im.constant.Constant;
import com.chen.ellen.im.core.session.ImConnect;
import com.chen.ellen.im.core.session.Session;
import io.netty.channel.ChannelHandlerContext;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ImConnectImpl implements ImConnect {

    private Logger logger = Logger.getLogger(ImConnectImpl.class);

    @Autowired
    private SessionManagerImpl sessionManager;

    @Override
    public void connect(ChannelHandlerContext ctx) {
        logger.info("有新的连接，创建会话Session");
        Session session = sessionManager.createSession(ctx);
        logger.info("创建新的会话成功Session" + session);

        ctx.channel().attr(Constant.SessionConfig.SERVER_SESSION_ID).set(session.getSessionId());
    }

    @Override
    public void close(ChannelHandlerContext ctx) {
        if (null != ctx) {
            String sessionId = getSessionId(ctx);
            sessionManager.closeSession(sessionId);
        }
    }

    @Override
    public String getSessionId(ChannelHandlerContext ctx) {
        String sessionId = ctx.channel().attr(Constant.SessionConfig.SERVER_SESSION_ID).get();
        return sessionId;
    }

    @Override
    public Session getSession(ChannelHandlerContext ctx) {
        return sessionManager.getSession(getSessionId(ctx));
    }
}
