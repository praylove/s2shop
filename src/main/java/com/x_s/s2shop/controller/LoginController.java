package com.x_s.s2shop.controller;

import com.x_s.s2shop.common.constant.ContextConstant;
import com.x_s.s2shop.common.entity.ResponseEntity;
import com.x_s.s2shop.common.exception.ParamException;
import com.x_s.s2shop.common.utils.HttpUtils;
import com.x_s.s2shop.common.utils.VerifyCodeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

@Controller
public class LoginController {

    @GetMapping("/login.html")
    public String loginPage() {
        return "login";
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

}
