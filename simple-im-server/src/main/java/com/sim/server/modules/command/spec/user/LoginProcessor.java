package com.sim.server.modules.command.spec.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.sim.common.exception.BizException;
import com.sim.common.exception.MessageCode;
import com.sim.common.msg.format.MsgParams;
import com.sim.common.msg.format.spec.user.LoginMsg;
import com.sim.server.modules.command.AbstractCommandProcessor;
import com.sim.server.modules.user.entity.User;
import com.sim.server.modules.user.service.SessionManager;
import com.sim.server.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author xiaoshun.cxs
 * 2020/12/9
 **/
@Component
public class LoginProcessor extends AbstractCommandProcessor<LoginMsg> {

    @Autowired
    private UserService userService;

    @Override
    public String process(String message) throws BizException {
        LoginMsg loginMsg = getArgs(message);
        User user = userService.getByLoginId(loginMsg.getLoginId());
        //if not existed, then create new user
        if (user == null) {
            userService.insert(
                    new User()
                            .setLoginId(loginMsg.getLoginId())
                            .setPassword(loginMsg.getPassword())
                            .setSessionId(getChannelHandlerContext().channel().id().asLongText())
            );
            SessionManager.registerChannel(loginMsg.getLoginId(), this.getChannelHandlerContext());
            return MessageCode.SUCCESS.getDesc();
        }

        //password not match
        if (!user.getPassword().equals(loginMsg.getPassword())) {
            throw new BizException(MessageCode.CUSTOM_ERROR, "password not match");
        }

        //update current user's session
        user.setSessionId(getChannelHandlerContext().channel().id().asLongText());
        userService.update(user);
        SessionManager.registerChannel(loginMsg.getLoginId(), this.getChannelHandlerContext());
        return MessageCode.SUCCESS.getDesc();
    }

    @Override
    protected LoginMsg getArgs(String message) throws BizException {
        MsgParams<LoginMsg> msgParams = JSON.parseObject(message, new TypeReference<MsgParams<LoginMsg>>(){}.getType());
        return msgParams.getMsg();
    }
}
