package com.sim.modules.command.spec;

import com.sim.exception.BizException;
import com.sim.exception.MessageCode;
import com.sim.modules.command.CommandProcessor;
import com.sim.modules.user.entity.User;
import com.sim.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xiaoshun.cxs
 * 2020/12/9
 **/
@Component
public class LoginProcessor implements CommandProcessor {

    @Autowired
    private UserService userService;

    private static final Pattern PATTERN = Pattern.compile("\\(\\S*\\)");
    private static final String LOGIN_SUCCESS = "login success";

    @Override
    public String process(String command) throws BizException {
        Matcher matcher = PATTERN.matcher(command);
        if (!matcher.find()) {
            throw new BizException(MessageCode.BIZ_ERROR);
        }

        //if match, then we should extract loginId and password, check if match
        String loginIdAndPassword = matcher.group();
        String[] strs = loginIdAndPassword.split(":");
        User user = userService.getByLoginId(strs[0]);
        //if not existed, then create new user
        if (user == null) {
            userService.insert(new User().setLoginId(strs[0]).setPassword(strs[1]));
            return LOGIN_SUCCESS;
        }

        //password not match
        if (!user.getPassword().equals(strs[1])) {
            throw new BizException(MessageCode.BIZ_ERROR, "password not match");
        }
        return LOGIN_SUCCESS;
    }
}
