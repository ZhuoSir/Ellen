package com.chen.ellen.im.core.session;

import lombok.Data;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

@Data
public class ImChatRoom {

    protected ImSession creator;

    protected Date createTime;

    /**
     * 会话组里的Session
     *
     * */
    protected ConcurrentHashMap<String, ImSession> sessionInGroup;


}
