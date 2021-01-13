package com.sim.server.modules.handler;

import com.sim.common.utils.ByteBufUtils;
import com.sim.server.modules.user.service.SessionManager;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author xiaoshun.cxs
 * 12/27/2020
 **/
@Slf4j
public class ServerMsgDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        out.add(ByteBufUtils.fromByteBuf(in));
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        log.info("connection established");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("connection active");
        ctx.writeAndFlush(ByteBufUtils.writeStringWithLineBreak("Your connection is active, now chat with pleasure"));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error(cause.getMessage(), cause);
        SessionManager.unregisterChannel(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("connection inactive");
        SessionManager.unregisterChannel(ctx);
    }
}
