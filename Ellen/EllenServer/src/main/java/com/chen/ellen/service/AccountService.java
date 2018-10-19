package com.chen.ellen.service;

import com.chen.ellen.po.ImAccount;
import com.chen.ellen.im.core.message.IMessageWrapper;
import com.chen.ellen.im.core.session.ImSession;

public interface AccountService {

    ImAccount initAccountAfterConnect(ImSession imSession, IMessageWrapper wrapper);

    ImAccount accountInfo(String accountId);

}
