package com.x_s.s2shop.common.config.security;

import com.x_s.s2shop.common.constant.ContextConstant;
import com.x_s.s2shop.common.exception.LoginException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class VerifyCodeAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private String verifyCodeName = "verifyCode";

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        String verifyCode = request.getParameter(verifyCodeName);
//        if (!verify(request, verifyCode))
//            throw new LoginException("验证码错误！");

        return super.attemptAuthentication(request, response);
    }

    private boolean verify(HttpServletRequest request, String code) {
        HttpSession session = request.getSession();
        Object verifyCode = session.getAttribute(ContextConstant.SESSION_VERIFY_CODE);
        System.out.println("verifyCode: " + verifyCode);
        System.out.println("code: " + code);
        return StringUtils.equalsIgnoreCase(String.valueOf(verifyCode), code);
    }

}
