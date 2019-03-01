package com.x_s.s2shop.common.config;

import com.x_s.s2shop.common.entity.FileTypeProperties;
import com.x_s.s2shop.common.entity.GithubConfigInfo;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertiesConfig {

    @Bean
    @ConfigurationProperties(prefix = "file-type")
    public FileTypeProperties fileType(){
        return new FileTypeProperties();
    }
    
    @Bean
    @ConfigurationProperties(prefix = "spring.security.oauth2.client.registration.github")
    public GithubConfigInfo githubConfigInfo(){
        return new GithubConfigInfo();
    }
}
