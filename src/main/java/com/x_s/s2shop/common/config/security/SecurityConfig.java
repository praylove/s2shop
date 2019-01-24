package com.x_s.s2shop.common.config.security;

import com.x_s.s2shop.common.entity.ResponseEntity;
import com.x_s.s2shop.common.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterAt(virifyCodeAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        http.authorizeRequests()
                .antMatchers("/", "index.html","/oauth/**")
                .permitAll()
                .anyRequest().permitAll()
                .and()
                .sessionManagement()
//                    .maximumSessions(1)   // 设置同一账号可同时在线数量，默认任意数量
                .and()
                .formLogin()
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
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
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
    
    @Bean
    public VerifyCodeAuthenticationFilter virifyCodeAuthenticationFilter() throws Exception {
        VerifyCodeAuthenticationFilter filter = new VerifyCodeAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }
    
    
    public LogoutSuccessHandler logoutSuccessHandler() {
        return (request, response, authentication) -> {
            Object principal = authentication.getPrincipal();
            ResponseEntity entity = ResponseEntity.ok(principal);
            HttpUtils.responseWithJson(response, entity, true);
        };
    }
    
    /**
     * 需要配置这个支持password模式
     * support password grant type
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
}
