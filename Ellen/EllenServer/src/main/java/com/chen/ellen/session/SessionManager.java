package com.chen.ellen.session;

import com.chen.ellen.im.core.session.ImGroup;
import com.chen.ellen.im.core.session.Session;
import io.netty.channel.ChannelHandlerContext;

public interface SessionManager {

    Session createSession(ChannelHandlerContext ctx);

    Session getSession(String sessionId);

    Session getSessionByAccountId(String accountId);

    void updateSession(Session session);

    void removeSession(String sessionId);

    void closeSession(String sessionId);

    void closeSession(Session session);

    ImGroup createGroup(Session creator);

    ImGroup getGroup(String groupId);

    void closeGroup(String groupId);

}
