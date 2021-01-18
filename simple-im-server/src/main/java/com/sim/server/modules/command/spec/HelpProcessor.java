package com.sim.server.modules.command.spec;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.sim.common.exception.BizException;
import com.sim.common.msg.format.MsgParams;
import com.sim.common.msg.format.spec.HelpMsg;
import com.sim.server.modules.command.AbstractCommandProcessor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Help Command Processor
 * @author xiaoshun.cxs
 * 2020/12/8
 **/
@Component
public class HelpProcessor extends AbstractCommandProcessor<HelpMsg> {
    @Override
    public String process(String command) throws BizException {
        List<String> commandList = Arrays.asList(
                ":sChat loginId message - single chat",
                ":gChat groupName - group chat in specify gid",
                ":createGroup groupName - create group",
                ":gMemberList groupName - member list for specify group",
                ":join groupName - join specify group, if joined,nothing happened,but switch to chat",
                ":quit groupName - quit specify group, if quited, nothing happened",
                ":onlineUserList - online user list",
                ":groupList - current group list",
                ":joinedGroupList - group list that you current joined",
                ":login loginId:password - login with id and password"
        );

        return String.join("\n", commandList);
    }

    @Override
    protected HelpMsg getArgs(String message) {
        MsgParams<HelpMsg> msgParams = JSON.parseObject(message, new TypeReference<MsgParams<HelpMsg>>(){}.getType());
        return msgParams.getMsg();
    }

}
