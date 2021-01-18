package com.sim.client.handler.input;

import com.alibaba.fastjson.JSON;
import com.sim.common.exception.BizException;
import com.sim.common.msg.format.MsgParams;
import com.sim.common.msg.format.spec.MsgSpec;
import com.sim.common.msg.parser.ParserFactory;
import com.sim.common.utils.ByteBufUtils;
import io.netty.channel.ChannelFuture;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/**
 * @author xiaoshun.cxs
 * 12/29/2020
 **/
@Slf4j
public class InputThread implements Runnable{

    private final ChannelFuture channelFuture;

    public InputThread(ChannelFuture channelFuture) {
        this.channelFuture = channelFuture;
    }

    @Override
    public void run() {
        while(true) {
            Scanner scanner = new Scanner(System.in);
            String str = scanner.nextLine();
            if ("quit".equals(str)) {
                break;
            }

            try {
                MsgParams<MsgSpec> msgParams = ParserFactory.parse(str);
                channelFuture.channel().writeAndFlush(ByteBufUtils.writeStringWithLineBreak(JSON.toJSONString(msgParams)));
            } catch (BizException|IllegalAccessException|InstantiationException e) {
                log.error(e.getMessage(), e);
            }
        }
    }
}
