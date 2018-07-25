package com.chen.ellen.im.shell;

import com.chen.ellen.im.core.handler.ChatReadListener;
import com.chen.ellen.im.shell.wrapper.CMessageWrapper;

public interface ChatService {

    void sendPersonalMessage(CMessageWrapper wrapper);

    void sendGroupMessage(CMessageWrapper wrapper);

    void setChatReadListener(ChatReadListener chatReadListener);

}
