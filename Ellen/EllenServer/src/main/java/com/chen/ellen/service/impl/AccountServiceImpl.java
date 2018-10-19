package com.chen.ellen.service.impl;

import com.chen.ellen.po.ImAccount;
import com.chen.ellen.im.core.message.IMessageWrapper;
import com.chen.ellen.im.core.session.ImSession;
import com.chen.ellen.service.AccountService;
import com.chen.ellen.session.SessionManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private SessionManagerImpl sessionManager;

    @Override
    public ImAccount initAccountAfterConnect(ImSession imSession,
                                             IMessageWrapper wrapper) {
        ImAccount imAccount = wrapper.getAccount();
        imSession.setAccountId(imAccount.getAccountId());
        sessionManager.updateSession(imSession);
        return imAccount;
    }

    @Override
    public ImAccount accountInfo(String accountId) {
        ImAccount imAccount = new ImAccount();
        imAccount.setAccountId(accountId);
        imAccount.setNickName("tester");
        return imAccount;
    }
}
