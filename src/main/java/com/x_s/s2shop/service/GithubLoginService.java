package com.x_s.s2shop.service;

import com.alibaba.fastjson.JSONObject;
import com.x_s.s2shop.common.constant.CodeConstant;
import com.x_s.s2shop.common.constant.ContextConstant;
import com.x_s.s2shop.common.entity.GithubConfigInfo;
import com.x_s.s2shop.common.utils.HttpUtils;
import com.x_s.s2shop.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class GithubLoginService {
    
    @Autowired
    private GithubConfigInfo configInfo;
    
    @Autowired
    private SysUserService userService;
    
    @Autowired
    private LoginService loginService;
    
    public GithubConfigInfo getConfigInfo(){
        return configInfo;
    }
    
    public JSONObject OAuth2Login(String code, String state){
        String token = getToken(code, state);
        System.out.println(token);
        Map<String, Object> userInfo = getGithubUserInfo(token);
        String loginId = (String) userInfo.get("login");
        String name = (String) userInfo.get("name");
        String avatar = (String) userInfo.get("avatar_url");
        if (!userService.exist(loginId)){
            userService.save(name, loginId, avatar);
        }
        return loginService.login(loginId, ContextConstant.DEFUALT_PASSWORD);
    }
    
    public String getToken(String code, String state){
        String url = "https://github.com/login/oauth/access_token";
        Map<String, String> params = new HashMap<String, String>(){{
            put("client_id", configInfo.getClient_id());
            put("client_secret", configInfo.getClient_secret());
            put("code", code);
//            put("redirect_uri", "http://localhost:9020/oauth/github");
            put("state", state);
        }};
        return HttpUtils.post(url, params);
    }
    
    public Map<String, Object> getGithubUserInfo(String token){
        String url = "https://api.github.com/user?" + token;
        Map<String, String> res = new HashMap<>();
        String response = HttpUtils.get(url, new HashMap<>());
        System.out.println(response);
        JSONObject object = JSONObject.parseObject(response);
        return object;
    }
    
}
