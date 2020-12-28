package com.sim.server.modules.handler;

import com.alibaba.fastjson.JSON;
import com.sim.server.modules.command.CommandProcessor;
import com.sim.server.modules.command.CommandProcessorFactory;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xiaoshun.cxs
 * 12/27/2020
 **/
@Slf4j
public class ServerMsgDecoder extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        log.info("connection established");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("connection established");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        CommandProcessor commandProcessor = CommandProcessorFactory.getProcessor(JSON.toJSONString(msg));
        String returnMsg = commandProcessor.process(commandProcessor.getArgs(JSON.toJSONString(msg))[0]);
        ctx.writeAndFlush(returnMsg);
    }
}
