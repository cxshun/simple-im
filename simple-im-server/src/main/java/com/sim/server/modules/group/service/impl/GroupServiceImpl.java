package com.sim.server.modules.group.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sim.server.AbstractServiceImpl;
import com.sim.server.modules.group.dao.GroupMapper;
import com.sim.server.modules.group.dao.GroupMemberRelMapper;
import com.sim.server.modules.group.entity.Group;
import com.sim.server.modules.group.entity.GroupMemberRel;
import com.sim.server.modules.group.service.GroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xiaoshun.cxs
 * 12/21/2020
 **/
@Service
@Slf4j
public class GroupServiceImpl extends AbstractServiceImpl<Group, Long> implements GroupService {

    @Autowired
    private GroupMapper groupMapper;
    @Autowired
    private GroupMemberRelMapper groupMemberRelMapper;

    @Override
    protected BaseMapper<Group> getMapper() {
        return groupMapper;
    }

    @Override
    public List<Group> list() {
        return groupMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public Long createGroup(String name, Long creatorId) {
        //create new group
        Group group = new Group().setMemberCount(1).setCreatorUid(creatorId);
        groupMapper.insert(group);

        if (!join(group.getId(), creatorId)) {
            log.error("user:[{}] joined group:[{}] error", creatorId, group.getId());
        }
        return group.getId();
    }

    @Override
    public boolean join(Long groupId, Long uid) {
        //creator joined the group
        GroupMemberRel groupMemberRel = new GroupMemberRel().setGroupId(groupId).setUid(uid);
        groupMemberRelMapper.insert(groupMemberRel);
        return true;
    }

    @Override
    public boolean join(String name, Long uid) {
        Group group = getByName(name);
        return join(group.getId(), uid);
    }

    @Override
    public boolean quit(String name, Long uid) {
        Group group = getByName(name);
        //delete current user's group relation
        groupMemberRelMapper.delete(new QueryWrapper<GroupMemberRel>().eq("group_id", group.getId()).eq("uid", uid));
        //update group's member number
        group.setMemberCount(group.getMemberCount() - 1);
        groupMapper.updateById(group);
        return true;
    }

    @Override
    public List<Group> getJoinedGroup(Long uid) {
        List<GroupMemberRel> groupMemberRelList = groupMemberRelMapper.selectList(
                new QueryWrapper<GroupMemberRel>().eq("uid", uid)
        );
        List<Long> groupIdList = groupMemberRelList.stream().map(GroupMemberRel::getGroupId).collect(Collectors.toList());
        return groupMapper.selectList(new QueryWrapper<Group>().in("group_id", groupIdList));
    }

    @Override
    public Group getByName(String name) {
        return getMapper().selectOne(new QueryWrapper<Group>().eq("name", name));
    }

    @Override
    public List<GroupMemberRel> memberList(String name) {
        Group group = getByName(name);
        return groupMemberRelMapper.selectList(new QueryWrapper<GroupMemberRel>().eq("group_id", group.getId()));
    }
}
