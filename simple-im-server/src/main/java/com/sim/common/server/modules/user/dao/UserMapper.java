package com.sim.common.server.modules.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sim.common.server.modules.user.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author xiaoshun.cxs
 * 2020/12/9
 **/
@Repository
public interface UserMapper extends BaseMapper<User> {
}
