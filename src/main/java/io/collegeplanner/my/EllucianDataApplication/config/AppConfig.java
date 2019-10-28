package io.collegeplanner.my.EllucianDataApplication.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static io.collegeplanner.my.EllucianDataApplication.util.Constants.JSP_VIEW_RESOLVER_PREFIX;
import static io.collegeplanner.my.EllucianDataApplication.util.Constants.JSP_VIEW_RESOLVER_SUFFIX;

@Configuration
public class AppConfig implements WebMvcConfigurer {

    @Override
    public void configureViewResolvers(final ViewResolverRegistry registry) {
        registry.jsp(JSP_VIEW_RESOLVER_PREFIX, JSP_VIEW_RESOLVER_SUFFIX);
    }

    @Override
    public void configureDefaultServletHandling(final DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}