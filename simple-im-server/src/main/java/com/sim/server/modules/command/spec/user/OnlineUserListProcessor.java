package com.sim.server.modules.command.spec.user;

import com.sim.common.exception.BizException;
import com.sim.server.modules.command.AbstractCommandProcessor;
import com.sim.server.modules.user.service.SessionManager;
import org.springframework.stereotype.Component;

/**
 * @author xiaoshun.cxs
 * 2021/1/11
 **/
@Component
public class OnlineUserListProcessor extends AbstractCommandProcessor {
    @Override
    public String process(String command) throws BizException {
        return String.join("\n", SessionManager.list());
    }
}
