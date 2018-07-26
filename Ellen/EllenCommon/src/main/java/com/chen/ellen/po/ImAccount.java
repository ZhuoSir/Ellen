package com.chen.ellen.po;

import lombok.Data;

import java.util.Date;

@Data
public class ImAccount {

    private String accountId;

    private String nickName;

    private String password;

    private Date createTime;
}
