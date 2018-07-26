package com.chen.ellen.im.core;

import com.chen.ellen.po.ImAccount;
import com.chen.ellen.im.core.codec.MessageDecoder;
import com.chen.ellen.im.core.codec.MessageEncoder;
import com.chen.ellen.im.core.exception.ServerConnectionException;
import com.chen.ellen.im.core.handler.ChatReadListener;
import com.chen.ellen.im.core.handler.ImClientHandler;
import com.chen.ellen.im.core.session.Session;
import com.chen.ellen.proto.S2CPacket;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.apache.log4j.Logger;

import java.net.InetSocketAddress;

public abstract class AbstractImClient implements ImClient {

    private Logger logger = Logger.getLogger(AbstractImClient.class);

    Bootstrap bootstrap;

    EventLoopGroup worker;

    Channel channel;

    ImAccount imAccount;

    Session mSession;

    ChatReadListener chatReadListener;

    private ImClientHandler imClientHandler;

    public void init(Integer threadNum) {
        if (null == threadNum) threadNum = 5;
        worker = new NioEventLoopGroup(threadNum);

        bootstrap = new Bootstrap();
        bootstrap.group(worker);
        bootstrap.channel(NioSocketChannel.class);

        imClientHandler = new ImClientHandler(chatReadListener);
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            protected void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast("messageDecoder", new MessageDecoder(S2CPacket.class));
                pipeline.addLast("messageEncoder", new MessageEncoder());
                pipeline.addLast("imClientHandler", imClientHandler);
            }
        }).option(ChannelOption.SO_BACKLOG, 128);
    }

    public void connect(String ip, int port, boolean sync) throws ServerConnectionException {
        try {
            bootstrap.remoteAddress(new InetSocketAddress(ip, port));
            ChannelFuture future = bootstrap.connect();
            if (sync) {
                future.sync();
            }

            channel = future.channel();
            mSession = new Session();
            mSession.setcChannel(channel);
            operateAfterConnect(future);

            logger.info("Client has connected to server successfully...");
        } catch (InterruptedException e) {
            logger.info("Client has connected to server failed...");
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            channel.closeFuture().sync();
            logger.info("Client has closed connection to server...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            worker.shutdownGracefully();
        }
    }

    public void setImAccount(ImAccount imAccount) {
        this.imAccount = imAccount;
    }

    public void setChatReadListener(ChatReadListener chatReadListener) {
        this.chatReadListener = chatReadListener;
        if (this.imClientHandler != null) {
            this.imClientHandler.setChatReadListener(chatReadListener);
        }
    }

    public Session getSession() {
        return mSession;
    }

    /** 发起连接之后登录操作 */
    public abstract void operateAfterConnect(ChannelFuture future) throws ServerConnectionException;
}
