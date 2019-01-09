package com.x_s.s2shop.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.x_s.s2shop.common.config.security.CustomUser;
import com.x_s.s2shop.domain.SysUser;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HttpUtils {

    public static void responseWithJson(HttpServletResponse response, Object param) throws IOException {
        responseWithJson(response, param, false);
    }

    public static void responseWithJson(HttpServletResponse response, Object param, boolean isCrossOrigin) throws IOException {
        if (isCrossOrigin){
            allowCrossOrigin(response);
        }
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(JSONObject.toJSONString(param));
        writer.flush();
    }

    public static void allowCrossOrigin(HttpServletResponse res){
        // Website you wish to allow to connect
        res.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");
        // Request methods you wish to allow
        res.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, PATCH, DELETE");
        // Request headers you wish to allow
        res.setHeader("Access-Control-Allow-Headers", "X-Requested-With,content-type");
        // Set to true if you need the website to include cookies in the requests sent
        // to the API (e.g. in case you use sessions)
        res.setHeader("Access-Control-Allow-Credentials", "true");
    }

    public static SysUser getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof CustomUser) {
            return ((CustomUser) principal).getUser();
        }
        return null;
    }

}
