package com.sim.modules.command;

import com.sim.exception.BizException;
import com.sim.exception.MessageCode;
import com.sim.utils.SpringUtils;
import com.sim.utils.StringUtils;

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
