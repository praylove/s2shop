package com.x_s.s2shop.controller;

import com.x_s.s2shop.common.entity.ResponseEntity;
import com.x_s.s2shop.common.exception.ParamException;
import com.x_s.s2shop.common.utils.HttpUtils;
import com.x_s.s2shop.service.GithubLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/github")
public class GithubLoginController {
    
    @Autowired
    private GithubLoginService service;
    
    private final String GITHUB_OAUTH_URL = "https://github.com/login/oauth/authorize";
    
    @GetMapping("/url")
    public ResponseEntity oauth(Principal principal){
        System.out.println(principal);
        return ResponseEntity.ok(HttpUtils.getUrlParam(GITHUB_OAUTH_URL, service.getConfigInfo()));
    }
    
    @GetMapping("/login")
    public ResponseEntity oatuhLogin(String code, String state){
        if (!StringUtils.hasText(code) || !StringUtils.hasText(state)){
            throw new ParamException("参数错误！");
        }
        return ResponseEntity.ok(service.OAuth2Login(code, state));
    }
    
}
