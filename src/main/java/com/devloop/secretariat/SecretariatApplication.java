package com.devloop.secretariat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
		com.devloop.secretariat.config.AppGeminiProperties.class,
		com.devloop.secretariat.config.AppEvolutionProperties.class
})
public class SecretariatApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecretariatApplication.class, args);
	}
}
