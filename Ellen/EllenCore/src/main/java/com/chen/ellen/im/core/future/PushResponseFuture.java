package com.chen.ellen.im.core.future;

import com.chen.ellen.im.core.session.ImSession;
import com.chen.ellen.proto.S2CPacket;
import io.netty.channel.ChannelFuture;
import lombok.Data;

@Data
public class PushResponseFuture {

    private ImSession imSession;

    private S2CPacket s2CPacket;

    private int retryTimes;

    private ChannelFuture channelFuture;

    public PushResponseFuture() {
    }

    public PushResponseFuture(S2CPacket s2CPacket, ChannelFuture channelFuture, ImSession imSession) {
        this.s2CPacket = s2CPacket;
        this.channelFuture = channelFuture;
        this.imSession = imSession;
    }

    public boolean isDone() {
        return channelFuture.isDone();
    }

    public boolean isSuccess() {
        return channelFuture.isSuccess();
    }

    public boolean isVoid() {
        return channelFuture.isVoid();
    }

    public boolean isCancelled() {
        return channelFuture.isCancelled();
    }

    public boolean isCancellable() {
        return channelFuture.isCancellable();
    }

    public void cancel(boolean mayInterruptIfRunning) {
        channelFuture.cancel(mayInterruptIfRunning);
    }
}

