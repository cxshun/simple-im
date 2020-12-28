package com.sim.server.modules.command.spec;

import com.sim.common.exception.BizException;
import com.sim.server.modules.command.AbstractCommandProcessor;
import com.sim.server.modules.user.entity.User;
import com.sim.server.modules.user.service.SessionManager;
import com.sim.server.modules.user.service.UserService;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author xiaoshun.cxs
 * 12/21/2020
 **/
@Component
public class SingleChatProcessor extends AbstractCommandProcessor {
    @Autowired
    private UserService userService;

    @Override
    public String process(String command) throws BizException {
        String[] args = getArgs(command);
        User user = userService.detail(Long.parseLong(args[0]));
        ChannelHandlerContext channelHandlerContext = SessionManager.getChannel(user.getLoginId());
        channelHandlerContext.writeAndFlush(args[1]);
        return null;
    }
}
