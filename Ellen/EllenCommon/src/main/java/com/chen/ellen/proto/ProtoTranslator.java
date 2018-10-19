package com.chen.ellen.proto;

/**
 * 协议数据转化器
 *
 * */
public class ProtoTranslator {

    public static S2CPacket transMessage(C2SPacket packet) {
        S2CPacket ret = new S2CPacket();
        S2CHead head = new S2CHead();
        S2CBody body = new S2CBody();

        head.setSign(packet.getC2SHead().getSign());
        head.setCmd(packet.getC2SHead().getCmd());
        head.setType(packet.getC2SHead().getType());
        head.setTimestamp(packet.getC2SHead().getTimestamp());
        head.setRequestId(packet.getC2SHead().getRequestId());
        ret.setS2CHead(head);

        body.setContent(packet.getC2SBody().getContent());
        body.setTitle(packet.getC2SBody().getTitle());
        body.setTimestamp(packet.getC2SBody().getTimestamp());
        body.setType(packet.getC2SBody().getType());
        ret.setS2CBody(body);

        return ret;
    }

    public static S2CPacket transSuccessResp(C2SPacket packet) {
        S2CPacket s2CPacket = transMessage(packet);
        S2CHead s2CHead = s2CPacket.getS2CHead();
        s2CHead.setCode(IMHeader.CODE.REQUESTSUCCESS);
        s2CPacket.setS2CHead(s2CHead);
        return s2CPacket;
    }

    public static S2CPacket transFailResp(C2SPacket packet) {
        S2CPacket s2CPacket = transMessage(packet);
        S2CHead s2CHead = s2CPacket.getS2CHead();
        s2CHead.setCode(IMHeader.CODE.REQUESTFAILURE);
        s2CPacket.setS2CHead(s2CHead);
        return s2CPacket;
    }

    public static S2CPacket transCancelResp(C2SPacket packet) {
        S2CPacket s2CPacket = transMessage(packet);
        S2CHead s2CHead = s2CPacket.getS2CHead();
        s2CHead.setCode(IMHeader.CODE.REQUESTCACENL);
        s2CPacket.setS2CHead(s2CHead);
        return s2CPacket;
    }
}
