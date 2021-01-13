package com.sim.server.modules.command;

import com.sim.common.exception.BizException;
import io.netty.channel.ChannelHandlerContext;
import lombok.Data;

/**
 * @author xiaoshun.cxs
 * 2020/12/14
 **/
@Data
public abstract class AbstractCommandProcessor implements CommandProcessor{

    protected ChannelHandlerContext channelHandlerContext;

    @Override
    public String[] getArgs(String command) throws BizException {
        return command.split(" ");
    }

}
