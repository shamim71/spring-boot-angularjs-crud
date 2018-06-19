package com.acme.product;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Adding CORS configuration.
 * 
 * @author sahmmed
 *
 */
@Configuration
public class WebMvcConfig {


	/**
	 * Create CORS configuration
	 * 
	 * @return the generated WebMvcConfigurer instance
	 */
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/**").allowedMethods("GET", "POST","PUT", "DELETE");
			}
		};
	}
}
