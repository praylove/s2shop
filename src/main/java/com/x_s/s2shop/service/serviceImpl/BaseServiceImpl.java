package com.x_s.s2shop.service.serviceImpl;

import com.x_s.s2shop.common.constant.CodeConstant;
import com.x_s.s2shop.common.exception.ParamException;
import com.x_s.s2shop.common.exception.ServiceException;
import com.x_s.s2shop.common.utils.BeanUtilsCustom;
import com.x_s.s2shop.common.utils.DateUtils;
import com.x_s.s2shop.common.utils.HttpUtils;
import com.x_s.s2shop.common.utils.Randoms;
import com.x_s.s2shop.domain.SysUser;
import com.x_s.s2shop.repository.BaseRepository;
import com.x_s.s2shop.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Optional;


public abstract class BaseServiceImpl<T> implements BaseService<T> {

    @Autowired
    private BaseRepository<T, String> baseRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<T> findById(String id) {
        return baseRepository.findById(id);
    }

    @Override
    @Transactional
    public void save(T entity) {
        setCreateParam(entity);
        try {
            baseRepository.save(entity);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    @Transactional
    public void updateAll(T entity) {
        setUpdateParam(entity);
        try {
            baseRepository.save(entity);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateSelective(T entity) {
        updateSelective(entity, "id");
    }

    @Override
    @Transactional
    public void updateSelective(T entity, String keyName) {
        String id = getId(entity, keyName);
        if (id == null){
            throw new ParamException(entity.getClass() + "id 不能为空！");
        }
        T item = baseRepository.findById(id).orElseThrow(() -> new ParamException("该ID不存在"));
        BeanUtilsCustom.copyWithNonNull(entity, item);
        setUpdateParam(item);
        baseRepository.saveAndFlush(item);
    }

    @Override
    @Transactional
    public void delete(String ids) {
        if (!StringUtils.hasText(ids)){
            throw new ParamException("删除项为空！");
        }

        try {
            baseRepository.deleteInBatchByIds(ids);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    /**
     * 获取对象的 ID
     */
    private String getId(T entity, String keyName) {
        return (String) BeanUtilsCustom.getProperty(entity, keyName);
    }

    /**
     * 设置更新时间
     */
    protected void setUpdateParam(Object object) {
        try {
            BeanUtilsCustom.setProperty(object, "updateTime", DateUtils.getCurrentTime());
        } catch (ServiceException ignored) {

        }
    }

    protected void setCreateParam(Object object) {
        setCreateParam(object, "id");
    }

    protected void setCreateParam(Object object, String key) {
        try {
            BeanUtilsCustom.setProperty(object, key, Randoms.uuid());
            BeanUtilsCustom.setProperty(object, "createTime", DateUtils.getCurrentTime());
            BeanUtilsCustom.setProperty(object, "status", CodeConstant.NORMAL_STATUS);
            SysUser current = HttpUtils.getCurrentUser();
            BeanUtilsCustom.setProperty(object, "creator", current == null ? null : current.getId());
        } catch (ServiceException ignored) {

        }
    }

}
