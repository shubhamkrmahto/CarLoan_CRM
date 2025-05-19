package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class ClmsCrmApiModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClmsCrmApiModuleApplication.class, args);
	}
	
	@Bean
	public RestTemplate rt() {
		RestTemplate rs = new RestTemplate();
		return rs;
	}

}
