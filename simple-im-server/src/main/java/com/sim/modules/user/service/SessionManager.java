package com.sim.modules.user.service;

import io.netty.channel.ChannelHandlerContext;

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
    private static final Map<String, ChannelHandlerContext> CHANNEL_HANDLER_CONTEXT_MAP = new HashMap<>(128);

    private SessionManager() {}

    /**
     * register channel handler for the user
     * @param loginId                   loginId
     * @param channelHandlerContext    channelOutBoundHandler that current user used
     */
    public static void registerChannel(String loginId,
                                       ChannelHandlerContext channelHandlerContext) {
        CHANNEL_HANDLER_CONTEXT_MAP.put(loginId, channelHandlerContext);
    }

    /**
     * unregister channelHandlerContext from contextMap
     * @param channelHandlerContext specify context
     */
    public static void unregisterChannel(ChannelHandlerContext channelHandlerContext) {
        CHANNEL_HANDLER_CONTEXT_MAP.entrySet().removeIf(entry ->
            entry.getValue().channel().id().equals(channelHandlerContext.channel().id())
        );
    }

    /**
     * get channel related to the user
     * @param loginId loginId
     * @return channelHandlerContext, the sequence is {inBoundHandler, outBoundHandler}
     */
    public static ChannelHandlerContext getChannel(String loginId) {
        return CHANNEL_HANDLER_CONTEXT_MAP.get(loginId);
    }

    /**
     * remove channel related to ths user
     * @param loginId loginId
     */
    public static void removeChannel(String loginId) {
        CHANNEL_HANDLER_CONTEXT_MAP.remove(loginId);
    }

}
