package com.sim.server.modules.command;

import com.sim.common.exception.BizException;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author xiaoshun.cxs
 * 2020/12/8
 **/
public interface CommandProcessor {

    /**
     * get channelHandlerContext
     * @return channelHandlerContext
     */
    ChannelHandlerContext getChannelHandlerContext();

    /**
     * set current relate handlerContext
     * @param channelHandlerContext relate to the current command client
     */
    void setChannelHandlerContext(ChannelHandlerContext channelHandlerContext);

    /**
     * processor for user input command
     * @param command  user input command
     * @return result after process
     * @throws BizException when some error happened, an BizException will be thrown
     */
    String process(String command) throws BizException;

    /**
     * get user's command arguments
     * @param command user input command
     * @return current command's related argument
     * @throws BizException when some error happened, an BizException will be thrown
     */
    String[] getArgs(String command) throws BizException;

}
