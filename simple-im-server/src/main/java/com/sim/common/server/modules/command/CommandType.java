package com.sim.common.server.modules.command;

import com.sim.common.server.modules.command.spec.HelpCommandProcessor;
import com.sim.common.server.modules.command.spec.LoginProcessor;
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
    HELP(":help", "help command", HelpCommandProcessor.class),
    SINGLE_CHAT(":sChat", "single chat", null),
    GROUP_CHAT(":gChat", "group chat in specify gid(optional)", null),
    GROUP_MEMBER_LIST(":gMemberList", "member list for specify group", null),
    JOIN_GROUP(":join", "join specify group, if joined,nothing happened,but switch to chat", null),
    QUIT_GROUP("quit", "quit specify group, if quited, nothing happened", null),
    ONLINE_USER_LIST(":ouList", "display online user list", null),
    GROUP_LIST(":groupList", "current group list", null),
    JOINED_GROUP_LIST(":joinGroupList", "group list that you current joined", null),
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
