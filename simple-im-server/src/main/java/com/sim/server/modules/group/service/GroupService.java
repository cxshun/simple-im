package com.sim.server.modules.group.service;

import com.sim.server.BaseService;
import com.sim.server.modules.group.entity.Group;

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

}
