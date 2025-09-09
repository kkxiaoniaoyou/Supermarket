package com.cbz.example.config;

/**
 * @author 11272
 * @description: TODO
 * @date 2024/1/10 15:55
 **/

import com.cbz.example.interceptor.JwtTokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private JwtTokenInterceptor jwtTokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        List<String> excludedPaths = Arrays.asList("/user/login", "/user/register", "/captcha", "/captchaNbr");

        // 在拦截器配置中使用这个集合
        registry.addInterceptor(jwtTokenInterceptor)
                // 拦截所有路径，但排除登录、注册和验证码接口
                .addPathPatterns("/**")
                .excludePathPatterns(excludedPaths);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*") // 允许所有域名
                .allowCredentials(true) // 允许携带凭据
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("Origin", "Content-Type", "Accept", "Authorization");
    }
}

