package com.sim.server.modules.command.spec.group;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.sim.common.exception.BizException;
import com.sim.common.utils.ByteBufUtils;
import com.sim.common.msg.format.MsgParams;
import com.sim.common.msg.format.spec.group.GroupChatMsg;
import com.sim.server.modules.command.AbstractCommandProcessor;
import com.sim.server.modules.group.entity.GroupMemberRel;
import com.sim.server.modules.group.service.GroupService;
import com.sim.server.modules.user.entity.User;
import com.sim.server.modules.user.service.SessionManager;
import com.sim.server.modules.user.service.UserService;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xiaoshun.cxs
 * 2021/1/12
 **/
@Component
public class GroupChatProcessor extends AbstractCommandProcessor<GroupChatMsg> {
    @Autowired
    private GroupService groupService;
    @Autowired
    private UserService userService;

    @Override
    public String process(String command) throws BizException {
        GroupChatMsg groupChatMsg = getArgs(command);

        //1、get current group's memberList
        List<GroupMemberRel> groupMemberRelList = groupService.memberList(groupChatMsg.getGroupName());
        User user = userService.getBySessionId(getChannelHandlerContext().channel().id().asLongText());
        //2、filter current user，current user don't need to receive his own message
        groupMemberRelList = groupMemberRelList.stream()
                .filter(groupMemberRel -> !groupMemberRel.getUid().equals(user.getId()))
                .collect(Collectors.toList());
        List<Long> uidList = groupMemberRelList.stream()
                .map(GroupMemberRel::getUid)
                .collect(Collectors.toList());
        //3、get group member's loginIdList
        List<User> userList = userService.listByIds(uidList);
        List<String> loginIdList = userList.stream().map(User::getLoginId).collect(Collectors.toList());

        //4、get channelHandlerContext in order to sending message
        List<ChannelHandlerContext> channelHandlerContextList = SessionManager.listByLoginIds(loginIdList);
        channelHandlerContextList.forEach(channelHandlerContext -> {
            channelHandlerContext.writeAndFlush(ByteBufUtils.writeStringWithLineBreak(groupChatMsg.getMsg()));
        });
        return null;
    }

    @Override
    protected GroupChatMsg getArgs(String message) throws BizException {
        MsgParams<GroupChatMsg> msgParams = JSON.parseObject(message, new TypeReference<MsgParams<GroupChatMsg>>(){}.getType());
        return msgParams.getMsg();
    }
}
