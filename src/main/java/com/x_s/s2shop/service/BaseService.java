package com.x_s.s2shop.service;

import com.x_s.s2shop.common.exception.ServiceException;
import com.x_s.s2shop.domain.SysUser;

import java.util.Optional;

public interface BaseService<T> {

    /**
     * 根据Id查询结果
     *
     * @param id
     * @return
     */
    Optional<T> findById(String id);

    /**
     * 新增结果
     *
     * @param entity
     */
    void save(T entity);

    /**
     * 根据Id修改结果
     *
     * @param entity
     */
    void updateAll(T entity);

    /**
     * 根据Id删除一个或多个结果
     *
     * @param ids
     *  结果id, 多个ID用 "<code>,</code>" 隔开
     */
    void delete(String ids);

    /**
     * 修改部分值， 忽略 <code>null</code> 字段
     *
     * @param entity
     * @param keyName
     *  主键字段名称
     * @return
     */
    void updateSelective(T entity, String keyName);

    /**
     * 修改部分值， 忽略 <code>null</code> 字段
     *
     * @param entity
     * @return
     */
    void updateSelective(T entity);

}
