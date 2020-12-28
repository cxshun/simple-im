package com.sim.server.modules.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sim.server.modules.user.dao.UserMapper;
import com.sim.server.modules.user.entity.User;
import com.sim.server.modules.user.service.UserService;
import com.sim.server.AbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public User detail(Long id) {
        return getMapper().selectById(id);
    }

    @Override
    public List<User> listByIds(List<Long> idList) {
        return getMapper().selectBatchIds(idList);
    }

    @Override
    public boolean insert(User user) {
        return getMapper().insert(user) > 0;
    }

    @Override
    public boolean update(User user) {
        return getMapper().updateById(user) > 0;
    }

    @Override
    public boolean delete(Long id) {
        return getMapper().deleteById(id) > 0;
    }

    @Override
    public User getByLoginId(String loginId) {
        return getMapper().selectOne(new QueryWrapper<User>().eq("login_id", loginId));
    }
}
