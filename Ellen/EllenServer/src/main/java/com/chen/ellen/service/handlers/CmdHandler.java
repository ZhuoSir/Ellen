package com.chen.ellen.service.handlers;

import com.chen.ellen.im.core.message.IMessageWrapper;
import com.chen.ellen.im.core.session.ImSession;

public interface CmdHandler {

    void handle(ImSession imSession, IMessageWrapper wrapper);
}
