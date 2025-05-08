package org.study.studyblogapi.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MediaConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/media/user_icons/**").addResourceLocations("file:uploads/user_icons/");
        registry.addResourceHandler("/media/post_images/**").addResourceLocations("file:uploads/post_images");
    }
}
