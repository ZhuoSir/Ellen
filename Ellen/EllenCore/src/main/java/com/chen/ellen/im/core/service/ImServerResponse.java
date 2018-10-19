package com.chen.ellen.im.core.service;

import com.chen.ellen.im.core.message.IMessageWrapper;
import com.chen.ellen.im.core.session.ImSession;

/**
 * Created by sunny-chen on 2018/1/22.
 */
public interface ImServerResponse {

    void response(ImSession imSession, IMessageWrapper wrapper);

    void requestSuccess(ImSession imSession);

    void requestFail(ImSession imSession);

    void serverBusy(ImSession imSession);

    void error(ImSession imSession);

    void pushMessage(ImSession imSession, IMessageWrapper wrapper);

    void pushGroupMessage(ImSession imSession, IMessageWrapper wrapper);
}
