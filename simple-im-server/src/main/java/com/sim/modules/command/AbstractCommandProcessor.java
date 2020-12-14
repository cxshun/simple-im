package com.sim.modules.command;

import com.sim.exception.BizException;

/**
 * @author xiaoshun.cxs
 * 2020/12/14
 **/
public abstract class AbstractCommandProcessor implements CommandProcessor{

    @Override
    public String[] getArgs(String command) throws BizException {
        return new String[0];
    }
}
