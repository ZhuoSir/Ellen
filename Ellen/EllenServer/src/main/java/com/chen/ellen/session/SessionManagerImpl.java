package com.chen.ellen.session;

import com.chen.ellen.im.core.session.ImGroup;
import com.chen.ellen.im.core.session.ImSession;
import io.netty.channel.ChannelHandlerContext;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionManagerImpl implements SessionManager {

    private Logger logger = Logger.getLogger(SessionManagerImpl.class);

    private static Map<String, ImSession> sessionMap = new ConcurrentHashMap<>();

    private static Map<String, ImGroup> groupMap = new ConcurrentHashMap<>();

    @Override
    public ImSession createSession(ChannelHandlerContext ctx) {
        ImSession imSession = new ImSession();
        imSession.setStatus(ImSession.StatusCode.ONLINE);
        imSession.setMChannel(ctx.channel());
        imSession.setSessionId(generateSessionId());
        sessionMap.putIfAbsent(imSession.getSessionId(), imSession);
        return imSession;
    }

    @Override
    public ImSession getSession(String sessionId) {
        if (null == sessionId) return null;
        return sessionMap.get(sessionId);
    }

    @Override
    public ImSession getSessionByAccountId(String accountId) {
        Set<String> keys = sessionMap.keySet();
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            ImSession imSession = sessionMap.get(key);
            if (accountId.equals(imSession.getAccountId())) {
                return imSession;
            }
        }

        return null;
    }

    @Override
    public void updateSession(ImSession imSession) {
        if (null != imSession) {
            sessionMap.put(imSession.getSessionId(), imSession);
        }
    }

    @Override
    public void removeSession(String sessionId) {
        sessionMap.remove(sessionId);
    }

    @Override
    public void closeSession(String sessionId) {
        ImSession imSession = getSession(sessionId);
        closeSession(imSession);
    }

    public void closeSession(ImSession imSession) {
        if (null != imSession && imSession.isConnect()) {
            imSession.close();
            removeSession(imSession.getSessionId());
        }
    }

    @Override
    public ImGroup createGroup(ImSession creator) {
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
