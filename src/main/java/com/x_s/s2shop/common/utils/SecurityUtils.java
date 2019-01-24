package com.x_s.s2shop.common.utils;

import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class SecurityUtils {
    
    public static String authMessage(AuthenticationException e) {
        String message = null;
        if (e instanceof UsernameNotFoundException) {    // 用户不存在
            message = "用户不存在";
        } else if (e instanceof DisabledException) {     // 用户已被禁用
            message = "用户已被禁用";
        } else if (e instanceof BadCredentialsException) {   // 坏的凭据
            message = "账号或密码错误";
        } else if (e instanceof LockedException) {   // 账户锁定
            message = "账户锁定";
        } else if (e instanceof AccountExpiredException) {   // 账户过期
            message = "账户过期";
        } else if (e instanceof CredentialsExpiredException) {   // 证书过期
            message = "密码过期";
        } else {
            message = e.getMessage();
        }
    
        return message;
    }
    
}
