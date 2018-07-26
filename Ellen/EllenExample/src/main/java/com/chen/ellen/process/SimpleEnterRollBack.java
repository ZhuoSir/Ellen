package com.chen.ellen.process;

import com.chen.ellen.console.$;

public class SimpleEnterRollBack implements EnterRollBack {

    @Override
    public void enter(String enter) {
        $.echo(enter);
    }
}
