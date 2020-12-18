package com.sim.common.server.modules.chat.handler;

import com.sim.common.server.modules.command.CommandProcessor;
import com.sim.common.server.modules.command.CommandProcessorFactory;
import com.sim.common.server.modules.user.service.SessionManager;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author xiaoshun.cxs
 * 2020/12/14
 **/
public class ChatServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        SessionManager.unregisterChannel(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        CommandProcessor processor = CommandProcessorFactory.getProcessor(msg.toString());
        SessionManager.registerChannel(processor.getArgs(msg.toString())[0], ctx);
        ctx.writeAndFlush(processor.process(msg.toString()));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        SessionManager.unregisterChannel(ctx);
    }

}
