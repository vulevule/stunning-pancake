package com.kontraktor.bitcoinservice.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public abstract class CorsConfiguration implements WebMvcConfigurer {
	 	@Override
	    public void addCorsMappings(CorsRegistry registry) {
	        registry.addMapping("/**").allowedOrigins("*").allowedMethods("PUT", "DELETE", "POST", "GET", "OPTIONS", "HEAD");
	        
	    }
}
