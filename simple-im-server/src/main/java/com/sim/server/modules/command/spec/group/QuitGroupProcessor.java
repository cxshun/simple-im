package com.sim.server.modules.command.spec.group;

import com.sim.common.exception.BizException;
import com.sim.common.exception.MessageCode;
import com.sim.server.modules.command.AbstractCommandProcessor;
import com.sim.server.modules.group.service.GroupService;
import com.sim.server.modules.user.entity.User;
import com.sim.server.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author xiaoshun.cxs
 * 2021/1/12
 **/
@Component
public class QuitGroupProcessor extends AbstractCommandProcessor {
    @Autowired
    private GroupService groupService;
    @Autowired
    private UserService userService;

    @Override
    public String process(String command) throws BizException {
        String[] args = getArgs(command);
        User user = userService.getBySessionId(getChannelHandlerContext().channel().id().asLongText());
        groupService.quit(args[1], user.getId());
        return MessageCode.SUCCESS.getDesc();
    }
}
