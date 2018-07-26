package com.chen.ellen.service;

import com.chen.ellen.po.ImAccount;
import com.chen.ellen.im.core.message.IMessageWrapper;
import com.chen.ellen.im.core.session.Session;

public interface AccountService {

    ImAccount initAccountAfterConnect(Session session, IMessageWrapper wrapper);

    ImAccount accountInfo(String accountId);

}
