package com.sim.server.modules.chat.handler;

import com.sim.server.modules.command.CommandProcessor;
import com.sim.server.modules.command.CommandProcessorFactory;
import com.sim.server.modules.user.service.SessionManager;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * @author xiaoshun.cxs
 * 2020/12/14
 **/
@Component
public class ChatServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        SessionManager.unregisterChannel(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        CommandProcessor processor = CommandProcessorFactory.getProcessor(msg.toString());
        SessionManager.registerChannel(processor.getArgs(msg.toString())[0], ctx);
        String returnMsg = processor.process(msg.toString());
        if (StringUtils.isNotBlank(returnMsg)) {
            ctx.writeAndFlush(processor.process(msg.toString()));
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        SessionManager.unregisterChannel(ctx);
    }

}
