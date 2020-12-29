package com.sim.client.handler.input;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.util.CharsetUtil;

import java.util.Scanner;

/**
 * @author xiaoshun.cxs
 * 12/29/2020
 **/
public class InputThread implements Runnable{

    private ChannelFuture channelFuture;

    public InputThread(ChannelFuture channelFuture) {
        this.channelFuture = channelFuture;
    }

    @Override
    public void run() {
        while(true) {
            Scanner scanner = new Scanner(System.in);
            String str = scanner.nextLine();
            channelFuture.channel().writeAndFlush(Unpooled.copiedBuffer(str, CharsetUtil.UTF_8));
            if ("quit".equals(str)) {
                break;
            }
        }
    }
}
