package com.sim.server.modules.group.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sim.server.AbstractServiceImpl;
import com.sim.server.modules.group.dao.GroupMemberRelMapper;
import com.sim.server.modules.group.entity.GroupMemberRel;
import com.sim.server.modules.group.service.GroupMemberRelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xiaoshun.cxs
 * 12/22/2020
 **/
@Service
public class GroupMemberRelServiceImpl extends AbstractServiceImpl<GroupMemberRel, Long> implements GroupMemberRelService {

    @Autowired
    private GroupMemberRelMapper groupMemberRelMapper;

    @Override
    protected BaseMapper<GroupMemberRel> getMapper() {
        return groupMemberRelMapper;
    }
}
