package com.chen.ellen.im.core.session;

import com.chen.ellen.proto.S2CPacket;
import io.netty.channel.ChannelFuture;
import lombok.Data;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by sunny-chen on 2018/1/26.
 */
@Data
public class ImGroup {

    private Session creator;

    private String groupId;

    private String groupName;

    private Date createTime;

    /**
     * 会话组里的Session
     *
     * */
    ConcurrentHashMap<String, Session> sessionInGroup;

    public ImGroup() {
        this.sessionInGroup = new ConcurrentHashMap<>();
    }

    public ImGroup(Session creator) {
        this.creator = creator;
        this.sessionInGroup = new ConcurrentHashMap<>();
        this.sessionInGroup.put(creator.getSessionId(), creator);
    }

    public Session getCreator() {
        return creator;
    }

    public void addSession(Session session) {
        if (null != session && session.isConnect()) {
            sessionInGroup.putIfAbsent(session.getSessionId(), session);
        }
    }

    public Session getSession(String sessionId) {
        return sessionInGroup.get(sessionId);
    }

    public int getSessionSize() {
        return sessionInGroup.size();
    }

    public List<ChannelFuture> sendGroupMessage(S2CPacket packet) {
        List<ChannelFuture> futureList = new ArrayList<>();

        // 组员分别发送信息
        Set<String> sessionIdSet = sessionInGroup.keySet();
        Iterator<String> iterator = sessionIdSet.iterator();
        while (iterator.hasNext()) {
            String sessionId = iterator.next();
            if (!creator.getSessionId().equals(sessionId)) {
                Session session = getSession(sessionId);
                ChannelFuture future = session.writeAndFlush(packet);
                futureList.add(future);
            }
        }

        return futureList;
    }

//    public void setCreator(Session creator) {
//        this.creator = creator;
//    }
//
//    public String getGroupId() {
//        return groupId;
//    }
//
//    public void setGroupId(String groupId) {
//        this.groupId = groupId;
//    }
//
//    public String getGroupName() {
//        return groupName;
//    }
//
//    public void setGroupName(String groupName) {
//        this.groupName = groupName;
//    }
//
//    public Date getCreateTime() {
//        return createTime;
//    }
//
//    public void setCreateTime(Date createTime) {
//        this.createTime = createTime;
//    }
//
//    public ConcurrentHashMap<String, Session> getSessionInGroup() {
//        return sessionInGroup;
//    }
//
//    public void setSessionInGroup(ConcurrentHashMap<String, Session> sessionInGroup) {
//        this.sessionInGroup = sessionInGroup;
//    }
}
