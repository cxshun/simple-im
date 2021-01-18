package com.sim.server.modules.command.spec.group;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.sim.common.exception.BizException;
import com.sim.common.exception.MessageCode;
import com.sim.common.msg.format.MsgParams;
import com.sim.common.msg.format.spec.group.QuitGroupMsg;
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
public class QuitGroupProcessor extends AbstractCommandProcessor<QuitGroupMsg> {
    @Autowired
    private GroupService groupService;
    @Autowired
    private UserService userService;

    @Override
    public String process(String command) throws BizException {
        QuitGroupMsg quitGroupMsg = getArgs(command);
        User user = userService.getBySessionId(getChannelHandlerContext().channel().id().asLongText());
        groupService.quit(quitGroupMsg.getGroupName(), user.getId());
        return MessageCode.SUCCESS.getDesc();
    }

    @Override
    protected QuitGroupMsg getArgs(String message) {
        MsgParams<QuitGroupMsg> msgParams = JSON.parseObject(message, new TypeReference<MsgParams<QuitGroupMsg>>(){}.getType());
        return msgParams.getMsg();
    }
}
