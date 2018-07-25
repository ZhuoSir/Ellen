package com.chen.ellen.im.shell;

import com.chen.ellen.im.core.session.Session;
import com.chen.ellen.im.shell.wrapper.CMessageWrapper;
import com.chen.ellen.proto.C2SPacket;
import com.chen.ellen.proto.IMHeader;

public class ImWrapperTranslator {

    public static C2SPacket MessageWrapperTranslate(Session session, CMessageWrapper wrapper) {
        Integer TYPE = wrapper.isGroup() ? IMHeader.TYPE.GROUP.GROUP_MESSAGE : IMHeader.TYPE.PERSONAL.PERSON_MESSAGE;
        Integer CMD  = wrapper.isGroup() ? IMHeader.CMD.GROUP : IMHeader.CMD.PERSONAL;
        C2SPacket.Builder builder = new C2SPacket.Builder(IMHeader.SIGN.PUSH, TYPE, CMD);

        builder.account(session.getImAccount());
//        builder.Sender(session.)
        builder.body(wrapper.getTitle(), wrapper.getContent(), 0);
        if (wrapper.isGroup()) {
            builder.groupId(wrapper.getReceiver());
        } else {
            builder.Receiver(wrapper.getReceiver());
        }

        return builder.build();
    }

}
