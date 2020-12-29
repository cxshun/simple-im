package com.sim.server.modules.handler;

import com.alibaba.fastjson.JSON;
import com.sim.server.modules.command.CommandProcessor;
import com.sim.server.modules.command.CommandProcessorFactory;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xiaoshun.cxs
 * 12/29/2020
 **/
@Slf4j
public class ImServerMsgHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        log.info("received message:{}", msg);
        CommandProcessor commandProcessor = CommandProcessorFactory.getProcessor(JSON.toJSONString(msg));
        String returnMsg = commandProcessor.process(commandProcessor.getArgs(JSON.toJSONString(msg))[0]);
        ctx.writeAndFlush(returnMsg);
    }

}
