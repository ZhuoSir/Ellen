package com.chen.ellen.proto;

import lombok.Data;

@Data
public class S2CHead extends Head {

    private int code;

    private String error;

    private String errorMsg;

    private String requestId;
}
