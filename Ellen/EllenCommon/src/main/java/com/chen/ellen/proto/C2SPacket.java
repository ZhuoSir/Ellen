package com.chen.ellen.proto;

import com.chen.ellen.datao.ImAccount;
import lombok.Data;

@Data
public class C2SPacket {

    private C2SHead c2SHead;

    private C2SBody c2SBody;

    public C2SPacket() {
    }

    public C2SPacket(Builder builder) {
        c2SHead = new C2SHead();
        c2SHead.setSign(builder.Sign);
        c2SHead.setCmd(builder.Cmd);
        c2SHead.setType(builder.Type);
        c2SHead.setTimestamp(builder.timestamp);
        c2SHead.setDeviceId(builder.deviceId);
        c2SHead.setGroupId(builder.groupId);
        c2SHead.setReceiver(builder.receiver);
        c2SHead.setSender(builder.sender);
        c2SHead.setRequestId(builder.requestId);

        c2SBody = new C2SBody();
        c2SBody.setContent(builder.content);
        c2SBody.setTimestamp(builder.btimestamp);
        c2SBody.setTitle(builder.title);
        c2SBody.setType(builder.type);
        c2SBody.setImAccount(builder.imAccount);
    }

    public C2SHead getC2SHead() {
        return c2SHead;
    }

    public void setC2SHead(C2SHead c2SHead) {
        this.c2SHead = c2SHead;
    }

    public C2SBody getC2SBody() {
        return c2SBody;
    }

    public void setC2SBody(C2SBody c2SBody) {
        this.c2SBody = c2SBody;
    }

    public static class Builder {

        private int Sign;
        private int Type;
        private int Cmd;
        private String timestamp;

        private String sender;
        private String receiver;
        private String deviceId;
        private String groupId;
        private String requestId;

        private String title;
        private String content;
        private String btimestamp;
        private int type;

        private ImAccount imAccount;

        public Builder(int sign, int type, int cmd) {
            Sign = sign;
            Type = type;
            Cmd = cmd;
        }

        public Builder Sender(String sender) {
            this.sender = sender;
            return this;
        }

        public Builder Receiver(String receiver) {
            this.receiver = receiver;
            return this;
        }

        public Builder deviceId(String deviceId) {
            this.deviceId = deviceId;
            return this;
        }

        public Builder groupId(String groupId) {
            this.groupId = groupId;
            return this;
        }

        public Builder requestId(String requestId) {
            this.requestId = requestId;
            return this;
        }

        public Builder account(ImAccount imAccount) {
            this.imAccount = imAccount;
            return this;
        }

        public Builder body(String title, String content, int type) {
            this.title = title;
            this.content = content;
            this.type = type;
            return this;
        }

        public Builder time(String timestamp) {
            this.timestamp = timestamp;
            this.btimestamp = timestamp;
            return this;
        }

        public C2SPacket build() {
            return new C2SPacket(this);
        }
    }
}
