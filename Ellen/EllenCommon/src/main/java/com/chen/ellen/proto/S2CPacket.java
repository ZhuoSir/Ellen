package com.chen.ellen.proto;

import lombok.Data;

@Data
public class S2CPacket {

    private S2CHead s2CHead;

    private S2CBody s2CBody;

    public S2CPacket() {
    }

    public S2CPacket(Builder builder) {
        s2CHead = new S2CHead();
        s2CHead.setType(builder.Type);
        s2CHead.setCmd(builder.Cmd);
        s2CHead.setSign(builder.Sign);
        s2CHead.setTimestamp(builder.timestamp);
        s2CHead.setCode(builder.code);
        s2CHead.setError(builder.error);
        s2CHead.setErrorMsg(builder.errorMsg);
        s2CHead.setRequestId(builder.requestId);

        s2CBody = new S2CBody();
        s2CBody.setType(builder.type);
        s2CBody.setTimestamp(builder.btimestamp);
        s2CBody.setTitle(builder.title);
        s2CBody.setContent(builder.content);
    }

    public static class Builder {

        private int Sign;
        private int Type;
        private int Cmd;
        private String timestamp;

        private int code;
        private String error;
        private String errorMsg;
        private String requestId;

        private String title;
        private String content;
        private String btimestamp;
        private int type;

        public Builder(int sign, int type, int cmd) {
            Sign = sign;
            Type = type;
            Cmd = cmd;
        }

        public Builder code(int code) {
            this.code = code;
            return this;
        }

        public Builder error(String error, String errorMsg) {
            this.error = error;
            this.errorMsg = errorMsg;
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

        public Builder requestId(String requestId) {
            this.requestId = requestId;
            return this;
        }

        public S2CPacket build() {
            return new S2CPacket(this);
        }
    }
}
