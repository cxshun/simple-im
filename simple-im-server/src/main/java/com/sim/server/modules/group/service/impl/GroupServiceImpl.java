package com.sim.server.modules.group.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sim.server.AbstractServiceImpl;
import com.sim.server.modules.group.dao.GroupMapper;
import com.sim.server.modules.group.entity.Group;
import com.sim.server.modules.group.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiaoshun.cxs
 * 12/21/2020
 **/
@Service
public class GroupServiceImpl extends AbstractServiceImpl<Group, Long> implements GroupService {

    @Autowired
    private GroupMapper groupMapper;

    @Override
    protected BaseMapper<Group> getMapper() {
        return groupMapper;
    }

    @Override
    public List<Group> list() {
        return groupMapper.selectList(new QueryWrapper<>());
    }
}
