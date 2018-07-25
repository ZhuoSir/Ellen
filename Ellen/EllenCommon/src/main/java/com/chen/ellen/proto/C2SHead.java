package com.chen.ellen.proto;

import lombok.Data;

@Data
public class C2SHead extends Head {

    private String sender;

    private String receiver;

    private String deviceId;

    private String groupId;

    private String requestId;
}
