package com.sim.common.server.modules.command.spec;

import com.sim.common.exception.BizException;
import com.sim.common.exception.MessageCode;
import com.sim.common.server.modules.command.AbstractCommandProcessor;
import com.sim.common.server.modules.user.entity.User;
import com.sim.common.server.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xiaoshun.cxs
 * 2020/12/9
 **/
@Component
public class LoginProcessor extends AbstractCommandProcessor {

    @Autowired
    private UserService userService;

    private static final Pattern PATTERN = Pattern.compile("\\(\\S*\\)");
    private static final String LOGIN_SUCCESS = "login success";

    @Override
    public String process(String command) throws BizException {
        String[] args = getArgs(command);
        User user = userService.getByLoginId(args[0]);
        //if not existed, then create new user
        if (user == null) {
            userService.insert(new User().setLoginId(args[0]).setPassword(args[1]));
            return LOGIN_SUCCESS;
        }

        //password not match
        if (!user.getPassword().equals(args[1])) {
            throw new BizException(MessageCode.BIZ_ERROR, "password not match");
        }
        return LOGIN_SUCCESS;
    }

    @Override
    public String[] getArgs(String command) throws BizException {
        Matcher matcher = PATTERN.matcher(command);
        if (!matcher.find()) {
            throw new BizException(MessageCode.BIZ_ERROR);
        }

        //if match, then we should extract loginId and password, check if match
        String loginIdAndPassword = matcher.group();
        return loginIdAndPassword.split(":");
    }
}
