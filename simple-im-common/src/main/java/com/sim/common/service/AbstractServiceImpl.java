package com.sim.common.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ReflectionUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author xiaoshun.cxs
 * 2020/12/9
 **/
@Slf4j
public abstract class AbstractServiceImpl<T, IdType extends Serializable> implements BaseService<T, IdType> {

    /**
     * get mapper for the current entity
     * @return mapper
     */
    protected abstract BaseMapper<T> getMapper();

    @Override
    public T detail(IdType id) {
        return getMapper().selectById(id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> listByIds(List<IdType> idList) {
        List<T> list = new ArrayList<>();
        List<T> tmpList = getMapper().selectBatchIds(idList);

        //restore the list sequence
        Map<IdType, T> map = tmpList
                .stream()
                .collect(
                        Collectors.toMap(t -> {
                            try {
                                return (IdType)Objects.requireNonNull(
                                        ReflectionUtils.findMethod(t.getClass(), "getId")
                                ).invoke(t);
                            } catch (IllegalAccessException|InvocationTargetException e) {
                                log.error(e.getMessage(), e);
                            }
                            return null;
                        },
                        Function.identity())
                );

        for (IdType id:idList) {
            list.add(map.get(id));
        }
        return list;
    }

    @Override
    public boolean insert(T t) {
        return getMapper().insert(t) > 0;
    }

    @Override
    public boolean update(T t) {
        return getMapper().updateById(t) > 0;
    }

    @Override
    public boolean delete(IdType id) {
        return getMapper().deleteById(id) > 0;
    }
}
