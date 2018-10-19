package com.chen.ellen.service;

import com.chen.ellen.im.core.message.IMessageWrapper;
import com.chen.ellen.im.core.session.ImSession;

/**
 * Created by sunny-chen on 2018/1/22.
 */
public interface FriendsService {


    void addFriend(ImSession imSession, IMessageWrapper wrapper);
}
