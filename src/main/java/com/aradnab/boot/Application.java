package com.aradnab.boot;

import com.aradnab.boot.config.ResourceUrl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class Application implements WebMvcConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        // Register resource handler for profile images
        registry.addResourceHandler(ResourceUrl.VIRTUAL_PROFILE_DIR + "/**").addResourceLocations(ResourceUrl.REAL_PROFILE_DIR + "/")
                .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());
        // Register resource handler for product images
        registry.addResourceHandler(ResourceUrl.VIRTUAL_PRODUCT_DIR + "/**").addResourceLocations(ResourceUrl.REAL_PRODUCT_DIR + "/")
                .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());
    }
}
