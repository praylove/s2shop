package com.x_s.s2shop.common.config.security;

import com.x_s.s2shop.common.entity.ResponseEntity;
import com.x_s.s2shop.common.utils.HttpUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableResourceServer
public class ResourceConfig extends ResourceServerConfigurerAdapter {
    
    public static final String DEMO_RESOURCE_ID = "order";
    
//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//        resources.resourceId(DEMO_RESOURCE_ID).stateless(true);
//    }
    
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(permissionArray()).permitAll()
                .requestMatchers((RequestMatcher) request -> request.getParameter("access_token") != null)
                .authenticated()
                .and()
                .logout()
                .disable()
                .cors().disable();
        System.out.println("ResourceConfig");
    }
    
    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return (request, response, authentication) -> {
            Object principal = authentication.getPrincipal();
            ResponseEntity entity = ResponseEntity.ok(principal);
            HttpUtils.responseWithJson(response, entity, true);
        };
    }
    
    private String[] permissionArray(){
        return new String[]{
                "/file/**",
                "/login/**",
                "/github/**",
                "/verifyCode",
        };
    }
}
