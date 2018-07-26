package com.chen.ellen.im.core.session;

import com.chen.ellen.po.ImAccount;
import com.chen.ellen.proto.C2SPacket;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import lombok.Data;

import java.io.Serializable;

@Data
public class Session implements Serializable {

    private static final long serialVersionUID = 8269505210699191257L;

    private Channel cChannel;

//    private String sessionId;

    private ImAccount imAccount;

    public ChannelFuture send(C2SPacket packet) {
        if (null != packet) {
            if (isConnect()) {
                ChannelFuture future = cChannel.writeAndFlush(packet);
                return future;
            }
        }

        return null;
    }

    public boolean isConnect() {
        if (null != cChannel) {
            return cChannel.isActive();
        }

        return false;
    }

    public ChannelFuture close() {
        if (null != cChannel) {
            ChannelFuture future = cChannel.close();
            future.awaitUninterruptibly(5000);
            return future;
        }

        return null;
    }

    public void setcChannel(Channel cChannel) {
        this.cChannel = cChannel;
    }

//    public void setSessionId(String sessionId) {
//        this.sessionId = sessionId;
//    }

    public void setImAccount(ImAccount imAccount) {
        this.imAccount = imAccount;
    }

    public Channel getcChannel() {
        return cChannel;
    }

//    public String getSessionId() {
//        return sessionId;
//    }

    public ImAccount getImAccount() {
        return imAccount;
    }
}
