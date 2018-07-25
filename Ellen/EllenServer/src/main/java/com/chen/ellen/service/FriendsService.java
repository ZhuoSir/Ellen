package com.chen.ellen.service;

import com.chen.ellen.im.core.message.IMessageWrapper;
import com.chen.ellen.im.core.session.Session;

/**
 * Created by sunny-chen on 2018/1/22.
 */
public interface FriendsService {


    void addFriend(Session session, IMessageWrapper wrapper);
}
