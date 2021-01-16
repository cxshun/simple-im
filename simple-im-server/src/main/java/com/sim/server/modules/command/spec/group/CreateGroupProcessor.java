package com.sim.server.modules.command.spec.group;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.sim.common.exception.BizException;
import com.sim.common.exception.MessageCode;
import com.sim.common.msg.format.MsgParams;
import com.sim.common.msg.format.spec.group.CreateGroupMsg;
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
public class CreateGroupProcessor extends AbstractCommandProcessor<CreateGroupMsg> {
    @Autowired
    private UserService userService;
    @Autowired
    private GroupService groupService;

    @Override
    public String process(String command) throws BizException {
        CreateGroupMsg createGroupMsg = getArgs(command);
        User user = userService.getBySessionId(getChannelHandlerContext().channel().id().asLongText());
        groupService.createGroup(createGroupMsg.getGroupName(), user.getId());
        return MessageCode.SUCCESS.getDesc();
    }

    @Override
    protected CreateGroupMsg getArgs(String message) {
        MsgParams<CreateGroupMsg> msgParams = JSON.parseObject(message, new TypeReference<MsgParams<CreateGroupMsg>>(){}.getType());
        return msgParams.getMsg();
    }
}
