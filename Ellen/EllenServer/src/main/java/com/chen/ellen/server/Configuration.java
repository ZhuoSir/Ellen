package com.chen.ellen.server;

import com.chen.ellen.im.core.proxy.ImMessageProxy;
import com.chen.ellen.im.core.server.ImServer;
import com.chen.ellen.im.core.server.ImServerImpl;
import com.chen.ellen.im.core.service.ImServerResponse;
import com.chen.ellen.im.core.session.ImConnect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

/**
 * Created by sunny-chen on 2018/7/25.
 */
@org.springframework.context.annotation.Configuration
public class Configuration {

    private ImServerImpl imServer = null;

    @Autowired
    private ImConnect imConnect;

    @Autowired
    private ImServerResponse imServerResponse;

    @Autowired
    private ImMessageProxy imMessageProxy;

    @Bean("imServer")
    public ImServer imServer() {
        if (imServer == null) {
            imServer = new ImServerImpl();
            imServer.setImConnect(imConnect);
            imServer.setImMessageProxy(imMessageProxy);
            imServer.setImServerResponse(imServerResponse);
        }

        return imServer;
    }

}
