package com.x_s.s2shop.common.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableAuthorizationServer
public class AuthConfig extends AuthorizationServerConfigurerAdapter {
    
    @Autowired
    private DataSource dataSource;
    
    /**
     * 注入authenticationManager
     * 来支持 password grant type
     */
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()")
                .allowFormAuthenticationForClients();
    }
    
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(jdbcClientDetailsService());
    }
    
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(new InMemoryTokenStore())
            .authenticationManager(authenticationManager);
    }
    
    @Bean
    public JdbcClientDetailsService jdbcClientDetailsService(){
        JdbcClientDetailsService service = new JdbcClientDetailsService(dataSource);
        service.setPasswordEncoder(new BCryptPasswordEncoder());
//        service.addClientDetails(clientDetails());
        return service;
    }
    
    @Bean
    public ClientDetails clientDetails(){
        BaseClientDetails clientDetails = new BaseClientDetails();
        clientDetails.setClientId("s2shop");
        clientDetails.setResourceIds(Collections.singletonList("order"));
        clientDetails.setClientSecret("123456");
        clientDetails.setScope(Collections.singletonList("admin"));
        clientDetails.setAuthorizedGrantTypes(Arrays.asList("password","refresh_token"));
        return clientDetails;
    }
}
