package com.sim.server.modules.command.spec;

import com.sim.common.exception.BizException;
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
public class GroupListProcessor extends AbstractCommandProcessor {

    @Autowired
    private GroupService groupService;

    @Override
    public String process(String command) throws BizException {
        List<Group> groupList = groupService.list();
        return groupList.stream()
                .map(group -> group.getName() + ":" + group.getId())
                .collect(Collectors.joining("\n"));
    }
}
