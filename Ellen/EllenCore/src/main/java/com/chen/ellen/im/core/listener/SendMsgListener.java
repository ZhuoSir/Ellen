package com.chen.ellen.im.core.listener;

import com.chen.ellen.im.core.session.Session;
import com.chen.ellen.proto.C2SPacket;
import com.chen.ellen.proto.ProtoTranslator;
import com.chen.ellen.proto.S2CPacket;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

/**
 * Created by sunny-chen on 2018/1/26.
 */
public class SendMsgListener implements ChannelFutureListener {

    private Session sender;

    public SendMsgListener(Session sender) {
        this.sender = sender;
    }

    private C2SPacket c2SPacket;

    public SendMsgListener() {
    }

    public SendMsgListener(Session sender, C2SPacket c2SPacket) {
        this.sender = sender;
        this.c2SPacket = c2SPacket;
    }

    @Override
    public void operationComplete(ChannelFuture channelFuture) throws Exception {
        S2CPacket s2CPacket;
        if (channelFuture.isSuccess()) {
            s2CPacket = null != c2SPacket
                    ? ProtoTranslator.transSuccessResp(c2SPacket)
                    : ProtoTranslator.transSuccessResp(new C2SPacket());
        } else {
            s2CPacket = null != c2SPacket
                    ? ProtoTranslator.transFailResp(c2SPacket)
                    : ProtoTranslator.transSuccessResp(new C2SPacket());
        }

        sender.writeAndFlush(s2CPacket);
    }
}
