package com.x_s.s2shop.common.config;

import com.x_s.s2shop.common.constant.HttpConstant;
import com.x_s.s2shop.common.entity.ResponseEntity;
import com.x_s.s2shop.common.exception.ResponsiveException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {


    @ControllerAdvice
    public static class ExceptionGlobalHandler {

        private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionGlobalHandler.class);


        @ResponseBody
        @ExceptionHandler(value = Exception.class)
        public ResponseEntity defaultErrorHandler(HttpServletResponse response, Exception exception) throws
                Exception {
//            response.setStatus(HttpConstant.ERROR_CODE);
            ResponseEntity responseEntity = ResponseEntity.error();
            if (exception instanceof TimeoutException) {
                responseEntity.setMessage("请求超时");
            } else if (exception instanceof MultipartException) {
//                response.setStatus(HttpConstant.FILE_ERROR_CODE);
                responseEntity.setCode(HttpConstant.FILE_ERROR_CODE);
//                responseEntity.setMessage("文件大小不能超过1MB");
            } else if (exception instanceof ResponsiveException){
                responseEntity.setMessage(exception.getMessage());
            } else {
                LOGGER.error("# 异常状态码：" + responseEntity.getCode(), exception);
            }

            return responseEntity;
        }

        @ResponseBody
        @ExceptionHandler(value = NoHandlerFoundException.class)
        public Map<String, Object> noHandlerFoundException(Exception exception) {
            Map<String, Object> map = new HashMap<>();
            map.put("message", "无效请求");
            map.put("success", "false");
            return map;
        }
    }

//    @Override
//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
//        // 注册Spring data jpa pageable的参数分解器
//        argumentResolvers.add(new PageableHandlerMethodArgumentResolver());
//    }


    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/file/**").addResourceLocations("classpath:/files/");
        super.addResourceHandlers(registry);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080")
                .allowedMethods("*")
                .allowCredentials(true).maxAge(3600);
    }

}
