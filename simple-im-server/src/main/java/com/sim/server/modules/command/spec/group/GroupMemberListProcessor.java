package com.sim.server.modules.command.spec.group;

import com.sim.common.constant.CommonConstant;
import com.sim.common.exception.BizException;
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
public class GroupMemberListProcessor extends AbstractCommandProcessor {
    @Autowired
    private GroupService groupService;
    @Autowired
    private UserService userService;

    @Override
    public String process(String command) throws BizException {
        String[] args = getArgs(command);
        List<GroupMemberRel> groupMemberRelList = groupService.memberList(args[1]);
        List<Long> uidList = groupMemberRelList.stream().map(GroupMemberRel::getUid).collect(Collectors.toList());
        List<User> userList = userService.listByIds(uidList);
        return userList.stream().map(User::getLoginId).collect(Collectors.joining(CommonConstant.SEPARATOR));
    }
}
