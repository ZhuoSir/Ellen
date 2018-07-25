package com.chen.ellen.datao;

import lombok.Data;

import java.util.Date;

@Data
public class ImAccount {

    private String accountId;

    private String nickName;

    private String password;

    private Date createTime;
}
