package com.x_s.s2shop.common.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.Authentication;

@Getter
@Setter
public class LoginToken {
    
    Authentication authentication;
    
    long lastModifyTime;
    
}
