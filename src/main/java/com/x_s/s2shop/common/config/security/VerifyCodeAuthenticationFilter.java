package com.x_s.s2shop.common.config.security;

import com.alibaba.druid.util.StringUtils;
import com.x_s.s2shop.common.constant.ContextConstant;
import com.x_s.s2shop.common.entity.ResponseEntity;
import com.x_s.s2shop.common.utils.HttpUtils;
import com.x_s.s2shop.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;

public class VerifyCodeAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private String verifyCodeName = "verifyCode";

    public VerifyCodeAuthenticationFilter() {
        setAuthenticationSuccessHandler((request, response, authResult) -> {
            System.out.println("登录成功！");
    
            Object principal = authResult.getPrincipal();
            ResponseEntity entity = ResponseEntity.ok(principal);
            HttpUtils.responseWithJson(response, entity, true);
        });
        setAuthenticationFailureHandler((request, response, failed) -> {
            String message = SecurityUtils.authMessage(failed);
            ResponseEntity entity = ResponseEntity.error(message);
            HttpUtils.responseWithJson(response, entity, true);
        });
    }
    
    
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        String verifyCode = request.getParameter(verifyCodeName);
//        if (!verify(request, verifyCode))
//            throw new LoginException("验证码错误！");
        return super.attemptAuthentication(request, response);
    }
    
    
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        super.doFilter(req, res, chain);
    }
    
    private boolean verify(HttpServletRequest request, String code) {
        HttpSession session = request.getSession();
        Object verifyCode = session.getAttribute(ContextConstant.SESSION_VERIFY_CODE);
        System.out.println("verifyCode: " + verifyCode);
        System.out.println("code: " + code);
        return StringUtils.equalsIgnoreCase(String.valueOf(verifyCode), code);
    }
    
}
