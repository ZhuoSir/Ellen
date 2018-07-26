package com.chen.ellen.im.core.message;

import com.chen.ellen.po.ImAccount;
import com.chen.ellen.proto.C2SBody;
import com.chen.ellen.proto.C2SHead;
import com.chen.ellen.proto.C2SPacket;
import io.netty.channel.ChannelHandlerContext;

import java.io.Serializable;

public class IMessageWrapper implements Serializable {

    private static final long serialVersionUID = 3036799586264586903L;

    private String sessionId;

    private String sender;

    private String receiver;

    private ChannelHandlerContext ctx;

    private C2SPacket packet;

    private C2SBody body;

    private C2SHead head;

    public IMessageWrapper(C2SPacket packet, ChannelHandlerContext ctx, String sessionId) {
        this.packet = packet;
        this.sender = packet.getC2SHead().getSender();
        this.receiver = packet.getC2SHead().getReceiver();
        this.body = packet.getC2SBody();
        this.head = packet.getC2SHead();
        this.ctx = ctx;
        this.sessionId = sessionId;
    }

    public ImAccount getAccount() {
        return body.getImAccount();
    }

    public int getSign() {
        return head.getSign();
    }

    public int getType() {
        return head.getType();
    }

    public int getCmd() {
        return head.getCmd();
    }

    public String getSender() {
        return sender;
    }

    public String getDeviceId() {
        return head.getDeviceId();
    }

    public String getGroupId() {
        return head.getGroupId();
    }

    public String getTitle() {
        if (body != null) {
            return body.getTitle();
        }

        return null;
    }

    public String getContent() {
        if (body != null) {
            return body.getContent();
        }

        return null;
    }

    public String getTimeStamp() {
        if (body != null) {
            return body.getTimestamp();
        }

        return null;
    }

    public Integer getContentType() {
        if (body != null) {
            return body.getType();
        }

        return null;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getReceiver() {
        return receiver;
    }

    public ChannelHandlerContext getCtx() {
        return ctx;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public void setCtx(ChannelHandlerContext ctx) {
        this.ctx = ctx;
    }

    public C2SPacket getPacket() {
        return packet;
    }

    public void setPacket(C2SPacket packet) {
        this.packet = packet;
    }

    public C2SBody getBody() {
        return body;
    }

    public void setBody(C2SBody body) {
        this.body = body;
    }

    public C2SHead getHead() {
        return head;
    }

    public void setHead(C2SHead head) {
        this.head = head;
    }
}
