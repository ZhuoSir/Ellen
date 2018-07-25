package com.chen.ellen.proto;

import lombok.Data;

@Data
public class Head {

    private int Sign;

    private int Type;

    private int Cmd;

    private String timestamp;
}
