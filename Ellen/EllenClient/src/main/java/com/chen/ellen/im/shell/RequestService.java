package com.chen.ellen.im.shell;

import com.chen.ellen.im.core.req.RequestRollBackHandler;
import com.chen.ellen.im.shell.wrapper.CMessageWrapper;
import com.chen.ellen.im.shell.wrapper.CRequestWrapper;

public interface RequestService {

    void sendRequest(CRequestWrapper wrapper, RequestRollBackHandler handler);

    void sendCommand(CRequestWrapper wrapper, RequestRollBackHandler handler);
}
