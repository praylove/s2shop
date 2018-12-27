package com.x_s.s2shop;

import com.x_s.s2shop.repository.BaseRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaRepositories(basePackageClasses = BaseRepository.class)
@EnableTransactionManagement
@SpringBootApplication
public class S2shopApplication {

	public static void main(String[] args) {
		SpringApplication.run(S2shopApplication.class, args);
	}
}
