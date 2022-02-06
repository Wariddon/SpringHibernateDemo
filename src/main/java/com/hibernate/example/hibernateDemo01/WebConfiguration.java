package com.hibernate.example.hibernateDemo01;
import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.EnumSet;

@Configuration
public class WebConfiguration {

    private static final String mapping = "/console/*";

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
    public ServletRegistrationBean h2servletRegistration(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean( new WebServlet());
        registrationBean.addUrlMappings(mapping);
        return registrationBean;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Bean
    public FilterRegistrationBean shallowEtagHeaderFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new ShallowEtagHeaderFilter());
        registration.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));
        registration.addUrlPatterns(mapping);
        return registration;
    }

    @Bean
    public ServletContextInitializer initializer() {
        return new ServletContextInitializer() {

            @Override
            public void onStartup(ServletContext servletContext) throws ServletException {
                servletContext.setInitParameter("p-name", "-value");
            }
        };
    }
}