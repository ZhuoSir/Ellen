package com.chen.ellen.im.constant;

import io.netty.util.AttributeKey;

/**
 * Created by sunny-chen on 2018/1/22.
 */
public interface Constant {

    interface SessionConfig {
        String SESSION_KEY= "account" ;
        AttributeKey<String> SERVER_SESSION_ID = AttributeKey.valueOf(SESSION_KEY);
        AttributeKey SERVER_SESSION_HEARBEAT = AttributeKey.valueOf("heartbeat");
    }


}
