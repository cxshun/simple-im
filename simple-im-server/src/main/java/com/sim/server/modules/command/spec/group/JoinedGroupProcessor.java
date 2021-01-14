package com.sim.server.modules.command.spec.group;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.sim.common.constant.CommonConstant;
import com.sim.common.exception.BizException;
import com.sim.common.msg.format.MsgParams;
import com.sim.common.msg.format.spec.group.JoinedGroupListMsg;
import com.sim.server.modules.command.AbstractCommandProcessor;
import com.sim.server.modules.group.entity.Group;
import com.sim.server.modules.group.service.GroupService;
import com.sim.server.modules.user.entity.User;
import com.sim.server.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xiaoshun.cxs
 * 2021/1/12
 **/
@Component
public class JoinedGroupProcessor extends AbstractCommandProcessor<JoinedGroupListMsg> {
    @Autowired
    private GroupService groupService;
    @Autowired
    private UserService userService;

    @Override
    public String process(String command) throws BizException {
        User user = userService.getBySessionId(getChannelHandlerContext().channel().id().asLongText());
        List<Group> groupList = groupService.getJoinedGroup(user.getId());

        return groupList.stream().map(Group::getName).collect(Collectors.joining(CommonConstant.SEPARATOR));
    }

    @Override
    protected JoinedGroupListMsg getArgs(String message) throws BizException {
        MsgParams<JoinedGroupListMsg> msgParams = JSON.parseObject(message, new TypeReference<MsgParams<JoinedGroupListMsg>>(){}.getType());
        return msgParams.getMsg();
    }
}
