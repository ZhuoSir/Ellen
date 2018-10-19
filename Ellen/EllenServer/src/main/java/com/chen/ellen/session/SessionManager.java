package com.chen.ellen.session;

import com.chen.ellen.im.core.session.ImGroup;
import com.chen.ellen.im.core.session.ImSession;
import io.netty.channel.ChannelHandlerContext;

public interface SessionManager {

    ImSession createSession(ChannelHandlerContext ctx);

    ImSession getSession(String sessionId);

    ImSession getSessionByAccountId(String accountId);

    void updateSession(ImSession imSession);

    void removeSession(String sessionId);

    void closeSession(String sessionId);

    void closeSession(ImSession imSession);

    ImGroup createGroup(ImSession creator);

    ImGroup getGroup(String groupId);

    void closeGroup(String groupId);

}
