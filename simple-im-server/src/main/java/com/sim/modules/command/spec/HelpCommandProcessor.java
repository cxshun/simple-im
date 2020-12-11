package com.sim.modules.command.spec;

import com.sim.modules.command.CommandProcessor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Help Command Processor
 * @author xiaoshun.cxs
 * 2020/12/8
 **/
@Component
public class HelpCommandProcessor implements CommandProcessor {
    @Override
    public String process(String command) {
        List<String> commandList = Arrays.asList(
                ":sChat(uid) - single chat",
                ":gChat[(gid)] - group chat in specify gid(optional)",
                ":gMemberList(gid) - member list for specify group",
                ":join(groupId) - join specify group, if joined,nothing happened,but switch to chat",
                ":quit(groupId) - quit specify group, if quited, nothing happened",
                ":ouList - online user list",
                ":groupList - current group list",
                ":joinGroupList - group list that you current joined",
                ":login(loginId:password) - login with id and password"
        );

        return String.join("\n", commandList);
    }
}
