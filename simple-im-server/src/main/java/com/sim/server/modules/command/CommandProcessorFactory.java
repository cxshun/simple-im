package com.sim.server.modules.command;

import com.alibaba.fastjson.JSON;
import com.sim.common.exception.BizException;
import com.sim.common.exception.MessageCode;
import com.sim.common.utils.SpringUtils;
import com.sim.common.utils.StringUtils;
import com.sim.common.msg.format.MsgParams;
import io.netty.channel.ChannelHandlerContext;

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
    public static CommandProcessor getProcessor(ChannelHandlerContext channelHandlerContext, String message) throws BizException {
        CommandType[] commandTypes = CommandType.values();
        MsgParams<?> msgParams = JSON.parseObject(message, MsgParams.class);
        for (CommandType commandType:commandTypes) {
            if (msgParams.getAction().equalsIgnoreCase(commandType.getType())) {
                CommandProcessor commandProcessor = SpringUtils.getBean(commandType.getProcessor());
                commandProcessor.setChannelHandlerContext(channelHandlerContext);
                return commandProcessor;
            }
        }

        throw new BizException(MessageCode.CUSTOM_ERROR, StringUtils.format("command:{} is not supported", msgParams));
    }

}
