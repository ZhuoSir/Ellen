package com.chen.ellen.im.core.listener;

import com.chen.ellen.im.core.future.PushResponseFuture;
import com.chen.ellen.im.core.process.PushResponseTask;
import com.chen.ellen.im.core.session.ImSession;
import com.chen.ellen.proto.C2SPacket;
import com.chen.ellen.proto.ProtoTranslator;
import com.chen.ellen.proto.S2CPacket;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

/**
 * Created by sunny-chen on 2018/1/26.
 */
public class ImSendListener implements ChannelFutureListener {

    private ImSession sender;

    private ImSession receiver;

    public ImSendListener(ImSession sender) {
        this.sender = sender;
    }

    private C2SPacket c2SPacket;

    public ImSendListener() {
    }

    public ImSendListener(ImSession sender, ImSession receiver, C2SPacket c2SPacket) {
        this.sender = sender;
        this.receiver = receiver;
        this.c2SPacket = c2SPacket;
    }

    @Override
    public void operationComplete(ChannelFuture channelFuture) throws Exception {
        S2CPacket s2CPacket = null;
        if (channelFuture.isDone() && channelFuture.isSuccess()) {
            s2CPacket = null != c2SPacket
                    ? ProtoTranslator.transSuccessResp(c2SPacket)
                    : ProtoTranslator.transSuccessResp(new C2SPacket());
        } else if (channelFuture.isDone() && !channelFuture.isSuccess()) {
            s2CPacket = null != c2SPacket
                    ? ProtoTranslator.transFailResp(c2SPacket)
                    : ProtoTranslator.transFailResp(new C2SPacket());
        } else if (channelFuture.isCancelled()) {
            s2CPacket = null != c2SPacket
                    ? ProtoTranslator.transCancelResp(c2SPacket)
                    : ProtoTranslator.transCancelResp(new C2SPacket());
        }
        ChannelFuture respFuture = sender.writeAndFlush(s2CPacket);
        if (respFuture.isDone() && respFuture.isSuccess()) {
            return;
        } else if (respFuture.isDone() && !respFuture.isSuccess()) {
            PushResponseFuture task = new PushResponseFuture(s2CPacket, channelFuture, sender);
            PushResponseTask.addSendFuture(task);
        }
    }

    public void setReceiver(ImSession receiver) {
        this.receiver = receiver;
    }

    public ImSession getReceiver() {
        return receiver;
    }

    public void setSender(ImSession sender) {
        this.sender = sender;
    }

    public ImSession getSender() {
        return sender;
    }
}
