package com.sim.modules.user.service;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelOutboundHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaoshun.cxs
 * 2020/12/10
 **/
public class SessionManager {

    /**
     * MAP that store user's channelHandler
     */
    private static final Map<String, ChannelHandler[]> CHANNEL_HANDLER_MAP = new HashMap<>(128);

    private SessionManager() {}

    /**
     * register channel handler for the user
     * @param loginId                   loginId
     * @param channelInboundHandler     channelInBoundHandler that current user used
     * @param channelOutboundHandler    channelOutBoundHandler that current user used
     */
    public static void registerChannel(String loginId,
                                       ChannelInboundHandler channelInboundHandler,
                                       ChannelOutboundHandler channelOutboundHandler) {
        CHANNEL_HANDLER_MAP.put(loginId, new ChannelHandler[]{channelInboundHandler, channelOutboundHandler});
    }

    /**
     * get channel related to the user
     * @param loginId loginId
     * @return channelHandler, the sequence is {inBoundHandler, outBoundHandler}
     */
    public static ChannelHandler[] getChannel(String loginId) {
        return CHANNEL_HANDLER_MAP.get(loginId);
    }

}
