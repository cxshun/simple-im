package com.sim.common.msg;

import com.sim.common.msg.format.spec.HelpMsg;
import com.sim.common.msg.format.spec.MsgSpec;
import com.sim.common.msg.format.spec.group.*;
import com.sim.common.msg.format.spec.user.LoginMsg;
import com.sim.common.msg.format.spec.user.OnlineUserListMsg;
import com.sim.common.msg.format.spec.user.SingleChatMsg;
import lombok.Getter;

/**
 * @author xiaoshun.cxs
 * 2021/1/18
 **/
@Getter
public enum MsgType {

    /**
     * command type that currently support
     */
    HELP(":help", "help command", HelpMsg.class),
    SINGLE_CHAT(":sChat", "single chat", SingleChatMsg.class),
    CREATE_GROUP(":createGroup", "create a new group, current user automatically joined", CreateGroupMsg.class),
    GROUP_CHAT(":gChat", "group chat in specify gid(optional)", GroupChatMsg.class),
    GROUP_MEMBER_LIST(":gMemberList", "member list for specify group", GroupMemberListMsg.class),
    JOIN_GROUP(":joinGroup", "join specify group, if joined,nothing happened,but switch to chat", JoinGroupMsg.class),
    QUIT_GROUP(":quitGroup", "quit specify group, if quited, nothing happened", QuitGroupMsg.class),
    ONLINE_USER_LIST(":onlineUserList", "display online user list", OnlineUserListMsg.class),
    GROUP_LIST(":groupList", "current group list", GroupListMsg.class),
    JOINED_GROUP_LIST(":joinedGroupList", "group list that you current joined", JoinedGroupListMsg.class),
    LOGIN(":login", "login with id and password", LoginMsg.class);

    private final String prefix;
    private final String desc;
    private final Class<? extends MsgSpec> msgSpec;

    MsgType(String prefix, String desc, Class<? extends MsgSpec> msgSpec) {
        this.prefix = prefix;
        this.desc = desc;
        this.msgSpec = msgSpec;
    }

    public static MsgType getByPrefix(String prefix) {
        MsgType[] msgTypes = values();
        for (MsgType msgType:msgTypes) {
            if (msgType.getPrefix().equals(prefix)) {
                return msgType;
            }
        }
        return null;
    }

}
