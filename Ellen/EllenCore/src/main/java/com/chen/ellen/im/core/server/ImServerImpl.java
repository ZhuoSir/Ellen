package com.chen.ellen.im.core.server;

import com.chen.ellen.im.core.proxy.MessageProxy;
import com.chen.ellen.im.core.server.codec.MessageDecoder;
import com.chen.ellen.im.core.server.codec.MessageEncoder;
import com.chen.ellen.im.core.server.handler.ImAcceptorHandler;
import com.chen.ellen.im.core.service.ServerRespService;
import com.chen.ellen.im.core.session.ImConnect;
import com.chen.ellen.proto.C2SPacket;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import org.apache.log4j.Logger;

public class ImServerImpl implements ImServer {

    private Logger logger = Logger.getLogger(ImServerImpl.class);

    private ServerBootstrap bootstrap;

    private EventLoopGroup parentGroup;

    private EventLoopGroup childGroup;

    private ChannelFuture future;

    private Channel sChannel;

    private int threadNums;

    private int port;

    private boolean sync;

    private ImConnect imConnect;

    private ServerRespService serverRespService;

    private MessageProxy messageProxy;

    @Override
    public void init(Integer threadNum) {
        bootstrap = new ServerBootstrap();
        if (null != threadNum) {
            this.threadNums = threadNum;
            parentGroup = new NioEventLoopGroup(threadNum);
            childGroup = new NioEventLoopGroup(threadNum);
        } else {
            parentGroup = new NioEventLoopGroup();
            childGroup = new NioEventLoopGroup();
        }

        bootstrap.group(parentGroup, childGroup);
        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                ChannelPipeline pipeline = socketChannel.pipeline();
                pipeline.addLast("messageDecoder", new MessageDecoder(C2SPacket.class));
                pipeline.addLast("messageEncoder", new MessageEncoder());
                pipeline.addLast(new IdleStateHandler(5, 5, 0));
                pipeline.addLast("imAcceptorHandler", new ImAcceptorHandler(imConnect, serverRespService, messageProxy));
            }
        }).option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true);
    }

    @Override
    public void bind(Integer port) {
        this.port = port;
        future = bootstrap.bind(port);
    }

    @Override
    public void start(boolean sync) {
        try {
            this.sync = sync;
            if (sync) {
                future.sync();
            }
            logger.info("Ellen IM Server 启动中：线程数：" + threadNums + " 端口号：" + port + " 是否同步：" + this.sync);

            sChannel = future.channel();
            future.addListener(new ChannelFutureListener() {
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (future.isSuccess()) {
                        logger.info("Ellen IM Server 启动成功");
                    } else {
                        logger.info("Ellen IM Server 启动失败");
                    }
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
            logger.error(e);
        }
    }

    public void setImConnect(ImConnect imConnect) {
        this.imConnect = imConnect;
    }

    public void setServerRespService(ServerRespService serverRespService) {
        this.serverRespService = serverRespService;
    }

    public void setMessageProxy(MessageProxy messageProxy) {
        this.messageProxy = messageProxy;
    }
}
