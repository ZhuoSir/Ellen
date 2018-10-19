package com.chen.ellen.im.core.process;

import com.chen.ellen.im.core.future.PushResponseFuture;
import com.chen.ellen.im.core.session.ImSession;
import io.netty.channel.ChannelFuture;
import org.apache.log4j.Logger;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class PushResponseTask extends DaemonTask {

    private Logger logger = Logger.getLogger(PushResponseTask.class);

    private static BlockingQueue<PushResponseFuture> futureBlockingQeque = new LinkedBlockingQueue<>(1024);

    public static void addSendFuture(PushResponseFuture pushResponseFuture) {
        futureBlockingQeque.add(pushResponseFuture);
    }

    @Override
    void taskDetail() {
        for (;;) {
            try {
                PushResponseFuture pushResponseFuture = futureBlockingQeque.take();
                if (pushResponseFuture.getRetryTimes() < 3) {
                    ImSession imSession = pushResponseFuture.getImSession();
                    ChannelFuture future = imSession.writeAndFlush(pushResponseFuture.getS2CPacket());
                    if (future.isSuccess()) {
                        pushResponseFuture = null;
                        continue;
                    } else if (future.isCancelled()) {
                        pushResponseFuture = null;
                        continue;
                    } else {
                        pushResponseFuture.setRetryTimes(pushResponseFuture.getRetryTimes() + 1);
                        futureBlockingQeque.add(pushResponseFuture);
                    }
                } else {
                    pushResponseFuture.setRetryTimes(pushResponseFuture.getRetryTimes() + 1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
