package com.sim.common.server.modules.command;

import com.sim.common.exception.BizException;
import com.sim.common.exception.MessageCode;
import com.sim.common.utils.SpringUtils;
import com.sim.common.utils.StringUtils;

/**
 * @author xiaoshun.cxs
 * 2020/12/14
 **/
public class CommandProcessorFactory {

    /**
     * get specify processor based on user input message
     * @param message user input message
     * @return Processor
     * @throws BizException can not find related processor
     */
    public static CommandProcessor getProcessor(String message) throws BizException {
        CommandType[] commandTypes = CommandType.values();
        for (CommandType commandType:commandTypes) {
            if (message.startsWith(commandType.getType())) {
                return SpringUtils.getBean(commandType.getProcessor());
            }
        }

        throw new BizException(MessageCode.CUSTOM_ERROR, StringUtils.format("command:{} is not supported", message));
    }

}
