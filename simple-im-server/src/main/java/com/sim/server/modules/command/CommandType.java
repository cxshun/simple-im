package com.sim.server.modules.command;

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
    HELP(":help", "help command", HelpProcessor.class),
    SINGLE_CHAT(":sChat", "single chat", SingleChatProcessor.class),
    CREATE_GROUP(":createGroup", "create a new group, current user automatically joined", CreateGroupProcessor.class),
    GROUP_CHAT(":gChat", "group chat in specify gid(optional)", GroupChatProcessor.class),
    GROUP_MEMBER_LIST(":gMemberList", "member list for specify group", GroupMemberListProcessor.class),
    JOIN_GROUP(":join", "join specify group, if joined,nothing happened,but switch to chat", JoinGroupProcessor.class),
    QUIT_GROUP("quit", "quit specify group, if quited, nothing happened", QuitGroupProcessor.class),
    ONLINE_USER_LIST(":onlineUserList", "display online user list", OnlineUserListProcessor.class),
    GROUP_LIST(":groupList", "current group list", GroupListProcessor.class),
    JOINED_GROUP_LIST(":joinGroupList", "group list that you current joined", JoinedGroupProcessor.class),
    LOGIN(":login", "login with id and password", LoginProcessor.class);

    private final String type;
    private final String desc;
    private final Class<? extends CommandProcessor> processor;
    CommandType(String type, String desc, Class<? extends CommandProcessor> processor) {
        this.type = type;
        this.desc = desc;
        this.processor = processor;
    }

}
