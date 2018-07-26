package com.chen.ellen.service;

import com.chen.ellen.Application;
import com.chen.ellen.console.$;
import com.chen.ellen.process.EnterProcess;
import com.chen.ellen.process.SimpleEnterRollBack;
import org.apache.log4j.Logger;

import java.util.concurrent.CountDownLatch;

public class SimpleApplication implements Application {

    private Logger logger = Logger.getLogger(SimpleApplication.class);

    private EnterProcess enterProcess;

    @Override
    public void init() {
        enterProcess = new EnterProcess(new SimpleEnterRollBack());
    }

    @Override
    public void start() {

        try {
            $.echo("welcome to use Ellen IM.");
            CountDownLatch latch = new CountDownLatch(1);
            enterProcess.start();
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
            logger.error(e);
        }

    }
}
