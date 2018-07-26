package com.chen.ellen.session;

import com.chen.ellen.im.core.session.ImGroup;
import com.chen.ellen.im.core.session.Session;
import io.netty.channel.ChannelHandlerContext;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionManagerImpl implements SessionManager {

    private Logger logger = Logger.getLogger(SessionManagerImpl.class);

    private static Map<String, Session> sessionMap = new ConcurrentHashMap<>();

    private static Map<String, ImGroup> groupMap = new ConcurrentHashMap<>();

    @Override
    public Session createSession(ChannelHandlerContext ctx) {
        Session session = new Session();
        session.setStatus(Session.StatusCode.ONLINE);
        session.setMChannel(ctx.channel());
        session.setSessionId(generateSessionId());
        sessionMap.putIfAbsent(session.getSessionId(), session);
        return session;
    }

    @Override
    public Session getSession(String sessionId) {
        if (null == sessionId) return null;
        return sessionMap.get(sessionId);
    }

    @Override
    public Session getSessionByAccountId(String accountId) {
        Set<String> keys = sessionMap.keySet();
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            Session session = sessionMap.get(key);
            if (accountId.equals(session.getAccountId())) {
                return session;
            }
        }

        return null;
    }

    @Override
    public void updateSession(Session session) {
        if (null != session) {
            sessionMap.put(session.getSessionId(), session);
        }
    }

    @Override
    public void removeSession(String sessionId) {
        sessionMap.remove(sessionId);
    }

    @Override
    public void closeSession(String sessionId) {
        Session session = getSession(sessionId);
        closeSession(session);
    }

    public void closeSession(Session session) {
        if (null != session && session.isConnect()) {
            session.close();
            removeSession(session.getSessionId());
        }
    }

    @Override
    public ImGroup createGroup(Session creator) {
        ImGroup imGroup = new ImGroup(creator);
        imGroup.setCreateTime(new Date());
        imGroup.setGroupName(creator.getAccountId() + "于" + imGroup.getCreateTime() + "发起的聊天组");
        imGroup.setGroupId(generateSessionId());

        if (groupMap.putIfAbsent(imGroup.getGroupId(), imGroup) == null) {
            logger.info("创建Group 成功 :" + imGroup);
        }

        return imGroup;
    }


    @Override
    public ImGroup getGroup(String groupId) {
        return groupMap.get(groupId);
    }

    @Override
    public void closeGroup(String groupId) {
        groupMap.remove(groupId);
    }

    private String generateSessionId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
//        return "31dsd1231foijijei121hufh1u1";
    }
}
