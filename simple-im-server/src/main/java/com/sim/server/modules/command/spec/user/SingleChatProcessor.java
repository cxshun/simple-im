package com.sim.server.modules.command.spec.user;

import com.sim.common.constant.CommonConstant;
import com.sim.common.exception.BizException;
import com.sim.common.utils.ByteBufUtils;
import com.sim.server.modules.command.AbstractCommandProcessor;
import com.sim.server.modules.user.entity.User;
import com.sim.server.modules.user.service.SessionManager;
import com.sim.server.modules.user.service.UserService;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        User user = userService.getByLoginId(args[1]);

        String message = IntStream.range(2, args.length).boxed()
                .map(idx -> args[idx])
                .collect(Collectors.joining(" "));
        ChannelHandlerContext channelHandlerContext = SessionManager.getChannel(user.getLoginId());
        channelHandlerContext.writeAndFlush(ByteBufUtils.writeStringWithLineBreak(message));
        return null;
    }
}
