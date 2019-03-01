package com.x_s.s2shop.service;

import com.x_s.s2shop.domain.SysRole;
import com.x_s.s2shop.vo.SysRoleVo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SysRoleService extends BaseService<SysRole> {
    
    Page<SysRole> list(SysRoleVo roleVo);

    List<SysRole> listWithoutPage(SysRoleVo roleVo);

    void save(String name);

    void update(String id, String name);

    void updateMeanu(String uid, String... meauIds);

    void removeMeau(String uid, String... meauIds);
}
