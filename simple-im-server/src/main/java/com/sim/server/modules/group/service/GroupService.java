package com.sim.server.modules.group.service;

import com.sim.common.service.BaseService;
import com.sim.server.modules.group.entity.Group;
import com.sim.server.modules.group.entity.GroupMemberRel;

import java.util.List;

/**
 * @author xiaoshun.cxs
 * 12/21/2020
 **/
public interface GroupService extends BaseService<Group, Long> {

    /**
     * list all group
     * @return groupList
     */
    List<Group> list();

    /**
     * create a new Group
     * @param name          groupName
     * @param creatorId     creator of the group
     * @return groupId
     */
    Long createGroup(String name, Long creatorId);

    /**
     * user join specify group
     * @param groupId specify groupId
     * @param uid     specify uid
     * @return true-success, false-fail
     */
    boolean join(Long groupId, Long uid);

    /**
     * join group by specify name
     * @param name groupName
     * @param uid  uid
     * @return true-success, false-fail
     */
    boolean join(String name, Long uid);

    /**
     * quit the group that specify by the name
     * @param name  groupName
     * @param uid   uid
     * @return true-success, false-fail
     */
    boolean quit(String name, Long uid);

    /**
     * get user's joined group list
     * @param uid specify uid
     * @return user' joined group list
     */
    List<Group> getJoinedGroup(Long uid);

    /**
     * get groupInfo by name
     * @param name group name
     * @return groupInfo
     */
    Group getByName(String name);

    /**
     * get group's memberList
     * @param name  groupName
     * @return memberList
     */
    List<GroupMemberRel> memberList(String name);

}
