package com.x_s.s2shop.controller;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.x_s.s2shop.common.constant.ContextConstant;
import com.x_s.s2shop.common.entity.ResponseEntity;
import com.x_s.s2shop.common.exception.ParamException;
import com.x_s.s2shop.common.utils.HttpUtils;
import com.x_s.s2shop.common.utils.VerifyCodeUtils;
import com.x_s.s2shop.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;
import java.util.Collections;

@Controller
public class LoginController {
    
    @Autowired
    private LoginService loginService;
    
//    @GetMapping("/login")
//    @ResponseBody
//    public String loginPage() {
//        return "login";
//    }
    
    
    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity login(String username, String password, String verifyCode, HttpSession session) {

//        if (!verify(verifyCode, session)){
//            throw new ParamException("验证码错误！");
//        }
        
        return ResponseEntity.ok(loginService.login(username, password));
    }
    
    
    @PostMapping("/logout")
    @ResponseBody
    public ResponseEntity logout(Principal principal) {
        return ResponseEntity.ok(loginService.logout(principal));
    }
    
    @ResponseBody
    @GetMapping("/userInfo")
    public ResponseEntity userInfo() {
        return ResponseEntity.ok(HttpUtils.getCurrentUser());
    }
    
    @GetMapping("/verifyCode")
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) {
        //设置响应头信息
        response.setContentType("image/jpeg");
        //指定页面不缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "No-cache");
        response.setDateHeader("Expires", 0);
        //获取输出流
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            String code = VerifyCodeUtils.code();
            //将验证码保存到session
            request.getSession().setAttribute(ContextConstant.SESSION_VERIFY_CODE, code);
            //输出图像
            BufferedImage image = VerifyCodeUtils.image(code);
            ImageIO.write(image, "JPEG", os);
        } catch (IOException e) {
            throw new ParamException(e.getMessage());
        }
    }
    
    
    private boolean verify(String verifyCode, HttpSession session) {
        Object code = session.getAttribute(ContextConstant.SESSION_VERIFY_CODE);
        return StringUtils.equalsIgnoreCase(String.valueOf(code), verifyCode);
    }
}
