package com.chen.ellen.service.handlers;

import com.chen.ellen.im.core.message.IMessageWrapper;
import com.chen.ellen.im.core.session.Session;
import com.chen.ellen.proto.IMHeader;
import com.chen.ellen.service.impl.AccountServiceImpl;
import com.chen.ellen.service.impl.FriendsServiceImpl;
import com.chen.ellen.service.impl.ServerRespServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TypeHandlerManager {

    @Autowired
    private FriendsServiceImpl friendsService;

    @Autowired
    private AccountServiceImpl accountService;

    @Autowired
    private ServerRespServiceImpl serverRespService;

    private final Map<Integer, TypeHandler> typeHandlerMap = new ConcurrentHashMap<Integer, TypeHandler>() {
        {
            put(IMHeader.TYPE.BASE.SIGNIN,          new SignInTypeHandler());
            put(IMHeader.TYPE.BASE.EXIT,            new ExitTypeHandler());
            put(IMHeader.TYPE.FRIEND.ADD_FRIEND,    new AddFriendTypeHandler());
            put(IMHeader.TYPE.FRIEND.REMOVE_FRIEND, new RmvFriendTypeHandler());
            put(IMHeader.TYPE.GROUP.GROUP_MESSAGE,  new GroupMessageTypeHandler());
        }
    };

    public TypeHandler getTypeHandler(Integer type) {
        return typeHandlerMap.get(type);
    }

    private class SignInTypeHandler implements TypeHandler {

        @Override
        public void handle(Session session, IMessageWrapper wrapper) {
            accountService.initAccountAfterConnect(session, wrapper);
        }
    }

    private class ExitTypeHandler implements TypeHandler {

        @Override
        public void handle(Session session, IMessageWrapper wrapper) {

        }
    }

    private class AddFriendTypeHandler implements TypeHandler {

        @Override
        public void handle(Session session, IMessageWrapper wrapper) {
            friendsService.addFriend(session, wrapper);
        }
    }

    private class RmvFriendTypeHandler implements TypeHandler {

        @Override
        public void handle(Session session, IMessageWrapper wrapper) {

        }
    }

    private class GroupMessageTypeHandler implements TypeHandler {

        @Override
        public void handle(Session session, IMessageWrapper wrapper) {
            serverRespService.pushGroupMessage(session, wrapper);
        }
    }
}