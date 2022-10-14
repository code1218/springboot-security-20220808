package com.study.security_junil.config.auth;

import com.study.security_junil.filter.AjaxFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<AjaxFilter> ajaxFilter() {
        FilterRegistrationBean<AjaxFilter> bean = new FilterRegistrationBean<AjaxFilter>(new AjaxFilter());
        bean.addUrlPatterns("/api/*");
        bean.setOrder(0);
        return bean;
    }

}
