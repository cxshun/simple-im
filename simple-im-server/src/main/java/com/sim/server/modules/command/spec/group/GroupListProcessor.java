package com.sim.server.modules.command.spec.group;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.sim.common.constant.CommonConstant;
import com.sim.common.exception.BizException;
import com.sim.common.msg.format.MsgParams;
import com.sim.common.msg.format.spec.group.GroupListMsg;
import com.sim.server.modules.command.AbstractCommandProcessor;
import com.sim.server.modules.group.entity.Group;
import com.sim.server.modules.group.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xiaoshun.cxs
 * 12/21/2020
 **/
@Component
public class GroupListProcessor extends AbstractCommandProcessor<GroupListMsg> {

    @Autowired
    private GroupService groupService;

    @Override
    public String process(String command) throws BizException {
        List<Group> groupList = groupService.list();
        return groupList.stream()
                .map(Group::getName)
                .collect(Collectors.joining(CommonConstant.SEPARATOR));
    }

    @Override
    protected GroupListMsg getArgs(String message) {
        MsgParams<GroupListMsg> msgParams = JSON.parseObject(message, new TypeReference<MsgParams<GroupListMsg>>(){}.getType());
        return msgParams.getMsg();
    }
}
