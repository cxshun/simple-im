package com.sim.server.modules.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sim.server.AbstractServiceImpl;
import com.sim.server.modules.user.dao.UserMapper;
import com.sim.server.modules.user.entity.User;
import com.sim.server.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xiaoshun.cxs
 * 2020/12/9
 **/
@Service
public class UserServiceImpl extends AbstractServiceImpl<User, Long> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    protected BaseMapper<User> getMapper() {
        return userMapper;
    }

    @Override
    public User getByLoginId(String loginId) {
        return getMapper().selectOne(new QueryWrapper<User>().eq("login_id", loginId));
    }

    @Override
    public User getBySessionId(String sessionId) {
        return getMapper().selectOne(new QueryWrapper<User>().eq("session_id", sessionId));
    }
}
