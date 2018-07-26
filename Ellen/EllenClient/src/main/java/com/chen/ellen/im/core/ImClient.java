package com.chen.ellen.im.core;

import com.chen.ellen.po.ImAccount;
import com.chen.ellen.im.core.exception.ServerConnectionException;
import com.chen.ellen.im.core.handler.ChatReadListener;
import com.chen.ellen.im.core.session.Session;

public interface ImClient {

    void init(Integer threadNum);

    void connect(String ip, int port, boolean sync) throws ServerConnectionException;

    void close();

    /** 设置聊天账户 */
    void setImAccount(ImAccount imAccount);

    Session getSession();

    void setChatReadListener(ChatReadListener chatReadListener);
}
