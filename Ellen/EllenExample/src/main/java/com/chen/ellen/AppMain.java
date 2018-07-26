package com.chen.ellen;

import com.chen.ellen.service.SimpleApplication;

public class AppMain {

    public static void main(String[] args) throws InterruptedException {
        Application application = new SimpleApplication();
        application.init();
        application.start();
    }
}
