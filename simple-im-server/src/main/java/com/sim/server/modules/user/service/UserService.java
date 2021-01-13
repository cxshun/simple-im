package com.sim.server.modules.user.service;

import com.sim.server.BaseService;
import com.sim.server.modules.user.entity.User;

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

    /**
     * get userInfo based on the sessionId
     * @param sessionId specify sessionId
     * @return userInfo
     */
    User getBySessionId(String sessionId);

}
