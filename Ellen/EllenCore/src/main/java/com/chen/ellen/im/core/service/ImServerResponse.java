package com.chen.ellen.im.core.service;

import com.chen.ellen.im.core.message.IMessageWrapper;
import com.chen.ellen.im.core.session.Session;

/**
 * Created by sunny-chen on 2018/1/22.
 */
public interface ImServerResponse {

    void response(Session session, IMessageWrapper wrapper);

    void requestSuccess(Session session);

    void requestFail(Session session);

    void serverBusy(Session session);

    void error(Session session);

    void pushMessage(Session session, IMessageWrapper wrapper);

    void pushGroupMessage(Session session, IMessageWrapper wrapper);
}
