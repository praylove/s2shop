package com.x_s.s2shop.common.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
public class PasswordClientDetails {
    
    private String password;
    
    private String username;
    
    private String grant_type;
    
    private String client_id;
    
    private String client_secret;
    
    public PasswordClientDetails(String password, String username) {
        this.password = password;
        this.username = username;
        this.grant_type = "password";
        this.client_id = "s2shop";
        this.client_secret = "123456";
    }
}
