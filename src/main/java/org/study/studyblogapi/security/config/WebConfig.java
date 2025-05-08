package org.study.studyblogapi.security.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Engedélyezze a kéréseket az összes /api végpontra
                .allowedOrigins("http://localhost:5173") // A frontend URL-je
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Mely HTTP metódusokat engedélyezünk
                .allowedHeaders("*") // Engedélyezett fejléc
                .allowCredentials(true); // Engedélyezze a hitelesítést (cookie-k, stb.)
    }
}