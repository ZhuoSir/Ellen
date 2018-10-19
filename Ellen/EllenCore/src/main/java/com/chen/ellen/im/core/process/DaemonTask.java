package com.chen.ellen.im.core.process;

public abstract class DaemonTask extends Thread {

    protected boolean isStart = false;

    @Override
    public void run() {
        super.run();
        this.isStart = true;
        taskDetail();
    }

    abstract void taskDetail();
}
