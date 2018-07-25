package com.chen.ellen.service.handlers;

import com.chen.ellen.im.core.message.IMessageWrapper;
import com.chen.ellen.im.core.session.Session;

public interface CmdHandler {

    void handle(Session session, IMessageWrapper wrapper);
}
