package com.sim.modules.command;

import com.sim.exception.BizException;

/**
 * @author xiaoshun.cxs
 * 2020/12/8
 **/
public interface CommandProcessor {

    /**
     * processor for user input command
     * @param command  user input command
     * @return result after process
     * @throws BizException when something error happened, an BizException will be thrown
     */
    String process(String command) throws BizException;

}
