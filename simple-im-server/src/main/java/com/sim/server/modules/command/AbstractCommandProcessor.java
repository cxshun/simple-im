package com.sim.server.modules.command;

import com.sim.common.exception.BizException;
import io.netty.channel.ChannelHandlerContext;
import lombok.Data;

/**
 * @author xiaoshun.cxs
 * 2020/12/14
 **/
@Data
public abstract class AbstractCommandProcessor<T> implements CommandProcessor{

    protected ChannelHandlerContext channelHandlerContext;

    /**
     * get current request detail msg
     * @param message message
     * @return detail msg dto
     * @throws BizException some error happened
     */
    protected abstract T getArgs(String message) throws BizException;

}
