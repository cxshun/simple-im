package com.sim.server.modules.command.spec.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.sim.common.exception.BizException;
import com.sim.common.msg.format.MsgParams;
import com.sim.common.msg.format.spec.user.OnlineUserListMsg;
import com.sim.server.modules.command.AbstractCommandProcessor;
import com.sim.server.modules.user.service.SessionManager;
import org.springframework.stereotype.Component;

/**
 * @author xiaoshun.cxs
 * 2021/1/11
 **/
@Component
public class OnlineUserListProcessor extends AbstractCommandProcessor<OnlineUserListMsg> {
    @Override
    public String process(String command) throws BizException {
        return String.join("\n", SessionManager.list());
    }

    @Override
    protected OnlineUserListMsg getArgs(String message) throws BizException {
        MsgParams<OnlineUserListMsg> msgParams = JSON.parseObject(message, new TypeReference<MsgParams<OnlineUserListMsg>>(){}.getType());
        return msgParams.getMsg();
    }
}
