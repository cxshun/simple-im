package com.sim.client.handler.msg;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xiaoshun.cxs
 * 12/29/2020
 **/
@Slf4j
public class ImClientMsgHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        log.info("received msg:\n{}", msg);
    }
}
