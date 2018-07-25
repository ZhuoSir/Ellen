package com.chen.ellen.im.core;

import com.chen.ellen.im.core.exception.ServerConnectionException;
import com.chen.ellen.proto.C2SPacket;
import com.chen.ellen.proto.IMHeader;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import org.apache.log4j.Logger;

public class AccountImClient extends AbstractImClient {

    private Logger logger = Logger.getLogger(AccountImClient.class);

    public void operateAfterConnect(ChannelFuture future) throws ServerConnectionException {
        if (future.isSuccess()) {
            C2SPacket c2SPacket = new C2SPacket
                    .Builder(IMHeader.SIGN.PUSH, IMHeader.TYPE.BASE.SIGNIN, IMHeader.CMD.CONNECT)
                    .account(imAccount)
                    .build();
            try {
                channel.writeAndFlush(c2SPacket).addListener(new ChannelFutureListener() {
                    public void operationComplete(ChannelFuture future) throws Exception {
                        if (future.isSuccess()) {
                            logger.info("signin successfully...");
                            mSession.setImAccount(imAccount);
                        } else {
                            logger.info("signin failed...");
                        }
                    }
                }).sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
                logger.error(e);
            }
        } else {
            throw new ServerConnectionException(" Server Connection is wrong. Maybe server has down...");
        }
    }
}
