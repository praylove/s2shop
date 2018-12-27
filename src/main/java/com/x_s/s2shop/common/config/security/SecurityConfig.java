package com.x_s.s2shop.common.config.security;

import com.x_s.s2shop.common.entity.ResponseEntity;
import com.x_s.s2shop.common.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{


    private UserDetailsService userDetailsService;

    private PasswordEncoder passwordEncoder;

    @Autowired
    SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterAt(virifyCodeAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        http.authorizeRequests()
                    .antMatchers("/", "index.html")
                    .permitAll()
                    .anyRequest().permitAll()
                .and()
                    .sessionManagement()
//                    .maximumSessions(1)   // 设置同一账号可同时在线数量，默认任意数量
                .and()
                    .formLogin()
                    .loginPage("/login.html")
                    .loginProcessingUrl("/login")
                    .usernameParameter("username")
                    .passwordParameter("password")
//                    .defaultSuccessUrl("/swagger-ui.html")
//                    .failureHandler(authenticationFailureHandler())     // 设置登录失败的处理方式
                    .permitAll()
                .and()
                    .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessHandler(logoutSuccessHandler())
                .and()
                    .csrf().disable();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }

    @Bean
    public VerifyCodeAuthenticationFilter virifyCodeAuthenticationFilter() throws Exception {
        VerifyCodeAuthenticationFilter filter = new VerifyCodeAuthenticationFilter();
        filter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
        filter.setAuthenticationFailureHandler(authenticationFailureHandler());
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler(){
        return (request, response, exception) -> {
            String message = this.getLoginMessage(exception);
            ResponseEntity entity = ResponseEntity.error(message);
            HttpUtils.responseWithJson(response, entity, true);
        };
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler(){
        return (request, response, authentication) -> {
            Object principal = authentication.getPrincipal();
            ResponseEntity entity = ResponseEntity.ok(principal);
            HttpUtils.responseWithJson(response, entity, true);
        };
    }

    public LogoutSuccessHandler logoutSuccessHandler(){
        return (request, response, authentication) -> {
            Object principal = authentication.getPrincipal();
            ResponseEntity entity = ResponseEntity.ok(principal);
            HttpUtils.responseWithJson(response, entity, true);
        };
    }

    private String getLoginMessage(AuthenticationException e){
        String message = null;
        if (e instanceof UsernameNotFoundException){    // 用户不存在
            message = "用户不存在";
        } else if (e instanceof DisabledException){     // 用户已被禁用
            message = "用户已被禁用";
        } else if (e instanceof BadCredentialsException){   // 坏的凭据
            message = "账号或密码错误";
        } else if (e instanceof LockedException){   // 账户锁定
            message = "账户锁定";
        } else if (e instanceof AccountExpiredException){   // 账户过期
            message = "账户过期";
        } else if (e instanceof CredentialsExpiredException){   // 证书过期
            message = "密码过期";
        } else {
            message = e.getMessage();
        }

        return message;
    }

}
