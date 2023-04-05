package io.wisoft.leedongyeop.jwtpractice.config;

import io.wisoft.leedongyeop.jwtpractice.interceptor.BearerAuthInterceptor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private final BearerAuthInterceptor bearerAuthInterceptor;
    private final Logger logger = LoggerFactory.getLogger(WebMvcConfig.class);

    public void addInterceptors(final InterceptorRegistry registry) {
        logger.info(">>> 인터셉터 등록");
        registry.addInterceptor(bearerAuthInterceptor).addPathPatterns("/info");
    }
}
