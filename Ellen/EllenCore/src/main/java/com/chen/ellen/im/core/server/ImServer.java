package com.chen.ellen.im.core.server;

public interface ImServer {

    void init(Integer threadNums);

    void bind(Integer port);

    void start(boolean sync);

}
