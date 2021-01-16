package com.sim.server.modules.command.spec.group;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.sim.common.exception.BizException;
import com.sim.common.exception.MessageCode;
import com.sim.common.msg.format.MsgParams;
import com.sim.common.msg.format.spec.group.JoinGroupMsg;
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
public class JoinGroupProcessor extends AbstractCommandProcessor<JoinGroupMsg> {
    @Autowired
    private GroupService groupService;
    @Autowired
    private UserService userService;

    @Override
    public String process(String command) throws BizException {
        User user = userService.getBySessionId(getChannelHandlerContext().channel().id().asLongText());
        JoinGroupMsg joinGroupMsg = getArgs(command);
        groupService.join(joinGroupMsg.getGroupName(), user.getId());
        return MessageCode.SUCCESS.getDesc();
    }

    @Override
    protected JoinGroupMsg getArgs(String message) {
        MsgParams<JoinGroupMsg> msgParams = JSON.parseObject(message, new TypeReference<MsgParams<JoinGroupMsg>>(){}.getType());
        return msgParams.getMsg();
    }
}
