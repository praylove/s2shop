package com.x_s.s2shop.common.entity;

import com.x_s.s2shop.common.utils.Randoms;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GithubConfigInfo {
    
    private String client_id;
    
    private String client_secret;
    
    private String redirect_uri;
    
    private String state;
    
    public String getState(){
        return Randoms.randomNumber(6) + "";
    }
    
}
