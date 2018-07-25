package com.chen.ellen.im.core.handler;

import com.chen.ellen.im.core.ImClientMsgWrapper;

/**
 * Created by sunny-chen on 2018/7/25.
 */
public interface ChatReadListener {

    public void read(ImClientMsgWrapper wrapper);
}
