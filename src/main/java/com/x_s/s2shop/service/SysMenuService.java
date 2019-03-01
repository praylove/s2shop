package com.x_s.s2shop.service;

import com.x_s.s2shop.domain.SysMenu;
import com.x_s.s2shop.vo.SysMenuVo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SysMenuService extends BaseService<SysMenu>{

    /**
     * 查询所有或符合条件的结果
     *
     * @param param
     * 查询条件
     * @return
     */
    Page<SysMenu> list(SysMenuVo param);

    List<SysMenu> getTree(String... type);

    List<SysMenu> getUserTree(String... type);

    List<SysMenu> listAll(String... type);
}
