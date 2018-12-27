package com.x_s.s2shop.repository;

import com.x_s.s2shop.domain.SysUser;

import java.util.Optional;

public interface SysUserRepository  extends BaseRepository<SysUser, String> {

    Optional<SysUser> findByName(String name);

    Optional<SysUser> findByLoginId(String loginId);
}
