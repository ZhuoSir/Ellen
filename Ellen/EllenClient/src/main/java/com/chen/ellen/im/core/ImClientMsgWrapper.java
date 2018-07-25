package com.chen.ellen.im.core;

import com.chen.ellen.proto.S2CBody;
import com.chen.ellen.proto.S2CHead;
import com.chen.ellen.proto.S2CPacket;

/**
 * Created by sunny-chen on 2018/7/25.
 */
public class ImClientMsgWrapper {

    private S2CPacket s2CPacket;

    private S2CBody s2CBody;

    private S2CHead s2CHead;

    public ImClientMsgWrapper(S2CPacket s2CPacket) {
        this.s2CPacket = s2CPacket;
        this.s2CHead = s2CPacket.getS2CHead();
        this.s2CBody = s2CPacket.getS2CBody();
    }

    public String getTitle() {
        return s2CBody.getTitle();
    }

    public String getContent() {
        return s2CBody.getContent();
    }

    public String getTimeStamp() {
        return s2CBody.getTimestamp();
    }

    public int getMsgType() {
        return s2CBody.getType();
    }
}
