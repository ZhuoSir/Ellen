package com.chen.ellen.im.shell;

import com.chen.ellen.im.core.ImClient;
import com.chen.ellen.im.core.req.RequestRollBackHandler;
import com.chen.ellen.im.core.session.Session;
import com.chen.ellen.im.shell.wrapper.CMessageWrapper;
import com.chen.ellen.im.shell.wrapper.CRequestWrapper;
import com.chen.ellen.proto.C2SPacket;

public class RequestServiceImpl implements RequestService {

    private Session session;

    public RequestServiceImpl() {
    }

    public RequestServiceImpl(ImClient imClient) {
        this.imClient = imClient;
        this.session = imClient.getSession();
    }

    private ImClient imClient;

    public void setImClient(ImClient imClient) {
        this.imClient = imClient;
        this.session = imClient.getSession();
    }

    public ImClient getImClient() {
        return imClient;
    }

    public void sendRequest(CRequestWrapper wrapper, RequestRollBackHandler handler) {

    }

    public void sendCommand(CRequestWrapper wrapper, RequestRollBackHandler handler) {

    }
}
