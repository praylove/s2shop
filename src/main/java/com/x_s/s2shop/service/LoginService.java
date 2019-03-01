package com.x_s.s2shop.service;

import com.alibaba.fastjson.JSONObject;
import com.x_s.s2shop.common.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.security.Principal;
import java.util.Collections;

@Service
public class LoginService {
    
    @Value("${server.port}")
    private String port;
    
    @Autowired
    private AuthorizationServerTokenServices authorizationServerTokenServices;
    
    @Autowired
    private ConsumerTokenServices consumerTokenServices;
    
    public JSONObject login(String username, String password){
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add(OAuth2Utils.GRANT_TYPE, "password");
        map.add(OAuth2Utils.CLIENT_ID, "s2shop");
        map.add("client_secret", "123456");
        map.add("username", username);
        map.add("password", password);
        String s = HttpUtils.post("http://localhost:9020/oauth/token", map);
        return JSONObject.parseObject(s);
    }
    
    public boolean logout(Principal principal){
        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) principal;
        OAuth2AccessToken accessToken = authorizationServerTokenServices.getAccessToken(oAuth2Authentication);
        consumerTokenServices.revokeToken(accessToken.getValue());
        return  true;
    }
    
}
