package com.chen.ellen.im.core.session;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ImSession implements Serializable {

    private static final long serialVersionUID = 8269505210699191257L;

    private Channel mChannel;

    private String sessionId;

    private String accountId;

    private String host;

    private Date bindTime;

    private Date updateTime;

    private int status;

    public interface StatusCode {
        int ONLINE = 0x11;
        int OFFLINE = 0x99;
    }

    public ChannelFuture writeAndFlush(Object object) {
        if (null != object) {
            if (isConnect()) {
                ChannelFuture future = mChannel.writeAndFlush(object);
                future.awaitUninterruptibly(5000);
                return future;
            }
        }

        return null;
    }

    public boolean isConnect() {
        if (null != mChannel) {
            return mChannel.isActive();
        }

        return false;
    }

    public ChannelFuture close() {
        if (null != mChannel) {
           ChannelFuture future = mChannel.close();
           future.awaitUninterruptibly(5000);
           return future;
        }

        return null;
    }

    // 更具连接状态调整当前回话状态
    public void status() {
        this.status = isConnect() ? StatusCode.ONLINE : StatusCode.OFFLINE;
    }
}
