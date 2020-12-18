package com.sim.common.server.modules.command;

import com.sim.common.exception.BizException;

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

    /**
     * get user's command arguments
     * @param command user input command
     * @return current command's related argument
     */
    String[] getArgs(String command) throws BizException;

}
