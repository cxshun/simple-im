package com.sim.server.modules.command.spec.user;

import com.sim.common.exception.BizException;
import com.sim.common.exception.MessageCode;
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
public class LoginProcessor extends AbstractCommandProcessor {

    @Autowired
    private UserService userService;

    private static final String LOGIN_SUCCESS = "login success";

    @Override
    public String process(String command) throws BizException {
        String[] args = getArgs(command);
        User user = userService.getByLoginId(args[0]);
        //if not existed, then create new user
        if (user == null) {
            userService.insert(
                    new User()
                            .setLoginId(args[0])
                            .setPassword(args[1])
                            .setSessionId(getChannelHandlerContext().channel().id().asLongText())
            );
            SessionManager.registerChannel(args[0], this.getChannelHandlerContext());
            return LOGIN_SUCCESS;
        }

        //password not match
        if (!user.getPassword().equals(args[1])) {
            throw new BizException(MessageCode.BIZ_ERROR, "password not match");
        }

        //update current user's session
        user.setSessionId(getChannelHandlerContext().channel().id().asLongText());
        userService.update(user);
        SessionManager.registerChannel(args[0], this.getChannelHandlerContext());
        return LOGIN_SUCCESS;
    }

    @Override
    public String[] getArgs(String command) throws BizException {
        String[] msgs = command.split(" ");
        //if match, then we should extract loginId and password, check if match
        return msgs[1].split(":");
    }
}
