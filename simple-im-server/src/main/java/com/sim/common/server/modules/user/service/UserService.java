package com.sim.common.server.modules.user.service;

import com.sim.common.server.modules.user.entity.User;
import com.sim.common.server.BaseService;

/**
 * @author xiaoshun.cxs
 * 2020/12/9
 **/
public interface UserService extends BaseService<User, Long> {

    /**
     * get userinfo based on loginId
     * @param loginId loginId
     * @return userinfo
     */
    User getByLoginId(String loginId);

}
