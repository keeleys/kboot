package cc.sitec.kboot.config;

import cc.sitec.kboot.interceptor.LoginInterceptor;
import cc.sitec.kboot.interceptor.RightsInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p>****************************************************************************</p>
 * <p><b>Copyright © 2010-2018 soho team All Rights Reserved<b></p>
 * <ul style="margin:15px;">
 * <li>Description : cc.sitec.kboot</li>
 * <li>Version     : 1.0</li>
 * <li>Creation    : 2018年08月09日</li>
 * <li>@author     : keeley</li>
 * </ul>
 * <p>****************************************************************************</p>
 */
@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {
    @Autowired
    RightsInterceptor rightsInterceptor;

    @Autowired
    LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(loginInterceptor);
        //此处表示需要拦截程序的所有请求
        registration.addPathPatterns("/**");
        //排除指定请求不拦截
        registration.excludePathPatterns("/", "/login", "/error");

        //请求的权限过滤器
        InterceptorRegistration rightsRegistration = registry.addInterceptor(rightsInterceptor);

        rightsRegistration.addPathPatterns("/**").excludePathPatterns("/error");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
