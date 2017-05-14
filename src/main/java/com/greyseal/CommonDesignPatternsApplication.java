package com.greyseal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = { "com.greyseal" })
@EnableJpaRepositories(basePackages = {"com.greyseal"})
@ComponentScan({ "com.greyseal" })
public class CommonDesignPatternsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommonDesignPatternsApplication.class, args);
	}
	
	@Bean
	public HealthIndicator helloHealthIndicator() {
		return new HealthIndicator() {
			@Override
			public Health health() {
				return Health.up().withDetail("I'm", "up").build();
			}
		};
	}
}
