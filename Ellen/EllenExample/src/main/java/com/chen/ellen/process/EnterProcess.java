package com.chen.ellen.process;

import com.chen.ellen.console.$;
import com.chen.ellen.process.EnterRollBack;

public class EnterProcess extends Thread {

    private EnterModel enterModel;

    private EnterRollBack rollBack;

    public EnterProcess(EnterRollBack rollBack) {
        this.rollBack = rollBack;
        this.enterModel = EnterModel.COMMAND;
    }

    @Override
    public void run() {
        while (true) {
            String enter = $.enter();
            rollBack.enter(enter);
        }
    }
}
