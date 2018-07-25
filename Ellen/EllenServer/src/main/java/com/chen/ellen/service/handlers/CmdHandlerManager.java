package com.chen.ellen.service.handlers;

import com.chen.ellen.im.core.message.IMessageWrapper;
import com.chen.ellen.im.core.service.ServerRespService;
import com.chen.ellen.im.core.session.Session;
import com.chen.ellen.proto.IMHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class CmdHandlerManager {

    @Autowired
    private ServerRespService serverRespService;

    @Autowired
    private TypeHandlerManager typeHandlerManager;

    private final Map<Integer, CmdHandler> handlerMap = new ConcurrentHashMap<Integer, CmdHandler>() {
        {
            put(IMHeader.CMD.CONNECT,  new ConnectCmdHandler());
            put(IMHeader.CMD.PERSONAL, new PersonalMsgCmdHandler());
            put(IMHeader.CMD.ONLINE,   new OnlineCmdHandler());
            put(IMHeader.CMD.OFFLINE,  new OffLineCmdHandler());
            put(IMHeader.CMD.FRIEND,   new FriendCmdHandler());
            put(IMHeader.CMD.GROUP,    new GroupCmdHandler());
            put(IMHeader.CMD.EXIT,     new ExitCmdHandler());
        }
    };

    public CmdHandler getCmdHandler(Integer cmd) {
        return handlerMap.get(cmd);
    }

    private class ConnectCmdHandler implements CmdHandler {

        @Override
        public void handle(Session session, IMessageWrapper wrapper) {
            TypeHandler typeHandler = typeHandlerManager.getTypeHandler(wrapper.getType());
            if (null != typeHandler) {
                typeHandler.handle(session, wrapper);
            }
        }
    }

    private class PersonalMsgCmdHandler implements CmdHandler {

        @Override
        public void handle(Session session, IMessageWrapper wrapper) {
            serverRespService.pushMessage(session, wrapper);
        }
    }

    private class OnlineCmdHandler implements CmdHandler {

        @Override
        public void handle(Session session, IMessageWrapper wrapper) {
            session.status();
        }
    }

    private class OffLineCmdHandler implements CmdHandler {

        @Override
        public void handle(Session session, IMessageWrapper wrapper) {
            session.status();
        }
    }

    private class FriendCmdHandler implements CmdHandler {

        @Override
        public void handle(Session session, IMessageWrapper wrapper) {
            TypeHandler typeHandler = typeHandlerManager.getTypeHandler(wrapper.getType());
            if (null != typeHandler) {
                typeHandler.handle(session, wrapper);
            }
        }
    }

    private class GroupCmdHandler implements CmdHandler {

        @Override
        public void handle(Session session, IMessageWrapper wrapper) {
            TypeHandler typeHandler = typeHandlerManager.getTypeHandler(wrapper.getType());
            if (null != typeHandler) {
                typeHandler.handle(session, wrapper);
            }
        }
    }

    private class ExitCmdHandler implements CmdHandler {

        @Override
        public void handle(Session session, IMessageWrapper wrapper) {
            session.close();
        }
    }
}
