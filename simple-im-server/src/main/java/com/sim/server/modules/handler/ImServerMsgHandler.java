package com.sim.server.modules.handler;

import com.sim.common.utils.ByteBufUtils;
import com.sim.common.utils.StringUtils;
import com.sim.server.modules.command.CommandProcessor;
import com.sim.server.modules.command.CommandProcessorFactory;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author xiaoshun.cxs
 * 12/29/2020
 **/
@Slf4j
public class ImServerMsgHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        log.info("received message:{}", msg);
        CommandProcessor commandProcessor = CommandProcessorFactory.getProcessor(ctx, msg);
        String returnMsg = commandProcessor.process(msg);
        if (StringUtils.isNotEmpty(returnMsg)) {
            ctx.writeAndFlush(ByteBufUtils.writeStringWithLineBreak(returnMsg));
        }
    }

}
