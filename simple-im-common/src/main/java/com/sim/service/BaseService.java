package com.sim.service;

import java.io.Serializable;
import java.util.List;

/**
 * @author xiaoshun.cxs
 * 2020/12/9
 **/
public interface BaseService<T, IdType extends Serializable> {

    /**
     * get entity detail by specify id
     * @param id    specify id
     * @return  entity detail
     */
    T detail(IdType id);

    /**
     * get entity list by idList
     * @param idList    idList
     * @return entity list
     */
    List<T> listByIds(List<IdType> idList);

    /**
     * insert entity
     * @param t entity info
     * @return true-insert success, false-insert fail
     */
    boolean insert(T t);

    /**
     * update entity
     * @param t entity info
     * @return true-update success, false-update fail
     */
    boolean update(T t);

    /**
     * delete entity by id(logically delete, only set is_deleted flag)
     * @param id specify id
     * @return true-delete success, false-delete fail
     */
    boolean delete(IdType id);

}
