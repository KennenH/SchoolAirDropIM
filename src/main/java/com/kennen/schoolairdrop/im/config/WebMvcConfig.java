package com.kennen.schoolairdrop.im.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author kennen
 * @date 2020/12/28 21:31
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /**
         * 资源映射路径
         * addResourceHandler：访问映射路径·
         * addResourceLocations：资源绝对路径
         */
        registry.addResourceHandler("/assets/goods/img/**").addResourceLocations("file:D:/assets/goods/img/");
        registry.addResourceHandler("/assets/user/avatars/**").addResourceLocations("file:D:/assets/user/avatars/");
    }
}