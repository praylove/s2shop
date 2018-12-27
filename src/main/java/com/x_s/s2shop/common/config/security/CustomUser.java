package com.x_s.s2shop.common.config.security;

import com.x_s.s2shop.domain.SysMenu;
import com.x_s.s2shop.domain.SysRole;
import com.x_s.s2shop.domain.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomUser extends User {

    private SysUser user;

    public CustomUser(SysUser user) {
        super(user.getLoginId(), user.getPassword(), true, user.isAccountNonExpired(),
                user.isCredentialsNonExpired(), user.isAccountNonLocked(), getAuthorities(user));
        this.user = user;
    }

    public SysUser getUser(){
        return this.user;
    }

    private static List<SimpleGrantedAuthority> getAuthorities(SysUser user){
//        Set<SysMenu> means = new HashSet<>();
//        for (SysRole role: user.getRoles()){
//            means.addAll(role.getMenus());
//        }
//        return  means.stream().map(m -> new SimpleGrantedAuthority(m.getPath())).collect(Collectors.toList());
        return user.getRoles().stream().map(r -> new SimpleGrantedAuthority(r.getRoleName())).collect(Collectors.toList());
    }
}
