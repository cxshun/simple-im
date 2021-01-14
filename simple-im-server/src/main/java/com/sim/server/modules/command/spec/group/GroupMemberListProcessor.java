package com.sim.server.modules.command.spec.group;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.sim.common.constant.CommonConstant;
import com.sim.common.exception.BizException;
import com.sim.common.msg.format.MsgParams;
import com.sim.common.msg.format.spec.group.GroupMemberListMsg;
import com.sim.server.modules.command.AbstractCommandProcessor;
import com.sim.server.modules.group.entity.GroupMemberRel;
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
public class GroupMemberListProcessor extends AbstractCommandProcessor<GroupMemberListMsg> {
    @Autowired
    private GroupService groupService;
    @Autowired
    private UserService userService;

    @Override
    public String process(String command) throws BizException {
        GroupMemberListMsg groupMemberListMsg = getArgs(command);
        List<GroupMemberRel> groupMemberRelList = groupService.memberList(groupMemberListMsg.getGroupName());
        List<Long> uidList = groupMemberRelList.stream().map(GroupMemberRel::getUid).collect(Collectors.toList());
        List<User> userList = userService.listByIds(uidList);
        return userList.stream().map(User::getLoginId).collect(Collectors.joining(CommonConstant.SEPARATOR));
    }

    @Override
    protected GroupMemberListMsg getArgs(String message) throws BizException {
        MsgParams<GroupMemberListMsg> msgParams = JSON.parseObject(message, new TypeReference<MsgParams<GroupMemberListMsg>>(){}.getType());
        return msgParams.getMsg();
    }
}
