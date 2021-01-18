package com.sim.server.modules.command;

import com.sim.common.msg.MsgType;
import com.sim.server.modules.command.spec.HelpProcessor;
import com.sim.server.modules.command.spec.group.*;
import com.sim.server.modules.command.spec.user.LoginProcessor;
import com.sim.server.modules.command.spec.user.OnlineUserListProcessor;
import com.sim.server.modules.command.spec.user.SingleChatProcessor;
import lombok.Getter;

/**
 * @author xiaoshun.cxs
 * 2020/12/9
 **/
@Getter
public enum CommandType {
    /**
     * command type that currently support
     */
    HELP(MsgType.HELP, HelpProcessor.class),
    SINGLE_CHAT(MsgType.SINGLE_CHAT, SingleChatProcessor.class),
    CREATE_GROUP(MsgType.CREATE_GROUP, CreateGroupProcessor.class),
    GROUP_CHAT(MsgType.GROUP_CHAT, GroupChatProcessor.class),
    GROUP_MEMBER_LIST(MsgType.GROUP_MEMBER_LIST, GroupMemberListProcessor.class),
    JOIN_GROUP(MsgType.JOIN_GROUP, JoinGroupProcessor.class),
    QUIT_GROUP(MsgType.QUIT_GROUP, QuitGroupProcessor.class),
    ONLINE_USER_LIST(MsgType.ONLINE_USER_LIST, OnlineUserListProcessor.class),
    GROUP_LIST(MsgType.GROUP_LIST, GroupListProcessor.class),
    JOINED_GROUP_LIST(MsgType.JOINED_GROUP_LIST, JoinedGroupProcessor.class),
    LOGIN(MsgType.LOGIN, LoginProcessor.class);

    private final MsgType msgType;
    private final Class<? extends CommandProcessor> processor;
    CommandType(MsgType msgType, Class<? extends CommandProcessor> processor) {
        this.msgType = msgType;
        this.processor = processor;
    }

}
