package com.x_s.s2shop.service;

import com.x_s.s2shop.domain.SysRole;
import com.x_s.s2shop.domain.SysUser;
import com.x_s.s2shop.vo.SysUserVo;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface SysUserService  extends BaseService<SysUser>{

    Optional<SysUser> findByName(String username);

    Optional<SysUser> findByLoginId(String loginId);

    Page<SysUser> list(SysUserVo userVo);

    Set<SysRole> getRole(String id);

    void resetPassword(String id);

    void addRole(String uid, String... roleIds);

    void removeRole(String uid, String... roleIds);
}
