package com.sim.modules.command;

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
    HELP(":help", "help command"),
    SINGLE_CHAT(":sChat", "single chat"),
    GROUP_CHAT(":gChat", "group chat in specify gid(optional)"),
    GROUP_MEMBER_LIST(":gMemberList", "member list for specify group"),
    JOIN_GROUP(":join", "join specify group, if joined,nothing happened,but switch to chat"),
    QUIT_GROUP("quit", "quit specify group, if quited, nothing happened"),
    ONLINE_USER_LIST(":ouList", "display online user list"),
    GROUP_LIST(":groupList", "current group list"),
    JOINED_GROUP_LIST(":joinGroupList", "group list that you current joined"),
    LOGIN(":login", "login with id and password");

    private final String type;
    private final String desc;
    CommandType(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

}
