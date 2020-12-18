package com.sim.common.server;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.io.Serializable;
import java.util.List;

/**
 * @author xiaoshun.cxs
 * 2020/12/9
 **/
public abstract class AbstractServiceImpl<T, IdType extends Serializable> implements BaseService<T, IdType>{

    /**
     * get mapper for the current entity
     * @return mapper
     */
    protected abstract BaseMapper<T> getMapper();

    @Override
    public T detail(IdType id) {
        return null;
    }

    @Override
    public List<T> listByIds(List<IdType> idList) {
        return null;
    }

    @Override
    public boolean insert(T t) {
        return false;
    }

    @Override
    public boolean update(T t) {
        return false;
    }

    @Override
    public boolean delete(IdType id) {
        return false;
    }
}
