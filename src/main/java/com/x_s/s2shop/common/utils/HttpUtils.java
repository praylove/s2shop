package com.x_s.s2shop.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.x_s.s2shop.common.config.security.CustomUser;
import com.x_s.s2shop.domain.SysUser;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.parser.Entity;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class HttpUtils {
    
    public static void responseWithJson(HttpServletResponse response, Object param) throws IOException {
        responseWithJson(response, param, false);
    }
    
    public static void responseWithJson(HttpServletResponse response, Object param, boolean isCrossOrigin) throws IOException {
        if (isCrossOrigin) {
            allowCrossOrigin(response);
        }
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(JSONObject.toJSONString(param));
        writer.flush();
    }
    
    public static void allowCrossOrigin(HttpServletResponse res) {
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
    
    public static String post(String url, MultiValueMap<String, String> params) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        // 以表单的方式提交
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //将请求头部和参数合成一个请求
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
        //执行HTTP请求，将返回的结构使用ResultVO类格式化
        return restTemplate.postForObject(url, requestEntity, String.class);
    }
    
    public static String post(String url, Map<String, String> params) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        params.forEach(map::add);
        return post(url, map);
    }
    
    public static String get(String url, Map<String, String> params) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(getUrlParam(url, params), String.class);
    }
    
    public static String getUrlParam(String url, Map<String, ? super Object> params) {
        StringBuilder sb = new StringBuilder();
        params.forEach((k, v) -> {
            sb.append("&").append(k).append("=").append(v);
        });
        sb.replace(0, 1, "?");
        return url + sb.toString();
    }
    
    public static String getUrlParam(String url, Object object) {
        return getUrlParam(url, BeanUtilsCustom.fields(object));
    }
    
}
