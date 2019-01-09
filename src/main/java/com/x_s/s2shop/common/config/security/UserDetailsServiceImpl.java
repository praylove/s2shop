package com.x_s.s2shop.common.config.security;

import com.x_s.s2shop.domain.SysUser;
import com.x_s.s2shop.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = userService.findByLoginId(username).orElseThrow(()-> new UsernameNotFoundException("当前用户不存在"));
        return new CustomUser(user);
    }

}
