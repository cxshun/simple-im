package com.sim.server.modules.command.spec.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.sim.common.exception.BizException;
import com.sim.common.utils.ByteBufUtils;
import com.sim.common.msg.format.MsgParams;
import com.sim.common.msg.format.spec.user.SingleChatMsg;
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
public class SingleChatProcessor extends AbstractCommandProcessor<SingleChatMsg> {
    @Autowired
    private UserService userService;

    @Override
    public String process(String command) throws BizException {
        SingleChatMsg singleChatMsg = getArgs(command);
        User user = userService.getByLoginId(singleChatMsg.getLoginId());

        ChannelHandlerContext channelHandlerContext = SessionManager.getChannel(user.getLoginId());
        channelHandlerContext.writeAndFlush(ByteBufUtils.writeStringWithLineBreak(singleChatMsg.getMsg()));
        return null;
    }

    @Override
    protected SingleChatMsg getArgs(String message) throws BizException {
        MsgParams<SingleChatMsg> msgParams = JSON.parseObject(message, new TypeReference<MsgParams<SingleChatMsg>>(){}.getType());
        return msgParams.getMsg();
    }
}
