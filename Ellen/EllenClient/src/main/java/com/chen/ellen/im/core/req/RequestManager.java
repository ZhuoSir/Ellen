package com.chen.ellen.im.core.req;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 请求管理器
 *
 * */
public class RequestManager {

    private static Map<String, RequestRollBackHandler> handlerPool = new ConcurrentHashMap<String, RequestRollBackHandler>();

    public String addRequestHandler(RequestRollBackHandler handler) {
        String requestId = UUID.randomUUID().toString();
        handlerPool.put(requestId, handler);
        return requestId;
    }
}
