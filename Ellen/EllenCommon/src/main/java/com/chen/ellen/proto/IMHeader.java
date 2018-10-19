package com.chen.ellen.proto;

public interface IMHeader {


    interface SIGN {
        int PUSH = 0x01;        // 请求，无需服务器响应  Client --> Server
        int REQUEST = 0x02;     // 请求，需要服务器响应  Client --> Server
        int RESPONSE = 0x02;    // 响应  Server --> Client
        int NOTICE = 0x03;      // 通知  Server --> Client  e.g.消息转发
        int TRANSPOND = 0x04;   // 消息转发 client A --> Server --> client B
    }

    interface CMD {
        int CONNECT = 0x01;
        int HEARTBEAT = 0x11;
        int ONLINE = 0x30;
        int OFFLINE = 0x31;
        int PERSONAL = 0xA1;
        int GROUP = 0xB1;
        int FRIEND = 0xC1;
        int EXIT = 0x99;
    }

//    interface TYPE {
//        /**
//         * type: 0x11 ~ 0x23 ===========================================================================================
//         */
//        int SIGNIN = 0x11;    // 登录
//        int REGISTER = 0x12;    // 注册
//        int LOGOUT = 0x13;    // 登出
//        int RECONN = 0x14;    // 重连
//        int PERSON_MESSAGE = 0x15;    // 发送个人消息
//        int GROUP_MESSAGE = 0x16;    // 发送讨论组消息
//        int CREATE_GROUP = 0x17;    // 创建讨论组
//        int DISBAND_GROUP = 0x18;    // 解散讨论组
//        int ADD_MEMBER = 0x19;    // 讨论组添加成员
//        int REMOVE_MEMBER = 0x1a;    // 讨论组移除成员
//        int ADD_FRIEND = 0x1b;    // 添加好友
//        int REMOVE_FRIEND = 0x1c;    // 删除好友
//        int ALL_FRIEND = 0x1d;    // 查看已添加好友
//        int UPDATE_SELF_INFO = 0x1e;    // 修改个人信息
//        int LOOK_SELF_INFO = 0x1f;    // 查看个人信息
//        int LOOK_FRIEND_INFO = 0x21;    // 查看好友信息
//        int LOOK_GROUP_INFO = 0x22;    // 查看自己所在讨论组信息
//        int HEARTBEAT = 0x23;    // 心跳
//        int EXIT = 0x30;        // 退出聊天
//        int ERROR = 0x99;
//        int SERVERBACK = 0x29;
//    }

    interface TYPE {

        interface BASE {
            int SIGNIN = 0x11;    // 登录
            int REGISTER = 0x12;    // 注册
            int LOGOUT = 0x13;    // 登出
            int RECONN = 0x14;    // 重连
            int HEARTBEAT = 0x23;    // 心跳
            int EXIT = 0x30;        // 退出聊天
            int ERROR = 0x99;
            int SERVERBACK = 0x29;
        }

        interface PERSONAL {
            int PERSON_MESSAGE = 0x15;    // 发送个人消息
            int UPDATE_SELF_INFO = 0x1e;    // 修改个人信息
            int LOOK_SELF_INFO = 0x1f;    // 查看个人信息
        }

        interface GROUP {
            int GROUP_MESSAGE = 0x16;    // 发送讨论组消息
            int CREATE_GROUP = 0x17;    // 创建讨论组
            int DISBAND_GROUP = 0x18;    // 解散讨论组
            int ADD_MEMBER = 0x19;    // 讨论组添加成员
            int REMOVE_MEMBER = 0x1a;    // 讨论组移除成员
            int LOOK_GROUP_INFO = 0x22;    // 查看自己所在讨论组信息
        }

        interface FRIEND {
            int ADD_FRIEND = 0x1b;    // 添加好友
            int REMOVE_FRIEND = 0x1c;    // 删除好友
            int ALL_FRIEND = 0x1d;    // 查看已添加好友
            int LOOK_FRIEND_INFO = 0x21;    // 查看好友信息
        }
    }

    interface CODE {
        int REQUESTSUCCESS = 0x10;
        int REQUESTFAILURE = 0x11;
        int REQUESTERROR   = 0x20;
        int REQUESTCACENL   = 0x30;
    }
}
