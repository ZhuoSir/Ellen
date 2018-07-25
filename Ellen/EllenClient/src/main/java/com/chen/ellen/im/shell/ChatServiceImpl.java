package com.chen.ellen.im.shell;

import com.chen.ellen.im.core.ImClient;
import com.chen.ellen.im.core.handler.ChatReadListener;
import com.chen.ellen.im.core.session.Session;
import com.chen.ellen.im.shell.wrapper.CMessageWrapper;
import com.chen.ellen.proto.C2SPacket;

public class ChatServiceImpl implements ChatService {

    private Session session;

    private ImClient imClient;

    public ChatServiceImpl() {
    }

    public ChatServiceImpl(ImClient imClient) {
        this.imClient = imClient;
        this.session = imClient.getSession();
    }

    public void setImClient(ImClient imClient) {
        this.imClient = imClient;
        this.session = imClient.getSession();
    }

    public ImClient getImClient() {
        return imClient;
    }

    public void sendPersonalMessage(final CMessageWrapper wrapper) {
        wrapper.setGroup(false);
        C2SPacket packet = ImWrapperTranslator.MessageWrapperTranslate(session, wrapper);
        session.send(packet);
    }

    public void sendGroupMessage(final CMessageWrapper wrapper) {
        wrapper.setGroup(true);
        C2SPacket packet = ImWrapperTranslator.MessageWrapperTranslate(session, wrapper);
        session.send(packet);
    }

    public void setChatReadListener(ChatReadListener chatReadListener) {
        imClient.setChatReadListener(chatReadListener);
    }
}
