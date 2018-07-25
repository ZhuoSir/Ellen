package com.chen.ellen.service.impl;

import com.chen.ellen.datao.ImAccount;
import com.chen.ellen.im.core.message.IMessageWrapper;
import com.chen.ellen.im.core.session.Session;
import com.chen.ellen.service.AccountService;
import com.chen.ellen.session.SessionManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private SessionManagerImpl sessionManager;

    @Override
    public ImAccount initAccountAfterConnect(Session session,
                                             IMessageWrapper wrapper) {
        ImAccount imAccount = wrapper.getAccount();
        session.setAccountId(imAccount.getAccountId());
        sessionManager.updateSession(session);
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
