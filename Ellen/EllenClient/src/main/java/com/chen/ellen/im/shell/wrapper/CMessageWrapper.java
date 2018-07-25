package com.chen.ellen.im.shell.wrapper;

import com.chen.ellen.proto.S2CPacket;
import lombok.Data;

import java.util.Date;

@Data
public class CMessageWrapper {

    private String receiver;

    private String title;

    private String content;

    private boolean isGroup;

    private Date creDate;

    public CMessageWrapper(Builder builder) {
        this.receiver = builder.receiver;
        this.title = builder.title;
        this.content = builder.content;
        this.isGroup = builder.isGroup;
        this.creDate = builder.creDate == null ? new Date() : builder.creDate;
    }

    public static class Builder {
        private String receiver;
        private String title;
        private String content;
        private boolean isGroup;
        private Date creDate;

        public Builder() {
        }

        public Builder receiver(String receiver) {
            this.receiver = receiver;
            this.isGroup = false;
            return this;
        }

        public Builder groupReceiver(String receiver) {
            this.receiver = receiver;
            this.isGroup = true;
            return this;
        }

        public Builder message(String title, String content) {
            this.title = title;
            this.content = content;
            return this;
        }

        public Builder createDdate(Date date) {
            this.creDate = date;
            return this;
        }

        public CMessageWrapper build() {
            return new CMessageWrapper(this);
        }
    }
}
